package purchaseOrder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Address")
public class Address {
    @XmlAttribute(name = "Type")
    public String type;
    @XmlElement(name = "Name")
    public String name;
    @XmlElement(name = "Street")
    public String street;
    @XmlElement(name = "City")
    public String city;
    @XmlElement(name = "State")
    public String state;
    @XmlElement(name = "Zip")
    public int zip;
    @XmlElement(name = "Country")
    public String country;

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", country='" + country + '\'' +
                '}';
    }
}