package purchaseOrder;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "PurchaseOrder")
@XmlRootElement(name = "PurchaseOrder")
public class PurchaseOrder {
    @XmlAttribute(name = "PurchaseOrderNumber")
    public int orderNumber;
    @XmlAttribute(name = "OrderDate")
    public String orderDate;

    @XmlElement(name = "Address")
    public List<Address> addressList = new ArrayList<>();

    @XmlElement(name = "DeliveryNotes")
    public String notes;

    @XmlElementWrapper(name = "Items", nillable = true)
    @XmlElement(name = "Item")
    public List<Item> items = new ArrayList<>();

    public PurchaseOrder() {
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "orderNumber=" + orderNumber +
                ", orderDate='" + orderDate + '\'' +
                ", addressList=" + addressList +
                ", notes='" + notes + '\'' +
                ", items=" + items +
                '}';
    }
}