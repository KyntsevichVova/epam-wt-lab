package com.kyntsevichvova.wtlab.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kyntsevichvova.wtlab.bean.Bet;
import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.bean.Race;
import com.kyntsevichvova.wtlab.parser.enums.EntityName;
import com.kyntsevichvova.wtlab.parser.enums.ListName;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log4j2
public class SaxHandler extends DefaultHandler {

    @Getter
    private List<Horse> horses = new ArrayList<>();
    @Getter
    private List<Race> races = new ArrayList<>();
    @Getter
    private List<Bet> bets = new ArrayList<>();

    private ListName list = ListName.NONE;

    private StringBuilder builder = new StringBuilder();

    @Override
    public void startDocument() throws SAXException {
        log.debug("Start document parsing");
    }

    @Override
    public void endDocument() throws SAXException {
        log.debug("End document parsing");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String chars = new String(ch).substring(start, start + length);
        log.debug("Characters: " + chars);
        builder.append(chars);
    }

    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) throws SAXException {

        log.debug("Parsing element " + qName);

        Optional<ListName> l = Arrays.stream(ListName.values())
                .filter(listName -> listName.getValue().equals(qName))
                .findAny();

        if (l.isPresent()) {
            list = l.get();
        } else {
            Optional<EntityName> e = Arrays.stream(EntityName.values())
                    .filter(entityName -> entityName.getValue().equals(qName))
                    .findAny();

            if (e.isPresent()) {
                builder = new StringBuilder();
            }

            builder.append('<').append(qName).append('>');
        }

    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) throws SAXException {

        log.debug("End parsing element " + qName);

        Optional<ListName> l = Arrays.stream(ListName.values())
                .filter(listName -> listName.getValue().equals(qName))
                .findAny();

        if (l.isPresent()) {
            list = ListName.NONE;
        } else {
            Optional<EntityName> e = Arrays.stream(EntityName.values())
                    .filter(entityName -> entityName.getValue().equals(qName))
                    .findAny();

            builder.append("</").append(qName).append('>');

            if (e.isPresent()) {

                XmlMapper mapper = new XmlMapper();

                try {
                    switch (e.get()) {
                        case HORSE:
                            if (list.equals(ListName.HORSES)) {
                                horses.add(mapper.readValue(builder.toString(), Horse.class));
                            }
                            break;
                        case RACE:
                            if (list.equals(ListName.RACES)) {
                                races.add(mapper.readValue(builder.toString(), Race.class));
                            }
                            break;
                        case BET:
                            if (list.equals(ListName.BETS)) {
                                bets.add(mapper.readValue(builder.toString(), Bet.class));
                            }
                            break;
                    }
                } catch (JsonProcessingException ex) {
                    log.error(ex.getMessage());
                }

            }

        }

    }
}
