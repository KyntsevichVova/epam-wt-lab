package com.kyntsevichvova.wtlab.controller.command.impl;

import com.kyntsevichvova.wtlab.controller.command.Command;
import com.kyntsevichvova.wtlab.service.HorseService;
import com.kyntsevichvova.wtlab.service.factory.ServiceFactory;

public class RemoveHorseCommand implements Command {
    @Override
    public String execute(String params) throws Exception {
        HorseService horseService = ServiceFactory.getInstance().getHorseService();

        return horseService.removeHorse(params.trim());
    }
}
