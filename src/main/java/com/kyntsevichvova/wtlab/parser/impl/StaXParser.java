package com.kyntsevichvova.wtlab.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kyntsevichvova.wtlab.bean.Bet;
import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.bean.Race;
import com.kyntsevichvova.wtlab.model.Model;
import com.kyntsevichvova.wtlab.parser.ModelParser;
import com.kyntsevichvova.wtlab.parser.enums.EntityName;
import com.kyntsevichvova.wtlab.parser.enums.ListName;
import com.kyntsevichvova.wtlab.parser.exception.ParserException;
import lombok.extern.log4j.Log4j2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Optional;

@Log4j2
public class StaXParser extends ModelParser {
    @Override
    public void parse(Model model) throws ParserException {
        try {
            XMLStreamReader reader = XMLInputFactory
                    .newInstance()
                    .createXMLStreamReader(new ByteArrayInputStream(model.getXml().getBytes()));

            ListName list = ListName.NONE;
            StringBuilder builder = new StringBuilder();

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLEvent.START_ELEMENT: {
                        String qName = reader.getLocalName();
                        String text = null;
                        try {
                            text = reader.getElementText();
                            log.debug("text: " + text);
                        } catch (XMLStreamException ignored) {}
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

                            if (text != null) {
                                builder.append(text);
                            }

                        }
                        break;
                    }
                    case XMLEvent.END_ELEMENT: {
                        String qName = reader.getLocalName();
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

                                log.debug("Mapping: " + builder.toString());

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
                                    throw new ParserException(ex);
                                }
                            }
                        }
                        break;
                    }
                    /*case XMLEvent.CHARACTERS:
                        log.debug("Chars: " + new String(reader.getTextCharacters()));
                        builder.append(reader.getTextCharacters());
                        break;*/
                }
            }

        } catch (XMLStreamException e) {
            throw new ParserException(e);
        }
    }
}
