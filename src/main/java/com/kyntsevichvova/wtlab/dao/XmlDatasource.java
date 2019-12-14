package com.kyntsevichvova.wtlab.dao;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kyntsevichvova.wtlab.dao.exception.DAOException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

public class XmlDatasource<T> {
    private FileTime lastModified = FileTime.fromMillis(0L);
    private final Path dbPath;
    private final JavaType type;
    private List<T> cached = new ArrayList<>();

    public XmlDatasource(Path dbPath, Class<T> clazz) {
        this.dbPath = dbPath;
        this.type = new ObjectMapper().getTypeFactory().constructCollectionType(List.class, clazz);
    }

    public void write(List<T> data) throws DAOException {
        if (!Files.exists(dbPath)) {
            try {
                Files.createDirectories(dbPath.getParent());
                Files.createFile(dbPath);
            } catch (IOException e) {
                throw new DAOException("Cannot create DB file", e);
            }
        }

        try (OutputStream outputStream = Files.newOutputStream(dbPath)) {
            XmlMapper mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(outputStream, data);
        } catch (IOException e) {
            throw new DAOException("Cannot write to DB file", e);
        }
    }

    public List<T> read() throws DAOException {
        if (!Files.exists(dbPath)) {
            try {
                Files.createDirectories(dbPath.getParent());
                Files.createFile(dbPath);
            } catch (IOException e) {
                throw new DAOException("Cannot create DB file", e);
            }
        }

        try {
            FileTime fileTime = Files.getLastModifiedTime(dbPath);
            if (lastModified.compareTo(fileTime) < 0) {
                try (InputStream inputStream = Files.newInputStream(dbPath)) {
                    XmlMapper mapper = new XmlMapper();
                    mapper.enable(SerializationFeature.INDENT_OUTPUT);
                    lastModified = fileTime;
                    cached = mapper.readValue(inputStream, type);
                    return cached;
                } catch (IOException e) {
                    throw new DAOException("Cannot read from DB file", e);
                }
            }
        } catch (IOException e) {
            throw new DAOException("Cannot get access to DB file", e);
        }
        return cached;
    }
}
