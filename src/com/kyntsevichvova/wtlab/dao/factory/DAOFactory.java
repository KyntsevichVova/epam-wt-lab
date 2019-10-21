package com.kyntsevichvova.wtlab.dao.factory;

import com.kyntsevichvova.wtlab.dao.UserDAO;
import com.kyntsevichvova.wtlab.dao.impl.FileUserDAO;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new FileUserDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
