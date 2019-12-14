package com.kyntsevichvova.wtlab.controller.command;

public interface Command {
    String execute(String params) throws Exception;
}
