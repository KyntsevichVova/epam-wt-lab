package com.kyntsevichvova.wtlab.dao.factory;

import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.bean.Race;
import com.kyntsevichvova.wtlab.bean.User;
import com.kyntsevichvova.wtlab.dao.CrudDAO;
import com.kyntsevichvova.wtlab.dao.impl.XmlHorseDAO;
import com.kyntsevichvova.wtlab.dao.impl.XmlRaceDAO;
import com.kyntsevichvova.wtlab.dao.impl.XmlUserDAO;

import java.nio.file.Paths;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private final CrudDAO<User> userDAO = new XmlUserDAO(Paths.get("D:\\lab\\users.xml"));
    private final CrudDAO<Horse> horseDAO = new XmlHorseDAO(Paths.get("D:\\lab\\horses.xml"));
    private final CrudDAO<Race> raceDAO = new XmlRaceDAO(Paths.get("D:\\lab\\races.xml"));

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public CrudDAO<User> getUserDAO() {
        return userDAO;
    }

    public CrudDAO<Horse> getHorseDAO() {
        return horseDAO;
    }

    public CrudDAO<Race> getRaceDAO() {
        return raceDAO;
    }
}
