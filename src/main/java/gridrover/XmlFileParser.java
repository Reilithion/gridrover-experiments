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

import org.apache.commons.digester.*;

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

	public List<ThingBean> getThings(String xmlFileName, List<Spectrum> spectra) {
		return loadThings(resourceLocater.getResource(xmlFileName), spectra);
	}
    
    public List<Spectrum> getSpectra(String xmlFileName) {
        return loadSpectra(resourceLocater.getResource(xmlFileName));
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

     private List<ThingBean> loadThings(InputStream input, List<Spectrum> spectra) {
     	List<ThingBean> thingBeans = null;
     	Digester digester = new Digester();
     	digester.setValidating(false);
     	digester.addObjectCreate("objects", "java.util.ArrayList");
     	digester.addObjectCreate("objects/thing", "gridrover.ThingBean");
     	digester.addSetProperties("objects/thing");
     	digester.addObjectCreate("objects/thing/spectroscopy/appearance", "gridrover.AppearanceBean");
     	digester.addSetProperties("objects/thing/spectroscopy/appearance");
     	digester.addObjectCreate("objects/thing/spectroscopy/appearance/response", "gridrover.ResponseBean");
     	digester.addSetProperties("objects/thing/spectroscopy/appearance/response");
     	digester.addSetNext("objects/thing/spectroscopy/appearance/response", "addResponseBean", "gridrover.ResponseBean");
     	digester.addSetNext("objects/thing/spectroscopy/appearance", "addAppearanceBean", "gridrover.AppearanceBean");
     	digester.addSetNext("objects/thing", "add", "gridrover.ThingBean");
     	try
     	{
     		thingBeans = (List<ThingBean>) digester.parse(input);
     		input.close();
     	}
     	catch (Exception e)
     	{
     		Debug.debug(e.toString());
     		return null;
     	}
     	return thingBeans;
     }

     private List<Spectrum> loadSpectra(InputStream input) {
         ArrayList<SpectrumBean> spectrumBeans = null;
         Digester digester = new Digester();
         digester.setValidating(false);
         digester.addObjectCreate("spectra", "java.util.ArrayList");
         digester.addObjectCreate("spectra/spectrum", "gridrover.SpectrumBean");
         digester.addSetProperties("spectra/spectrum");
         digester.addCallMethod("spectra/spectrum/color", "addColor", 0);
         digester.addCallMethod("spectra/spectrum/shape", "addShape", 0);
         digester.addSetNext("spectra/spectrum", "add", "gridrover.SpectrumBean");
         try
         {
         	spectrumBeans = (ArrayList<SpectrumBean>) digester.parse(input);
         	input.close();
         }
         catch (Exception e)
         {
         	Debug.debug(e.toString());
         	return null;
         }
         ArrayList<Spectrum> spectra = new ArrayList<Spectrum>();
         for (SpectrumBean sb : spectrumBeans)
         {
         	spectra.add(new Spectrum(sb));
         }
         return spectra;
     }
}
