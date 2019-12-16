package com.kyntsevichvova.wtlab.service.factory;

import com.kyntsevichvova.wtlab.service.ValidatorService;
import com.kyntsevichvova.wtlab.service.impl.XSDValidatorService;
import lombok.Getter;

public class ServiceFactory {

    @Getter
    private final static ServiceFactory instance = new ServiceFactory();

    @Getter
    private final ValidatorService validatorService = new XSDValidatorService();

}
