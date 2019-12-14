package com.kyntsevichvova.wtlab.service;

import com.kyntsevichvova.wtlab.service.exception.ServiceException;

public interface HorseService {
    String addHorse(String name, String wins) throws ServiceException;
    String removeHorse(String name) throws ServiceException;
    String showHorses(String comparator) throws ServiceException;
}
