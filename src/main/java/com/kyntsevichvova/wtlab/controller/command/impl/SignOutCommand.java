package com.kyntsevichvova.wtlab.controller.command.impl;

import com.kyntsevichvova.wtlab.controller.command.Command;
import com.kyntsevichvova.wtlab.service.UserService;
import com.kyntsevichvova.wtlab.service.factory.ServiceFactory;

public class SignOutCommand implements Command {
    @Override
    public String execute(String params) {
        UserService userService = ServiceFactory.getInstance().getUserService();

        return userService.signOut();
    }
}
