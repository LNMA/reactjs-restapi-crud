package com.louay.model.dao;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class CommonDaoImpl<T> implements CommonDao<T>, Serializable {
    private static final long serialVersionUID = -8132072884691046161L;
    private EntityManager entityManager;
    protected final String NOT_NULL_ENTITY = "entity must not be null!.";

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    private void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, NOT_NULL_ENTITY);
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public <S extends T> S update(S entity) {
        Assert.notNull(entity, NOT_NULL_ENTITY);
        if (isExist(entity)) {
            getEntityManager().merge(entity);
            return entity;
        } else {
            throw new UnsupportedOperationException("Can not update not exist entity!.");
        }
    }

    abstract public <S extends T> Boolean isExist(S entity);
}
