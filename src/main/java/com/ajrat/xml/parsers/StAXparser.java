package com.ajrat.xml.parsers;

import lombok.extern.slf4j.Slf4j;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

@Slf4j
public class StAXparser {
    private static XMLEvent event;

    private static void processStartElement() {
        StartElement startElement = event.asStartElement();
        log.info("Element {}", startElement.getName().getLocalPart());
        Iterator<Attribute> iterator = startElement.getAttributes();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()) {
            Attribute attribute = iterator.next();
            stringBuilder.append(attribute.getName().getLocalPart())
                    .append("=\"")
                    .append(attribute.getValue())
                    .append("\" ");
        }
        if (stringBuilder.length() > 0) {
            log.info("Attributes: {}", stringBuilder.toString());
        }
    }

    private static void processEndElement() {
        EndElement endElement = event.asEndElement();
        log.info("End element {}", endElement.getName().getLocalPart());
    }

    private static void processStartDocument() {
        log.info("Start document {}", event.toString());
    }

    private static void processEndDocument() {
        log.info("End document {}", event.toString());
    }

    private static void processCharacters(String elementType) {
        Characters characters = event.asCharacters();
        String text = characters.getData().replace("\n", "").trim();
        if (text.length() > 0) {
            log.info("{}: {}", elementType, text);
        }
    }

    public static void parse(File file) {
        log.info("===== StAX parsing...");
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader;
        try {
            eventReader = factory.createXMLEventReader(new FileReader(file));
        } catch (XMLStreamException e) {
            log.info("!!! ===== StAXparser XMLStreamException {}", e.getMessage());
            return;
        } catch (FileNotFoundException e) {
            log.info("!!! ===== StAXparser FileNotFoundException {}", e.getMessage());
            return;
        }
        Map<Integer, Consumer<Integer>> hashMap = new HashMap<>();
        hashMap.put(XMLStreamConstants.START_ELEMENT, x -> StAXparser.processStartElement());
        hashMap.put(XMLStreamConstants.CHARACTERS, x -> StAXparser.processCharacters("Characters"));
        hashMap.put(XMLStreamConstants.CDATA, x -> StAXparser.processCharacters("CDATA"));
        hashMap.put(XMLStreamConstants.COMMENT, x -> StAXparser.processCharacters("COMMENT"));
        hashMap.put(XMLStreamConstants.END_ELEMENT, x -> StAXparser.processEndElement());
        hashMap.put(XMLStreamConstants.START_DOCUMENT, x -> StAXparser.processStartDocument());
        hashMap.put(XMLStreamConstants.END_DOCUMENT, x -> StAXparser.processEndDocument());
        while (eventReader.hasNext()) {
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                log.info("!!! ===== StAXparser XMLStreamException {}", e.getMessage());
                return;
            }
            if (hashMap.containsKey(event.getEventType())) {
                hashMap.get(event.getEventType()).accept(event.getEventType());
            } else {
                log.info("!!! ===== StAXparser unknown EventType {}", event.getEventType());
            }
        }
    }
}
