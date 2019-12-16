package com.kyntsevichvova.wtlab.service;

import com.kyntsevichvova.wtlab.service.exception.ServiceException;

import java.sql.Connection;

public interface ConnectionService {

    Connection acquireConnection(String url, String username, String password) throws ServiceException;

}
