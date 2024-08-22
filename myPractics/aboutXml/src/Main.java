import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        /* DOM представляет XML-документ в виде структуры данных дерева. Каждая нода дерева соответствует элементу,
        атрибуту или текстовому узлу в XML. Весь документ загружается в память. Предпочтителен для небольших и средних
        XML-документов, когда нужно произвольное чтение и изменение данных */

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("prop.xml"));

        Element element = document.getDocumentElement();
        System.out.println(element.getTagName());
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if(nodeList.item(i) instanceof Element){
                System.out.println(((Element) nodeList.item(i)).getTagName());
            }
        }
    }

    static void printElement(NodeList nodeList){

    }
}
