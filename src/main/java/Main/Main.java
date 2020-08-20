package Main;


import XMLparsers.JAXB;
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
        myPurchaseOrder.items.stream()
                .forEach((x) -> System.out.println(x));
        System.out.println("=== sort by price ==========");
        myPurchaseOrder.items.stream()
                .sorted((a, b) -> (int) (a.price - b.price))
                .forEach((x) -> System.out.println(x));
        System.out.println("===filter by price gt 100 ==========");
        myPurchaseOrder.items.stream()
                .filter((x) -> x.price > 100.0)
                .forEach((x) -> System.out.println(x));
        System.out.println("== if price gt 100 set qty to 5 (map)===========");
        myPurchaseOrder.items.stream()
                .filter((x) -> x.price > 100.0)
                .map(x -> {
                    x.quantity = 5;
                    return x;
                })
                .forEach((x) -> System.out.println(x));
    }
}
