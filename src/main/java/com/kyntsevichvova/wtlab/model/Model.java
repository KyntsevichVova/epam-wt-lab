package com.kyntsevichvova.wtlab.model;

import com.kyntsevichvova.wtlab.model.exception.ModelException;
import com.kyntsevichvova.wtlab.service.ValidatorService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;
import com.kyntsevichvova.wtlab.service.factory.ServiceFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    private Model() {
    }

    public void init(String xmlFile, String xsdFile) throws ModelException {
        if (!initialized) {
            try (
                    InputStream xmlStream = Files.newInputStream(Paths.get(xmlFile));
                    InputStreamReader xmlStreamReader = new InputStreamReader(xmlStream);
                    BufferedReader xmlReader = new BufferedReader(xmlStreamReader);
                    InputStream xsdStream = Files.newInputStream(Paths.get(xsdFile));
                    InputStreamReader xsdStreamReader = new InputStreamReader(xsdStream);
                    BufferedReader xsdReader = new BufferedReader(xsdStreamReader)
            ) {
                xml = xmlReader
                        .lines()
                        .collect(Collectors.joining("\r\n"));

                xsd = xsdReader
                        .lines()
                        .collect(Collectors.joining("\r\n"));

                validate(xml, xsd);

                initialized = true;
            } catch (IOException e) {
                log.error("Error while loading model: " + e.getMessage());
                throw new ModelException(e);
            }
        }
    }

    private void validate(String xml, String xsd) throws ModelException {

        ValidatorService service = ServiceFactory.getInstance().getValidatorService();

        try (
                ByteArrayInputStream xmlStream = new ByteArrayInputStream(xml.getBytes());
                ByteArrayInputStream xsdStream = new ByteArrayInputStream(xsd.getBytes())
        ) {

            service.validate(
                    xmlStream,
                    xsdStream
            );

        } catch (IOException | ServiceException e) {
            log.error("Error while validating model:" + e.getMessage());
            throw new ModelException(e);
        }
    }

}
