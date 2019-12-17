package com.kyntsevichvova.wtlab.parser.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kyntsevichvova.wtlab.bean.Bet;
import com.kyntsevichvova.wtlab.bean.Horse;
import com.kyntsevichvova.wtlab.bean.Race;
import com.kyntsevichvova.wtlab.model.Model;
import com.kyntsevichvova.wtlab.parser.ModelParser;
import com.kyntsevichvova.wtlab.parser.enums.ListName;
import com.kyntsevichvova.wtlab.parser.exception.ParserException;
import lombok.extern.log4j.Log4j2;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;

@Log4j2
public class DomParser extends ModelParser {
    @Override
    public void parse(Model model) throws ParserException {
        log.debug("Parsing with DOM");

        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(model.getXml().getBytes()));

            document.normalize();

            Node list;
            NodeList nodes;
            XmlMapper mapper = new XmlMapper();

            list = document.getElementsByTagName(ListName.HORSES.getValue()).item(0);
            nodes = list.getChildNodes();

            StringBuilder builder;

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                builder = new StringBuilder();

                builder.append("<").append(node.getNodeName()).append(">");

                NodeList children = node.getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if (child.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    builder.append("<").append(child.getNodeName()).append(">");
                    builder.append(child.getTextContent());
                    builder.append("</").append(child.getNodeName()).append(">");
                }

                builder.append("</").append(node.getNodeName()).append(">");

                log.debug("Parsing: " + builder.toString());
                horses.add(mapper.readValue(new StringReader(builder.toString()), Horse.class));
            }

            list = document.getElementsByTagName(ListName.RACES.getValue()).item(0);
            nodes = list.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                builder = new StringBuilder();

                builder.append("<").append(node.getNodeName()).append(">");

                NodeList children = node.getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if (child.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    builder.append("<").append(child.getNodeName()).append(">");
                    builder.append(child.getTextContent());
                    builder.append("</").append(child.getNodeName()).append(">");
                }

                builder.append("</").append(node.getNodeName()).append(">");

                log.debug("Parsing: " + builder.toString());
                races.add(mapper.readValue(new StringReader(builder.toString()), Race.class));
            }

            list = document.getElementsByTagName(ListName.BETS.getValue()).item(0);
            nodes = list.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                builder = new StringBuilder();

                builder.append("<").append(node.getNodeName()).append(">");

                NodeList children = node.getChildNodes();

                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if (child.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    builder.append("<").append(child.getNodeName()).append(">");
                    builder.append(child.getTextContent());
                    builder.append("</").append(child.getNodeName()).append(">");
                }

                builder.append("</").append(node.getNodeName()).append(">");

                log.debug("Parsing: " + builder.toString());
                bets.add(mapper.readValue(new StringReader(builder.toString()), Bet.class));
            }

        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new ParserException(e);
        }
    }
}
