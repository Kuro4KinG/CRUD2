package com.kuro4king.crud.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    List<T> getAll();

    T getById(ID id);

    T save(T t);

    T update(T t);

    void delete(ID id);
}
