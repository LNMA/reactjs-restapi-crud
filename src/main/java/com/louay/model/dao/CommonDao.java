package com.louay.model.dao;

public interface CommonDao<T> {
    <S extends T> S save(S entity);

    <S extends T> S update(S entity);
}
