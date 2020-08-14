package XMLparsers;

import lombok.extern.slf4j.Slf4j;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.jar.Attributes;

@Slf4j
public class StAXparser {
    public static void parse(File file) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader =
                factory.createXMLEventReader(new FileReader(file));

        while(eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch(event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
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
                    if (stringBuilder.length() > 0 ) {
                        log.info("Attributes: {}", stringBuilder.toString());
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    String text = characters.getData().replace("\n", "").trim();
                    if (text.length() > 0) {
                        log.info("Characters: {}", text);
                    }
                    break;
                case XMLStreamConstants.CDATA:
                    characters = event.asCharacters();
                    text = characters.getData().replace("\n", "").trim();
                    if (text.length() > 0) {
                        log.info("CDATA: {}", text);
                    }
                    break;
                case XMLStreamConstants.COMMENT:
                    characters = event.asCharacters();
                    text = characters.getData().replace("\n", "").trim();
                    if (text.length() > 0) {
                        log.info("COMMENT: {}", text);
                    }
                    break;
            }
        }
    }
}
