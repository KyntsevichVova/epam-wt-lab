package com.kyntsevichvova.wtlab.service;

import com.kyntsevichvova.wtlab.service.exception.ServiceException;

import java.nio.file.Path;

public interface ValidatorService {

    void validate(Path dataFile, Path schemaFile) throws ServiceException;

}
