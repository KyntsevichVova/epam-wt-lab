package com.kyntsevichvova.wtlab.controller.command.impl;

import com.kyntsevichvova.wtlab.controller.command.Command;
import com.kyntsevichvova.wtlab.service.HorseService;
import com.kyntsevichvova.wtlab.service.factory.ServiceFactory;

public class AddHorseCommand implements Command {
    @Override
    public String execute(String params) throws Exception {
        HorseService horseService = ServiceFactory.getInstance().getHorseService();

        String name;
        String wins;
        int delimiterPos = params.indexOf(' ');
        if (delimiterPos != -1) {
            name = params.substring(0, delimiterPos).trim();
            wins = params.substring(delimiterPos + 1).trim();
        } else {
            name = params.trim();
            wins = "0";
        }

        return horseService.addHorse(name, wins);
    }
}
