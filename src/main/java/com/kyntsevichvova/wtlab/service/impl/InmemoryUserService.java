package com.kyntsevichvova.wtlab.service.impl;

import com.kyntsevichvova.wtlab.bean.User;
import com.kyntsevichvova.wtlab.dao.CrudDAO;
import com.kyntsevichvova.wtlab.dao.exception.DAOException;
import com.kyntsevichvova.wtlab.dao.factory.DAOFactory;
import com.kyntsevichvova.wtlab.service.UserService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;

import java.util.Optional;

public class InmemoryUserService implements UserService {
    private User currentUser;

    @Override
    public String register(User user) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CrudDAO<User> userDAO = daoFactory.getUserDAO();

        try {
            Optional<User> found = userDAO
                    .findAll()
                    .stream()
                    .filter(item -> item.getLogin().equals(user.getLogin()))
                    .findFirst();
            if (found.isPresent()) {
                throw new ServiceException("User already exists");
            }

            userDAO.save(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return "You were registered. Try to log in";
    }

    @Override
    public String signIn(User user) throws ServiceException {
        if (currentUser != null) {
            return "You must sign out first";
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        CrudDAO<User> userDAO = daoFactory.getUserDAO();

        try {
            Optional<User> found = userDAO
                    .findAll()
                    .stream()
                    .filter(item -> item.getLogin().equals(user.getLogin()))
                    .findFirst();
            if (!found.isPresent()) {
                throw new ServiceException("No such user exists");
            }
            User foundUser = found.get();
            if (!foundUser.getLogin().equals(user.getLogin())) {
                throw new ServiceException("Wrong password");
            }

            currentUser = foundUser;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return "You were signed in";
    }

    @Override
    public String signOut() {
        if (currentUser == null) {
            return "You are already signed out";
        }
        currentUser = null;
        return "You were signed out";
    }
}
