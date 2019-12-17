package com.kyntsevichvova.wtlab.parser.impl;

import com.kyntsevichvova.wtlab.model.Model;
import com.kyntsevichvova.wtlab.parser.ModelParser;
import com.kyntsevichvova.wtlab.parser.exception.ParserException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SaxParser extends ModelParser {

    public void parse(Model model) throws ParserException {
        SaxHandler handler = new SaxHandler();

        try {

            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(new ByteArrayInputStream(model.getXml().getBytes())));

        } catch (SAXException | IOException e) {
            throw new ParserException(e);
        }

        horses = handler.getHorses();
        races = handler.getRaces();
        bets = handler.getBets();

    }

}
