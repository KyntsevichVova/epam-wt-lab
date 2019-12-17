package com.kyntsevichvova.wtlab.parser.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EntityName {

    NONE("NONE"),
    HORSE("horse"),
    RACE("race"),
    BET("bet");

    @Getter
    private String value;

}
