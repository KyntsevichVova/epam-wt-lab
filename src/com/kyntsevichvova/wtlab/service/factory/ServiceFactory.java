package com.kyntsevichvova.wtlab.service.factory;

import com.kyntsevichvova.wtlab.service.UserService;
import com.kyntsevichvova.wtlab.service.impl.InmemoryUserService;

public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();

    private UserService userService = new InmemoryUserService();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }
}
