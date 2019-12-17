package com.kyntsevichvova.wtlab.service.impl;

import com.kyntsevichvova.wtlab.service.ValidatorService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class XSDValidatorService implements ValidatorService {

    @Override
    public void validate(InputStream dataStream, InputStream schemaStream) throws ServiceException {
        log.debug("Start validation with XSD");

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(schemaStream));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(dataStream));

        } catch (IOException | SAXException e) {
            log.error("Validation caused exception: " + e.getMessage());
            throw new ServiceException(e);
        }

        log.debug("Validation successful");
    }
}
