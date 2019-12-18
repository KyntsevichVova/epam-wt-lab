package com.kyntsevichvova.wtlab;

import com.kyntsevichvova.wtlab.model.Model;
import com.kyntsevichvova.wtlab.model.exception.ModelException;
import com.kyntsevichvova.wtlab.parser.ModelParser;
import com.kyntsevichvova.wtlab.parser.exception.ParserException;
import com.kyntsevichvova.wtlab.parser.impl.SaxParser;
import com.kyntsevichvova.wtlab.service.MigrationService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;
import com.kyntsevichvova.wtlab.service.factory.ServiceFactory;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
public class Main {

    private final static String xmlFile = "src/main/resources/data.xml";
    private final static String xsdFile = "src/main/resources/data.xsd";
    private final static String url = "jdbc:mysql://localhost:3306/";
    private final static String username = "root";
    private final static String password = "root";

    public static void main(String[] args) {
        log.debug("Started migration");

        Model model = Model.getInstance();

        try {

            model.init(xmlFile, xsdFile);

        } catch (ModelException e) {
            e.printStackTrace();
            log.error("Exiting application...");
            System.exit(-1);
        }

        ModelParser parser = new SaxParser();

        try (Connection connection = ServiceFactory
                .getInstance()
                .getConnectionService()
                .acquireConnection(url, username, password)
        ) {

            parser.parse(model);

            MigrationService service = ServiceFactory.getInstance().getMigrationService();

            service.migrateHorses(connection, parser.getHorses());
            service.migrateRaces(connection, parser.getRaces());
            service.migrateBets(connection, parser.getBets());

        } catch (ServiceException | SQLException | ParserException e) {
            log.error("Error while migrating: " + e.getMessage());
        }
    }

}
