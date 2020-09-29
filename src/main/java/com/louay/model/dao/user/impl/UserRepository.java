package com.louay.model.dao.user.impl;

import com.louay.model.dao.CommonDaoImpl;
import com.louay.model.dao.user.UserDao;
import com.louay.model.entity.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class UserRepository extends CommonDaoImpl<User> implements UserDao {
    private static final long serialVersionUID = -2502404840485408004L;

    @Override
    public <S extends User> Boolean isExist(S entity) {
        Assert.notNull(entity, NOT_NULL_ENTITY);

        return !getEntityManager().createQuery("SELECT u FROM User u WHERE u.userId = :userId")
                .setParameter("userId", entity.getUserId())
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public <S extends User> S delete(S entity) {
        Assert.notNull(entity, NOT_NULL_ENTITY);

        Class<? extends User> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S entityFound = (S) getEntityManager().getReference(entityClass, entity.getUserId());
        getEntityManager().remove(entityFound);
        return entity;
    }

    @Override
    public <S extends User> S findOneById(S entity) {
        Assert.notNull(entity, NOT_NULL_ENTITY);

        Class<? extends User> entityClass = entity.getClass();
        @SuppressWarnings("unchecked")
        S result = (S) getEntityManager().find(entityClass, entity.getUserId());
        return result;
    }

    @Override
    public List<User> find100User() {
        return getEntityManager().createQuery("SELECT u from User u", User.class)
                .setMaxResults(100)
                .getResultList();
    }
}
