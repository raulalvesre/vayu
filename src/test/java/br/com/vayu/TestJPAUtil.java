package br.com.vayu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPAUtil {

    private static EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("vayu_test");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

}
