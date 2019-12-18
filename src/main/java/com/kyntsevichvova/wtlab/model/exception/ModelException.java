package com.kyntsevichvova.wtlab.model.exception;

public class ModelException extends Exception {

    public ModelException(String message) {
        super(message);
    }

    public ModelException(Exception e) {
        super(e);
    }

    public ModelException(String message, Exception e) {
        super(message, e);
    }

}
