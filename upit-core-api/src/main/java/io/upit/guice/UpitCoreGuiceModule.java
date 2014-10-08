package io.upit.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import io.upit.filestorage.LocalDiskFileStorageStrategy;
import io.upit.filestorage.StreamingFileStorageStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class UpitCoreGuiceModule extends AbstractModule {
    private final Logger logger = LoggerFactory.getLogger(UpitCoreGuiceModule.class);

    @Override
    protected void configure() {

        File instanceHomeDirectory = new File("./upit/");
        File uploadedFileRepositoryPath = new File(instanceHomeDirectory, "uploadedFiles/");

        if(uploadedFileRepositoryPath.exists()) {
            if(!uploadedFileRepositoryPath.isDirectory()) {
                addError("Upload File Repository exists and is not a directory: " + uploadedFileRepositoryPath.getAbsolutePath());
                return;
            } else if(!uploadedFileRepositoryPath.canWrite()) {
                addError("Upload File Repository exists but is not writable: " + uploadedFileRepositoryPath.getAbsolutePath());
                return;
            }
        } else {
            if(!uploadedFileRepositoryPath.mkdirs()){
                addError("Failed creating Upload File Repository: " + uploadedFileRepositoryPath.getAbsolutePath());
                return;
            }
        }

        logger.info("upit.io Home Directory: " + instanceHomeDirectory.getAbsolutePath());
        System.out.println("upit.io Home Directory: " + instanceHomeDirectory.getAbsolutePath());

        logger.info("Uploaded File Repo: " + uploadedFileRepositoryPath.getAbsolutePath());
        System.out.println("Uploaded File Repo: " + uploadedFileRepositoryPath.getAbsolutePath());

        bind(File.class).annotatedWith(Names.named("upitHomeDirectory")).toInstance(instanceHomeDirectory);
        bind(File.class).annotatedWith(Names.named("upitLocalDiskFileRepository")).toInstance(uploadedFileRepositoryPath);

        bind(StreamingFileStorageStrategy.class).to(LocalDiskFileStorageStrategy.class);
    }


}
