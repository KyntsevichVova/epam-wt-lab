package com.kyntsevichvova.wtlab.controller;

import com.kyntsevichvova.wtlab.controller.command.Command;
import com.kyntsevichvova.wtlab.controller.command.CommandName;
import com.kyntsevichvova.wtlab.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<String, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.SIGN_IN.getCommand(), new SignInCommand());
        repository.put(CommandName.REGISTER.getCommand(), new RegisterCommand());
        repository.put(CommandName.SIGN_OUT.getCommand(), new SignOutCommand());
        repository.put(CommandName.ADD_HORSE.getCommand(), new AddHorseCommand());
        repository.put(CommandName.REMOVE_HORSE.getCommand(), new RemoveHorseCommand());
        repository.put(CommandName.SHOW_HORSES.getCommand(), new ShowHorsesCommand());
    }

    Command getCommand(String commandName) {
        if (repository.containsKey(commandName)) {
            return repository.get(commandName);
        } else {
            throw new IllegalArgumentException("Unsupported command");
        }
    }
}
