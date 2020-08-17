package purchaseOrder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Item")
public class Item {
    @XmlAttribute(name = "PartNumber")
    public String partNumber;
    @XmlElement(name = "ProductName")
    public String name;
    @XmlElement(name = "Quantity")
    public int quantity;
    @XmlElement(name = "USPrice")
    public float price;
    @XmlElement(name = "ShipDate")
    public String shipDate;
    @XmlElement(name = "Comment")
    public String comment;

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "partNumber='" + partNumber + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", shipDate='" + shipDate + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
