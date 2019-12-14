package com.kyntsevichvova.wtlab.service.impl;

import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.dao.CrudDAO;
import com.kyntsevichvova.wtlab.dao.exception.DAOException;
import com.kyntsevichvova.wtlab.dao.factory.DAOFactory;
import com.kyntsevichvova.wtlab.service.HorseService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HorseServiceImpl implements HorseService {
    /**
     * @param name Name of horse to add
     * @param wins String representation of number of wins
     * @return Response of service
     * @throws ServiceException Thrown if horse with such name already exists or there was a problem with DAO
     */
    @Override
    public String addHorse(String name, String wins) throws ServiceException {
        int numWins;
        try {
            numWins = Integer.parseInt(wins);
        } catch (NumberFormatException e) {
            return "Number of wins must be a number";
        }
        if (numWins < 0) {
            return "Number of wins must be positive";
        }

        CrudDAO<Horse> horseDAO = DAOFactory.getInstance().getHorseDAO();

        try {
            Optional<Horse> found = horseDAO
                    .findAll()
                    .stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst();
            if (found.isPresent()) {
                throw new ServiceException("Horse with such name already exists");
            }

            horseDAO.save(new Horse(name, numWins));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return "Horse was added";
    }

    @Override
    public String removeHorse(String name) throws ServiceException {
        CrudDAO<Horse> horseDAO = DAOFactory.getInstance().getHorseDAO();

        try {
            Optional<Horse> found = horseDAO
                    .findAll()
                    .stream()
                    .filter(item -> item.getName().equals(name))
                    .findFirst();
            if (!found.isPresent()) {
                throw new ServiceException("No horse with such name");
            }

            horseDAO.deleteById(found.get().getId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return "Horse was removed";
    }

    @Override
    public String showHorses(String comparator) throws ServiceException {
        Comparator<Horse> comparator1;
        if (comparator.equals("wins")) {
            comparator1 = Horse.getWinsComparator();
        } else {
            comparator1 = Horse.getNameComparator();
        }

        CrudDAO<Horse> horseDAO = DAOFactory.getInstance().getHorseDAO();

        try {
            List<Horse> horses = horseDAO.findAll();
            horses.sort(comparator1);
            return horses
                    .stream()
                    .map(Horse::toString)
                    .collect(Collectors.joining("\n"));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
