package eg.gov.iti.contract.ui.helpers;

import eg.gov.iti.contract.ui.models.UserMessageModel;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaveSession {

    public static List<UserMessageModel> userMessageModelList = new ArrayList<>();

    private static Document createEmptyDocument () throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.newDocument();
    }

    private static void populateDocumentWithDate (Document document, List<UserMessageModel> userMessageModels)
    {
        Element messages = document.createElement("messages");
        userMessageModels.stream().forEach(message->{
            Element message1 = createMessage(document, message.getSenderName(), message.getReceiverPhoneNumber(), message.getMessageBody(), "en");
            messages.appendChild(message1);
        });
        document.appendChild(messages);
    }

    private static Element createMessage (Document document, String from, String to, String body, String lang)
    {
        Element messageElement = document.createElement("messages");
        messageElement.setAttribute("date", LocalDate.now().toString());

        Element fromElement = document.createElement("from");
        fromElement.setTextContent(from);
        messageElement.appendChild(fromElement);

        Element toElement = document.createElement("to");
        toElement.setTextContent(to);
        messageElement.appendChild(toElement);

        Element bodyElement = document.createElement("body");
        bodyElement.setAttribute("lang", lang);
        bodyElement.setTextContent(body);
        messageElement.appendChild(bodyElement);
        return messageElement;

    }

    private static void printDocumentContents (Document document)
    {
        System.out.println("traverse document tree Recursively :");
        Element root = document.getDocumentElement();
        traverseDomTreeRecursively(root);

    }

    private static void traverseDomTreeRecursively (Node parentNode)
    {
        //print node's Node & value
        String nodeType = getNodeType(parentNode.getNodeType());
        System.out.println("\nNode: " + parentNode.getNodeName() + " , Type: " + nodeType + ",value: " + parentNode.getNodeValue());
        //print node
        NamedNodeMap nodeMap = parentNode.getAttributes();
        if (nodeMap != null) {
            for (int i = 0; i < nodeMap.getLength(); i++) {
                Node node = nodeMap.item(i);
                System.out.println("attribute: " + node.getNodeName() + ",Value:" + node.getNodeValue());
            }
        }

        NodeList childNodes = parentNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            traverseDomTreeRecursively(node);
        }
    }

    private static String getNodeType (Short nodeType)
    {
        switch (nodeType) {
            case Node.ELEMENT_NODE:
                return "ELEMENT";
            case Node.ATTRIBUTE_NODE:
                return "ATTRIBUTE";
            case Node.TEXT_NODE:
                return "TEXT";
            case Node.CDATA_SECTION_NODE:
                return "CDATA_SECTION";
            case Node.ENTITY_NODE:
                return "ENTITY";
            case Node.ENTITY_REFERENCE_NODE:
                return "ENTITY_REFERENCE";
            case Node.PROCESSING_INSTRUCTION_NODE:
                return "PROCESSING_INSTRUCTION";
            case Node.COMMENT_NODE:
                return "COMMENT";
            case Node.DOCUMENT_NODE:
                return "DOCUMENT";
            case Node.DOCUMENT_FRAGMENT_NODE:
                return "DOCUMENT_FRAGMENT";
            case Node.DOCUMENT_TYPE_NODE:
                return "DOCUMENT_TYPE";
            default:
                return "defaultValue";
        }
    }

    public static void saveSession(List<UserMessageModel> userMessageModels){
        try {
            Document document = createEmptyDocument();
            populateDocumentWithDate(document,userMessageModels);
            printDocumentContents(document);
            genderedHTMLUsingTraxAPI(document);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    // Generate HTML From Dom source

    private static void genderedHTMLUsingTraxAPI(Document domSource) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        DOMSource xmlSource = new DOMSource(domSource);

        TransformerFactory factory = TransformerFactory.newInstance();
        documentFactory.setNamespaceAware(true);

        DocumentBuilder builder = documentFactory.newDocumentBuilder();
        Document document1 = builder.parse(new File("chat-style.xsl"));
        DOMSource xsltSource = new DOMSource(document1);

        StreamResult result = new StreamResult(new File("ChatSession.html"));
//        Transformer transformer = factory.newTransformer();
        Transformer transformer = factory.newTransformer(xsltSource);
        transformer.transform(xmlSource,result);

    }

}
