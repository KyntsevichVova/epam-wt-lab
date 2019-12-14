package com.kyntsevichvova.wtlab.controller.command;

public enum CommandName {
    SIGN_IN("signin"),
    REGISTER("reg"),
    SIGN_OUT("signout"),
    ADD_HORSE("addhorse"),
    REMOVE_HORSE("remove_horse"),
    SHOW_HORSES("showhorses");

    private String command;

    CommandName(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
