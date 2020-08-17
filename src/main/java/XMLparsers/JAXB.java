package XMLparsers;

import purchaseOrder.Address;
import purchaseOrder.Item;
import purchaseOrder.PurchaseOrder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

public class JAXB {

    public static PurchaseOrder getPurchaseOrder(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseOrder.class, Address.class, Item.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (PurchaseOrder) jaxbUnmarshaller.unmarshal(file);

    }

    public static String getXML(PurchaseOrder purchaseOrder) throws JAXBException {
        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(purchaseOrder, writer);
        return writer.toString();
    }
}
