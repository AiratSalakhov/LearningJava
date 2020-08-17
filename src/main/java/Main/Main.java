package Main;


import XMLparsers.DOMparser;
import XMLparsers.JAXB;
import XMLparsers.StAXparser;
import fileUtils.ApacheFUDemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;
import purchaseOrder.PurchaseOrder;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XMLStreamException, JAXBException {
        ApacheFUDemo.demo();
        File file = FileUtils.getFile(
                Main.class.getClassLoader().getResource("order.xml").getPath());
        log.info(file.getAbsolutePath());
        //log.info("Hash of file is: {}", file.getAbsolutePath()); //sha1Hash(file));
        //DOMparser.parse(file);
        //StAXparser.parse(file);
        PurchaseOrder myPurchaseOrder = JAXB.getPurchaseOrder(file);
        log.info("ORDER: {}", myPurchaseOrder);
        log.info("XML: {}", JAXB.getXML(myPurchaseOrder));
    }
}
