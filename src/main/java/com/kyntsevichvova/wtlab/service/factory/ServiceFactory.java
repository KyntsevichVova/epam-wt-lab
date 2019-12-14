package com.kyntsevichvova.wtlab.service.factory;

import com.kyntsevichvova.wtlab.service.HorseService;
import com.kyntsevichvova.wtlab.service.UserService;
import com.kyntsevichvova.wtlab.service.impl.HorseServiceImpl;
import com.kyntsevichvova.wtlab.service.impl.InmemoryUserService;

public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();

    private UserService userService = new InmemoryUserService();
    private HorseService horseService = new HorseServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public HorseService getHorseService() {
        return horseService;
    }
}
