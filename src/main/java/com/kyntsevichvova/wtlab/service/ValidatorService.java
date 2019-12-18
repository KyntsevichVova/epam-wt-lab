package com.kyntsevichvova.wtlab.service;

import com.kyntsevichvova.wtlab.service.exception.ServiceException;

import java.io.InputStream;

public interface ValidatorService {

    void validate(InputStream dataStream, InputStream schemaStream) throws ServiceException;

}
