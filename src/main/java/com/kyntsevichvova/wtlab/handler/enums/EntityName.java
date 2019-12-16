package com.kyntsevichvova.wtlab.handler.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public enum EntityName {

    NONE("NONE"),
    HORSE("HORSE"),
    RACE("RACE"),
    BET("BET");

    @Getter
    private String value;

}
