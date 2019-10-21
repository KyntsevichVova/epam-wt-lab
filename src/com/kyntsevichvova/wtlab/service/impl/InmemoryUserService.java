package com.kyntsevichvova.wtlab.service.impl;

import com.kyntsevichvova.wtlab.bean.User;
import com.kyntsevichvova.wtlab.dao.UserDAO;
import com.kyntsevichvova.wtlab.dao.exception.DAOException;
import com.kyntsevichvova.wtlab.dao.factory.DAOFactory;
import com.kyntsevichvova.wtlab.service.UserService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;

public class InmemoryUserService implements UserService {
    private User currentUser;

    @Override
    public void register(User user) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            userDAO.register(user.getLogin(), user.getPassword());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void signIn(User user) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            userDAO.signIn(user.getLogin(), user.getPassword());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        currentUser = user;
    }

    @Override
    public void signOut(User user) throws ServiceException {
        currentUser = null;
    }
}
