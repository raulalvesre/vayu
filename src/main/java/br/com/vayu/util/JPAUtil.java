package br.com.vayu.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManager entityManager;

    static {
        entityManager = Persistence.createEntityManagerFactory("vayu").createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

}
