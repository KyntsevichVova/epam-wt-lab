package com.kyntsevichvova.wtlab.dao.impl;

import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.dao.XmlRepository;

import java.nio.file.Path;

public class XmlHorseDAO extends XmlRepository<Horse> {
    public XmlHorseDAO(Path dbPath) {
        super(dbPath, Horse.class);
    }
}
