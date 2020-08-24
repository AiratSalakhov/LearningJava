package Main;


import XMLparsers.DOMparser;
import XMLparsers.JAXB;
import XMLparsers.StAXparser;
import fileUtils.ApacheFUDemo;
import fileUtils.SpringFUDemo;
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
        log.info("===== Processing file {}", file.getAbsolutePath());
        log.info("===== Hash of file {} is: {}", file.getName(), SpringFUDemo.getHash(file));
        DOMparser.parse(file);
        StAXparser.parse(file);
        PurchaseOrder myPurchaseOrder = JAXB.getPurchaseOrder(file);
        log.info("===== ORDER: {}", myPurchaseOrder);
        log.info("===== XML: {}", JAXB.getXML(myPurchaseOrder));
        log.info("===== Original items ==========");
        myPurchaseOrder.items.stream()
                .forEach((x) -> log.info(String.valueOf(x)));
        log.info("===== Sort by price ==========");
        myPurchaseOrder.items.stream()
                .sorted((a, b) -> (int) (a.price - b.price))
                .forEach((x) -> log.info(String.valueOf(x)));
        log.info("===== Filter by price gt 100 ==========");
        myPurchaseOrder.items.stream()
                .filter((x) -> x.price > 100.0)
                .forEach((x) -> log.info(String.valueOf(x)));
        log.info("===== If price gt 100 set qty to 5 (map)===========");
        myPurchaseOrder.items.stream()
                .filter((x) -> x.price > 100.0)
                .map(x -> {
                    x.quantity = 5;
                    return x;
                })
                .forEach((x) -> log.info(String.valueOf(x)));
    }
}
