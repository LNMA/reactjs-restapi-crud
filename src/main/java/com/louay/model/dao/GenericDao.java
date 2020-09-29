package com.louay.model.dao;

public interface GenericDao<T> {
    <S extends T> S delete(S entity);

    <S extends T> S findOneById(S entity);

    <S extends T> Boolean isExist(S entity);
}
