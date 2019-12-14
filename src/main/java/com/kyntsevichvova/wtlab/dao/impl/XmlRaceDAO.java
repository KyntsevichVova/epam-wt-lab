package com.kyntsevichvova.wtlab.dao.impl;

import com.kyntsevichvova.wtlab.bean.Race;
import com.kyntsevichvova.wtlab.dao.XmlRepository;

import java.nio.file.Path;

public class XmlRaceDAO extends XmlRepository<Race> {
    public XmlRaceDAO(Path dbPath) {
        super(dbPath, Race.class);
    }
}
