package com.kyntsevichvova.wtlab.service;

import com.kyntsevichvova.wtlab.bean.Bet;
import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.bean.Race;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;

public interface MigrationService {

    void migrateHorses(Connection connection, List<Horse> horses) throws ServiceException;
    void migrateRaces(Connection connection, List<Race> races) throws ServiceException;
    void migrateBets(Connection connection, List<Bet> bets) throws ServiceException;

}
