package com.kyntsevichvova.wtlab.controller.command.impl;

import com.kyntsevichvova.wtlab.bean.User;
import com.kyntsevichvova.wtlab.controller.command.Command;
import com.kyntsevichvova.wtlab.service.UserService;
import com.kyntsevichvova.wtlab.service.factory.ServiceFactory;

public class RegisterCommand implements Command {
    @Override
    public String execute(String params) throws Exception {
        UserService userService = ServiceFactory.getInstance().getUserService();

        String login;
        String password;
        int delimiterPos = params.indexOf(' ');
        if (delimiterPos != -1) {
            login = params.substring(0, delimiterPos).trim();
            password = params.substring(delimiterPos + 1).trim();
        } else {
            login = params.trim();
            password = "";
        }

        return userService.register(new User(login, password));
    }
}
