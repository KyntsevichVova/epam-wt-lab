package com.kyntsevichvova.wtlab.dao.impl;

import com.kyntsevichvova.wtlab.bean.User;
import com.kyntsevichvova.wtlab.dao.XmlRepository;

import java.nio.file.Path;

public class XmlUserDAO extends XmlRepository<User> {
    public XmlUserDAO(Path dbPath) {
        super(dbPath, User.class);
    }
}
