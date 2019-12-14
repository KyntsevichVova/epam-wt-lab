package com.kyntsevichvova.wtlab.dao;

import com.kyntsevichvova.wtlab.bean.BaseEntity;
import com.kyntsevichvova.wtlab.dao.exception.DAOException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class XmlRepository<T extends BaseEntity> implements CrudDAO<T> {
    private List<T> db = new ArrayList<>();
    private Long id = 1L;
    private final XmlDatasource<T> datasource;

    public XmlRepository(Path dbPath, Class<T> clazz) {
        datasource = new XmlDatasource<>(dbPath, clazz);
    }

    private void saveDB() throws DAOException {
        try {
            datasource.write(db);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private void updateDB() throws DAOException {
        try {
            db = datasource.read();
        } catch (Exception e) {
            throw new DAOException(e);
        }

        id = db
                .stream()
                .map(BaseEntity::getId)
                .max(Long::compareTo)
                .orElse(0L);
    }

    public List<T> findAll() throws DAOException {
        updateDB();
        return db;
    }

    @Override
    public Optional<T> findById(Long id) throws DAOException {
        updateDB();

        return db
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public void save(T item) throws DAOException {
        updateDB();

        if (item.getId() == null) {
            id++;
            item.setId(id);
            db.add(item);
        } else {
            Optional<T> found = findById(item.getId());
            if (!found.isPresent()) {
                throw new DAOException("No such item exists");
            }
            int index = db.indexOf(found.get());
            db.set(index, item);
        }

        saveDB();
    }

    public void deleteById(Long id) throws DAOException {
        updateDB();

        Optional<T> found = findById(id);
        if (!found.isPresent()) {
            throw new DAOException("No such item exists");
        }

        db.remove(found.get());

        saveDB();
    }

    public void delete(T item) throws DAOException {
        deleteById(item.getId());
    }
}
