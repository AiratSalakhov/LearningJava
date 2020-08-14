package XMLparsers;

import purchaseOrder.Address;
import purchaseOrder.Item;
import purchaseOrder.PurchaseOrder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXB {
    public static PurchaseOrder getPurchaseOrder(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseOrder.class, Address.class, Item.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (PurchaseOrder) jaxbUnmarshaller.unmarshal(file);

    }

    public static String getXML(PurchaseOrder purchaseOrder) {
        return "";
    }
}
