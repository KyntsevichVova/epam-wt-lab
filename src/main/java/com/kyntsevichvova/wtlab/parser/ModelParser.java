package com.kyntsevichvova.wtlab.parser;

import com.kyntsevichvova.wtlab.bean.Bet;
import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.bean.Race;
import com.kyntsevichvova.wtlab.model.Model;
import com.kyntsevichvova.wtlab.parser.exception.ParserException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class ModelParser {

    @Getter
    protected List<Horse> horses = new ArrayList<>();

    @Getter
    protected List<Race> races = new ArrayList<>();

    @Getter
    protected List<Bet> bets = new ArrayList<>();

    public abstract void parse(Model model) throws ParserException;

}
