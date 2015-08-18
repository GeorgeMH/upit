package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.UpitDAOException;
import io.upit.dal.UploadedFileDAO;
import io.upit.dal.jpa.models.JpaUploadedFile;
import io.upit.dal.models.UploadedFile;
import io.upit.filestorage.FileStorageException;
import io.upit.filestorage.StreamingFileStorageStrategy;
import org.hashids.Hashids;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.InputStream;
import java.util.List;

public class JpaUploadedFileDAO extends EntityManagerDAO<UploadedFile, JpaUploadedFile, Long> implements UploadedFileDAO {

    private final StreamingFileStorageStrategy fileStorageStrategy;
    private final Hashids hashids;

    @Inject
    public JpaUploadedFileDAO(EntityManager entityManager, StreamingFileStorageStrategy fileStorageStrategy, Hashids hashids) {
        super(UploadedFile.class, JpaUploadedFile.class, entityManager);
        this.fileStorageStrategy = fileStorageStrategy;
        this.hashids = hashids;
    }

    public UploadedFile getByFileHash(String fileHash) {
        TypedQuery<JpaUploadedFile> query = entityManager.createQuery("SELECT uf FROM UploadedFile uf WHERE uf.fileHash = :fileHash", JpaUploadedFile.class);
        query.setParameter("fileHash", fileHash);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UploadedFile getByIdHash(String idHash) {
        TypedQuery<JpaUploadedFile> query = entityManager.createQuery("SELECT uf FROM UploadedFile uf WHERE uf.idHash = :idHash", JpaUploadedFile.class);
        query.setParameter("idHash", idHash);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<? extends UploadedFile> getByUserId(long userId) {
        TypedQuery<JpaUploadedFile> query = entityManager.createQuery("SELECT uf FROM UploadedFile uf WHERE uf.userId = :userId", JpaUploadedFile.class);

        query.setParameter("userId", userId);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UploadedFile create(UploadedFile uploadedFile, InputStream inputStream) throws UpitDAOException {
        try {
            UploadedFile uploadedFileFromStore = fileStorageStrategy.storeFile(uploadedFile, inputStream);

            UploadedFile existingEntity = getByFileHash(uploadedFileFromStore.getFileHash());
            if (null == existingEntity) {
                existingEntity = create(uploadedFileFromStore);
                existingEntity.setIdHash(hashids.encode(existingEntity.getId()));
                update(existingEntity);
            }

            return existingEntity;
        } catch (FileStorageException e) {
            throw new UpitDAOException("Failed processing file", e);
        }
    }

    @Override
    public InputStream getFileStream(UploadedFile uploadedFile) {
        return fileStorageStrategy.retrieveFile(uploadedFile);
    }
}
