package com.ajrat.xml.parsers;

import com.ajrat.domain.Address;
import com.ajrat.domain.Item;
import com.ajrat.domain.PurchaseOrder;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

@Slf4j
public class JAXB {

    public PurchaseOrder getPurchaseOrder(File file) {
        JAXBContext jaxbContext;
        PurchaseOrder purchaseOrder = null;
        log.info("===== Unmarshaling...");
        try {
            jaxbContext = JAXBContext.newInstance(PurchaseOrder.class, Address.class, Item.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            purchaseOrder = (PurchaseOrder) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            log.info("!!! ===== JAXB JAXBException {}", e.getMessage());
            return new PurchaseOrder();
        }
        log.info("===== ORDER: {}", purchaseOrder);
        return purchaseOrder;
    }

    public void getXML(PurchaseOrder purchaseOrder) {
        JAXBContext context;
        StringWriter writer = new StringWriter();
        log.info("===== Marshaling...");
        try {
            context = JAXBContext.newInstance(PurchaseOrder.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(purchaseOrder, writer);
        } catch (JAXBException e) {
            log.info("!!! ===== JAXB JAXBException {}", e.getMessage());
        }
        log.info("===== XML: {}", writer.toString());
    }
}
