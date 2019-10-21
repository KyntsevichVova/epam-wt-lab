package com.kyntsevichvova.wtlab.service;

import com.kyntsevichvova.wtlab.bean.User;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;

public interface UserService {
    void register(User user) throws ServiceException;
    void signIn(User user) throws ServiceException;
    void signOut(User user) throws ServiceException;
}
