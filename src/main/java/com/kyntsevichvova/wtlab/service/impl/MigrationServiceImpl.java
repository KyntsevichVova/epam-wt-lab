package com.kyntsevichvova.wtlab.service.impl;

import com.kyntsevichvova.wtlab.bean.Bet;
import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.bean.Race;
import com.kyntsevichvova.wtlab.service.MigrationService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Log4j2
public class MigrationServiceImpl implements MigrationService {
    @Override
    public void migrateHorses(Connection connection, List<Horse> horses) throws ServiceException {
        try {
            log.debug("Preparing statement to migrate HORSES");

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO HORSE (id, name, wins) VALUES (?, ?, ?);");

            for (Horse horse : horses) {
                log.debug("Migrating horse: " + horse.toString());

                statement.setLong(1, horse.getId());
                statement.setString(2, horse.getName());
                statement.setInt(3, horse.getWins());

                statement.execute();
            }

            statement.close();

        } catch (SQLException e) {
            log.error("Error migrating HORSES: " + e.getMessage());
            throw new ServiceException(e);
        }

    }

    @Override
    public void migrateRaces(Connection connection, List<Race> races) throws ServiceException {
        try {
            log.debug("Preparing statement to migrate RACES");

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO RACE (id, `date`, winnerId) VALUES (?, ?, ?);");

            for (Race race : races) {
                log.debug("Migrating race: " + race.toString());

                statement.setLong(1, race.getId());
                statement.setDate(2, new Date(race.getDate().getTime()));
                statement.setLong(3, race.getWinnerId());

                statement.execute();
            }

            statement.close();

        } catch (SQLException e) {
            log.error("Error migrating RACES: " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public void migrateBets(Connection connection, List<Bet> bets) throws ServiceException {
        try {
            log.debug("Preparing statement to migrate BETS");

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO BET (id, user, raceId, horseId, sum) VALUES (?, ?, ?, ?, ?);");

            for (Bet bet : bets) {
                log.debug("Migrating bet: " + bet.toString());

                statement.setLong(1, bet.getId());
                statement.setString(2, bet.getUser());
                statement.setLong(3, bet.getRaceId());
                statement.setLong(4, bet.getHorseId());
                statement.setInt(5, bet.getSum());

                statement.execute();
            }

            statement.close();

        } catch (SQLException e) {
            log.error("Error migrating BETS: " + e.getMessage());
            throw new ServiceException(e);
        }
    }
}
