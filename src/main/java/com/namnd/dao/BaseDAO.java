package com.namnd.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDAO {
    <T> List<T> findAll(Class<T> clazz);
    <T> Optional<T> findById(Serializable id, Class<T> clazz);
    <T> void persist(T entity);
    <T> void update(T entity);
    <T> void delete(T entity);
}
