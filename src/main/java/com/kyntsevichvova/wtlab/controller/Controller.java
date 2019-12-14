package com.kyntsevichvova.wtlab.controller;

import com.kyntsevichvova.wtlab.controller.command.Command;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    private final char delimiter = ' ';

    public String executeTask(String request) {
        int delimiterPos = request.indexOf(delimiter);
        String commandName;
        String params;
        if (delimiterPos != -1) {
            commandName = request.substring(0, delimiterPos);
            params = request.substring(delimiterPos + 1);
        } else {
            commandName = request;
            params = "";
        }

        String response;

        try {
            Command command = commandProvider.getCommand(commandName);
            response = command.execute(params);
        } catch (Exception e) {
            response = e.getMessage();
        }

        return response;
    }
}
