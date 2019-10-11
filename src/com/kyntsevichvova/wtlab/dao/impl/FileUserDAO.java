package com.kyntsevichvova.wtlab.dao.impl;

import com.kyntsevichvova.wtlab.bean.User;
import com.kyntsevichvova.wtlab.dao.UserDAO;
import com.kyntsevichvova.wtlab.dao.exception.DAOException;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUserDAO implements UserDAO {
    private final static String DB_FILENAME = "";
    private List<User> db = new ArrayList<>();
    private Path dbPath = Paths.get(DB_FILENAME);
    private FileTime lastModified = FileTime.fromMillis(0L);

    @Override
    public void register(String login, String password) throws DAOException {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException();
        }

        updateDB();

        for (User user : db) {
            if (user.getLogin().equals(login)) {
                throw new DAOException("Username already exists");
            }
        }

        try {
            Files.write(dbPath, Arrays.asList(login, password), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new DAOException("Cannot write to DB file", e);
        }
    }

    @Override
    public void signIn(String login, String password) throws DAOException {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException();
        }

        updateDB();

        for (User user : db) {
            if (user.getLogin().equals(login)) {
                if (!user.getPassword().equals(password)) {
                    throw new DAOException("Wrong password");
                }
                break;
            }
        }
    }

    private void updateDB() throws DAOException {
        if (!Files.exists(dbPath)) {
            try {
                Files.createFile(dbPath);
            } catch (IOException e) {
                throw new DAOException("Cannot create DB file", e);
            }
        }

        try {
            FileTime fileTime = Files.getLastModifiedTime(dbPath);
            if (lastModified.compareTo(fileTime) < 0) {
                List<String> lines = Files.readAllLines(dbPath);
                List<User> updatedDB = new ArrayList<>();
                for (int i = 0; i < lines.size(); i += 2) {
                    if (i + 1 >= lines.size() || lines.get(i).isEmpty()) {
                        throw new DAOException("DB is corrupted");
                    }
                    updatedDB.add(new User(lines.get(i), lines.get(i + 1)));
                }
                db = updatedDB;
                lastModified = fileTime;
            }
        } catch (IOException e) {
            throw new DAOException("Cannot access DB", e);
        }
    }
}
