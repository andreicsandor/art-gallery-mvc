package org.gallery.model.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class Repository<T> {
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private EntityManagerFactory entityManagerFactory;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    private Class<T> entityClass;

    public Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public Repository(Class<T> entityClass, String persistenceUnit) {
        this.entityClass = entityClass;
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    public T create(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

        return entity;
    }

    public T findById(Long id) {
        return this.entityManager.find(entityClass, id);
    }

    public T update(T entity) {
        entityManager.getTransaction().begin();
        T mergedEntity = entityManager.merge(entity);
        entityManager.getTransaction().commit();

        return mergedEntity;
    }

    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public void close() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }
}
