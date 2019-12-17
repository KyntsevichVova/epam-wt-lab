package com.kyntsevichvova.wtlab.model;

import com.kyntsevichvova.wtlab.service.ValidatorService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;
import com.kyntsevichvova.wtlab.service.factory.ServiceFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.stream.Collectors;

@Log4j2
public class Model {

    @Getter
    private final static Model instance = new Model();

    private boolean initialized = false;

    @Getter
    private String xml;

    @Getter
    private String xsd;

    private Model() {}

    public void init(ServletContext context) {
        if (!initialized) {
            try {
                InputStream s = context.getResourceAsStream("/resources/data.xml");
                BufferedReader r = new BufferedReader(new InputStreamReader(s));
                xml = r.lines().collect(Collectors.joining("\r\n"));

                s = context.getResourceAsStream("/resources/data.xsd");
                r = new BufferedReader(new InputStreamReader(s));
                xsd = r.lines().collect(Collectors.joining("\r\n"));

                validate(xml, xsd);

                initialized = true;
            } catch (Exception e) {
                log.error("Error while loading model: " + e.getMessage());
            }
        }
    }

    private void validate(String xml, String xsd) {
        ValidatorService service = ServiceFactory.getInstance().getValidatorService();
        try {
            service.validate(
                    new ByteArrayInputStream(xml.getBytes()),
                    new ByteArrayInputStream((xsd.getBytes()))
            );
        } catch (ServiceException e) {
            log.error("Error while validating model:" + e.getMessage());
        }
    }

}
