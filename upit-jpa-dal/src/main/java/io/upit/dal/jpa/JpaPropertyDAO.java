package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.PropertyDAO;
import io.upit.dal.jpa.models.JpaProperty;
import io.upit.dal.models.Property;

import javax.persistence.EntityManager;

public class JpaPropertyDAO extends EntityManagerDAO<Property, String> implements PropertyDAO {

    @Inject
    public JpaPropertyDAO(EntityManager entityManager) {
        super(Property.class, JpaProperty.class, entityManager);
    }
}
