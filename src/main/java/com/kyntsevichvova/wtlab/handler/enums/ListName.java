package com.kyntsevichvova.wtlab.handler.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ListName {

    NONE("NONE"),
    HORSES("HORSES"),
    RACES("RACES"),
    BETS("BETS");

    @Getter
    private String value;

}
