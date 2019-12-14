package com.kyntsevichvova.wtlab.service;

import com.kyntsevichvova.wtlab.bean.User;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;

public interface UserService {
    String register(User user) throws ServiceException;
    String signIn(User user) throws ServiceException;
    String signOut();
}
