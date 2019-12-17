package com.kyntsevichvova.wtlab.parser.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ListName {

    NONE("NONE"),
    HORSES("Horses"),
    RACES("Races"),
    BETS("Bets");

    @Getter
    private String value;

}
