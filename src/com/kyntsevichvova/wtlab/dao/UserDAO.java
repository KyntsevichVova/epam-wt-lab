package com.kyntsevichvova.wtlab.dao;

import com.kyntsevichvova.wtlab.dao.exception.DAOException;

public interface UserDAO {
    void register(String login, String password) throws DAOException;
    void signIn(String login, String password) throws DAOException;
}
