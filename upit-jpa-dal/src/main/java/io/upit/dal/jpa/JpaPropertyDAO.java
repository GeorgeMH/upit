package io.upit.dal.jpa;

import com.google.inject.Inject;
import io.upit.dal.PropertyDAO;
import io.upit.dal.jpa.models.JpaPropertyValue;
import io.upit.dal.models.PropertyValue;

import javax.persistence.EntityManager;

public class JpaPropertyDAO extends EntityManagerDAO<PropertyValue, JpaPropertyValue, String> implements PropertyDAO {

    @Inject
    public JpaPropertyDAO(EntityManager entityManager) {
        super(PropertyValue.class, JpaPropertyValue.class, entityManager);
    }
}
