package com.kyntsevichvova.wtlab.service.impl;

import com.kyntsevichvova.wtlab.service.ConnectionService;
import com.kyntsevichvova.wtlab.service.exception.ServiceException;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

@Log4j2
public class ConnectionServiceImpl implements ConnectionService {

    private Connection connection;

    private final static Path schema = Paths.get("src/main/resources/schema.sql");
    private final static Path data = Paths.get("src/main/resources/data.sql");

    @Override
    public Connection acquireConnection(String url, String username, String password) throws ServiceException {
        if (connection == null) {
            log.debug("Acquiring first connection");
            log.debug("url = " + url);
            log.debug("username = " + username);
            log.debug("password = " + password);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager
                        .getConnection(url + "/?user=" + username + "&password=" + password);
                initSchema();
                initData();
            } catch (ClassNotFoundException | SQLException e) {
                log.error(e.getMessage());
                throw new ServiceException(e);
            }
        }

        return connection;
    }

    private void initSchema() throws ServiceException {
        if (Files.exists(schema)) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(String.join("", Files.readAllLines(schema)));
            } catch (SQLException | IOException e) {
                log.error("Database error while initializing schema: " + e.getMessage());
                throw new ServiceException(e);
            }
        }
    }

    private void initData() throws ServiceException {
        if (Files.exists(data)) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(String.join("", Files.readAllLines(data)));
            } catch (SQLException | IOException e) {
                log.error("Database: " + e.getMessage());
                throw new ServiceException(e);
            }
        }
    }

}
