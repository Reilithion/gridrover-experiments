package gridrover;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This is the XML file parser for GridRover.  Using Extractors it will extract desired information from a given xml file.
 */
public class XmlFileParser {
    private ResourceLocater resourceLocater;
    
    /**
     * Constructor
     * @param resourceLocater The ResourceLocater to use when getting the XML file to parse.
     */
    XmlFileParser(ResourceLocater resourceLocater) {
        this.resourceLocater = resourceLocater;
    }

    public List<Thing> getThings(String xmlFileName) {
        return loadItems(setupDocument(xmlFileName));
    }
    
    public List<Spectrum> getSpectrum(String xmlFileName) {
        return loadSpectrum(setupDocument(xmlFileName));
    }
    
    private Document setupDocument(String xmlFileName) {
        InputStream resourceStream = resourceLocater.getResource(xmlFileName);
        Document doc = null;
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(resourceStream);
        }
        catch (ParserConfigurationException e)
        {
            System.out.println("ParserConfigurationException while trying to get a builder.");
            System.out.println(e.toString());
        }
        catch (SAXException e)
        {
            System.out.println("SAXException while trying to parse xml.");
            System.out.println(e.toString());
        }
        catch (IOException ioe) {
            System.out.println("IOException while trying to parse xml.");
            System.out.println("Tried to open: " + resourceStream.toString());
            System.out.println(ioe.toString());            
        }
        return doc;
    }
    
     private List<Thing> loadItems(Document doc) {
         List<Thing> retVal = new ArrayList<Thing>();
         if (doc != null) {
            NodeList thingList = doc.getDocumentElement().getChildNodes();
            int numberOfItems = thingList.getLength();
            Debug.debug("Number of Items to read from file: " + numberOfItems);
            for (int i = 0; i < numberOfItems; i++) {
                Node thingNode = thingList.item(i);
                if (thingNode.getNodeType() == Node.ELEMENT_NODE
                        && ((Element) thingNode).getTagName().equalsIgnoreCase("THING")) {
                    String thingName = null;
                    double thingMass = -1.0;
                    double thingBulk = -1.0;
                    NodeList thingPropertyList = thingNode.getChildNodes();
                    for (int j = 0; j < thingPropertyList.getLength(); j++) {
                        Node propertyNode = thingPropertyList.item(j);
                        if (propertyNode.getNodeName().equalsIgnoreCase("NAME"))
                            thingName = propertyNode.getTextContent();
                        if (propertyNode.getNodeName().equalsIgnoreCase("MASS"))
                            thingMass = Double.parseDouble(propertyNode
                                    .getTextContent());
                        if (propertyNode.getNodeName().equalsIgnoreCase("BULK"))
                            thingBulk = Double.parseDouble(propertyNode
                                    .getTextContent());
                    }
                    Thing thing = new Thing(thingName, thingMass, thingBulk);
                    retVal.add(thing);
                }
            }
        }
        return retVal;
     }
     
     private List<Spectrum> loadSpectrum(Document doc) {
         return new ArrayList<Spectrum>();
     }
}
