package com.kyntsevichvova.wtlab.dao.exception;

public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }

    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
