package com.kyntsevichvova.wtlab;

import com.kyntsevichvova.wtlab.handler.DataHandler;
import com.kyntsevichvova.wtlab.service.MigrationService;
import com.kyntsevichvova.wtlab.service.ValidatorService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;
import com.kyntsevichvova.wtlab.service.factory.ServiceFactory;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
public class Main {

    private final static String xmlFile = "src/main/resources/data.xml";
    private final static String xsdFile = "src/main/resources/data.xsd";
    private final static String url = "jdbc:mysql://localhost:3306/";
    private final static String username = "root";
    private final static String password = "root";

    public static void main(String[] args) throws SAXException, IOException {
        log.debug("Started migration");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.debug("Driver class not found");
            log.error(e.getMessage());
            log.error("Exiting application...");
            System.exit(-1);
        }

        ValidatorService validatorService = ServiceFactory.getInstance().getValidatorService();
        try {
            validatorService.validate(Paths.get(xmlFile), Paths.get(xsdFile));
        } catch (ServiceException e) {
            e.printStackTrace();
            log.error("Exiting application...");
            System.exit(-1);
        }

        DataHandler handler = new DataHandler();
        XMLReader reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(handler);
        reader.parse(new InputSource(xmlFile));

        try (Connection connection = ServiceFactory
                .getInstance()
                .getConnectionService()
                .acquireConnection(url, username, password)
        ) {

            MigrationService service = ServiceFactory.getInstance().getMigrationService();

            service.migrateHorses(connection, handler.getHorses());
            service.migrateRaces(connection, handler.getRaces());
            service.migrateBets(connection, handler.getBets());

        } catch (ServiceException | SQLException e) {
            log.error("Error while migrating: " + e.getMessage());
        }
    }

}
