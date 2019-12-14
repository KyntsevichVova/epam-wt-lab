package com.kyntsevichvova.wtlab.dao;

import com.kyntsevichvova.wtlab.dao.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> {
    Optional<T> findById(Long id) throws DAOException;
    List<T> findAll() throws DAOException;
    void save(T item) throws DAOException;
    void deleteById(Long id) throws DAOException;
    void delete(T item) throws DAOException;
}
