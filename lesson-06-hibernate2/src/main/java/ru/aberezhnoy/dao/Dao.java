package ru.aberezhnoy.dao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface Dao<T, ID extends Serializable> {

    List<T> findAll();

    public Optional<T> findById(ID id);

    void deleteById(ID id);

    T saveOrUpdate(T obj);
}
