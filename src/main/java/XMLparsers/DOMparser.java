package XMLparsers;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Slf4j
public class DOMparser {
    public static void parse(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        Node root = document.getDocumentElement();
        log.info("Root");
        getNodes(root);
    }

    private static void getNodes(Node node) {
        log.info("Element {}", node.getNodeName());
        StringBuilder stringBuilder = getAttributes(node);
        if (stringBuilder.length() > 0) {
            log.info("Attributes: {}", stringBuilder.toString());
        }
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node1 = nodeList.item(i);
            switch (node1.getNodeType()) {
                case Node.ELEMENT_NODE:
                    getNodes(node1);
                    break;
                case Node.TEXT_NODE:
                    String text = node1.getTextContent().replace("\n", "").trim();
                    if (text.length() > 0) {
                        log.info("Text: {}", text);
                    }
                    break;
                case Node.CDATA_SECTION_NODE:
                    log.info("CDATA: {}", node1.getTextContent());
                    break;
                case Node.COMMENT_NODE:
                    log.info("Comment: {}", node1.getTextContent());
                    break;
                default:
                    log.info("OTHER({}): {}", node1.getNodeName(), node1.getNodeValue());
            }
        }
    }

    private static StringBuilder getAttributes(Node node) {
        StringBuilder stringBuilder = new StringBuilder();
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            stringBuilder.append(nodeMap.item(i).getNodeName())
                    .append("=\"")
                    .append(nodeMap.item(i).getNodeValue())
                    .append("\" ");
        }
        return stringBuilder;
    }
}
