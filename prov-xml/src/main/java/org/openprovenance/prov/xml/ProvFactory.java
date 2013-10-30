package org.openprovenance.prov.xml;

import java.util.Hashtable;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.namespace.QName;
import javax.xml.datatype.DatatypeFactory;
import org.openprovenance.prov.model.Attribute.AttributeKind;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/** A stateless factory for PROV objects. */

//TODO: move the QNameExport capability outside the factory, and make it purely stateless, without namespace. 

public class ProvFactory extends org.openprovenance.prov.model.ProvFactory {

    static public DocumentBuilder builder;

    public static final String DEFAULT_NS = "_";

    private final static ProvFactory oFactory = new ProvFactory();

    public static final String packageList = "org.openprovenance.prov.xml:org.openprovenance.prov.xml.validation";

    static {
	initBuilder();
    }

    private static String fileName = "toolbox.properties";
    private static final String toolboxVersion = getPropertiesFromClasspath(fileName).getProperty("toolbox.version");

    public String getVersion() {
        return toolboxVersion;
    }

    private static Properties getPropertiesFromClasspath(String propFileName) {
        Properties props = new Properties();
        InputStream inputStream = ProvFactory.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputStream == null) {
            return null;
        }
        try {
            props.load(inputStream);
        } catch (IOException ee) {
            return null;
        }
        return props;   
    }

    public static ProvFactory getFactory() {
	return oFactory;
    }

    static void initBuilder() {
	try {
	    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	    docBuilderFactory.setNamespaceAware(true);
	    builder = docBuilderFactory.newDocumentBuilder();
	} catch (ParserConfigurationException ex) {
	    throw new RuntimeException(ex);
	}
    }

    public static String printURI(java.net.URI u) {
	return u.toString();
    }

    protected DatatypeFactory dataFactory;
    /** Note, this method now makes it stateful :-( */
    private Hashtable<String, String> namespaces = null;

    final protected org.openprovenance.prov.xml.validation.ObjectFactory vof;

    public ProvFactory() {
	super(new ObjectFactory2());
	vof = new org.openprovenance.prov.xml.validation.ObjectFactory();
	init();
	setNamespaces(new Hashtable<String, String>());
    }

    public ProvFactory(Hashtable<String, String> namespaces) {
	super(new ObjectFactory2(), namespaces);
	vof = new org.openprovenance.prov.xml.validation.ObjectFactory();
	this.namespaces = namespaces;
	init();
    }

    public ProvFactory(ObjectFactory2 of) {
	super(of);
	vof = new org.openprovenance.prov.xml.validation.ObjectFactory();
	init();
	setNamespaces(new Hashtable<String, String>());
    }

  


/*



    public Attribute newAttribute(QName qname, Object value, ValueConverter vconv) {
  	Attribute res = new Attribute(qname, value, vconv.getXsdType(value));
  	return res;
      }



    public Attribute newAttribute(Attribute.AttributeKind kind, Object value, ValueConverter vconv) {
  	Attribute res = new Attribute(kind, value, vconv.getXsdType(value));
  	return res;
      }
    public Attribute newAttribute(Attribute.AttributeKind kind, Object value, QName type) {
  	Attribute res = new Attribute(kind, value, type);
  	return res;
      }

    public Attribute newAttribute(String namespace, String localName,
				  String prefix, Object value, ValueConverter vconv) {
	Attribute res = new Attribute(new QName(namespace, localName, prefix),
	                              value, 
	                              vconv.getXsdType(value));
	return res;
    }
*/
    public org.openprovenance.prov.model.Attribute newAttribute(QName elementName, Object value, QName type) {
	// TODO: use TypedValue.getAttributeKind and switch on a kind
	if (elementName.equals(Attribute.PROV_LOCATION_QNAME)) {
	    return newLocation(value,type);
	}
	if (elementName.equals(Attribute.PROV_TYPE_QNAME)) {
	    return newType(value,type);
	}
	if (elementName.equals(Attribute.PROV_VALUE_QNAME)) {
	    return newValue(value,type);
	}
	if (elementName.equals(Attribute.PROV_ROLE_QNAME)) {
	    return newRole(value,type);
	}
	if (elementName.equals(Attribute.PROV_LABEL_QNAME)) {
	    return newLabel(value,type);
	}
	return newOther(elementName, value, type);
    }
    
    public org.openprovenance.prov.model.Attribute newAttribute(String namespace, String localName,
                                                                String prefix, Object value, QName type) {

        return newAttribute(new QName(namespace, localName, prefix),
                            value, 
                            type);
    }
    
    public Location newLocation(Object value, QName type) {
        Location loc=new Location();
        loc.type=type;
        loc.setValueAsObject(value);
        return loc;
    }
    public Type newType(Object value, QName type) {
        Type typ=new Type();
        typ.type=type;
        typ.setValueAsObject(value);
        return typ;
    }
    public Value newValue(Object value, QName type) {
        Value res=new Value();
        res.type=type;
        res.setValueAsObject(value);
        return res;
    }
    public Role newRole(Object value, QName type) {
        Role res=new Role();
        res.type=type;
        res.setValueAsObject(value);
        return res;
    }
    public Label newLabel(Object value, QName type) {
        Label res=new Label();
        res.type=type;
        res.setValueAsObject(value);
        return res;
    }
    public OtherAttribute newOther(QName elementName, Object value, QName type) {
        OtherAttribute res=new OtherAttribute();
        res.type=type;
        res.setValueAsObject(value);
        res.setElementName(elementName);
        return res;
    }

    
    @Override
    public org.openprovenance.prov.model.Attribute createAttribute(QName qname,
							    Object value,
							    QName type) {
	return new Attribute(qname,value,type);
    }

    @Override
    public org.openprovenance.prov.model.Attribute createAttribute(AttributeKind kind,
                                                                   Object value,
                                                                   QName type) {
	return new Attribute(kind,value,type);

    }
    

    @Override
    public org.openprovenance.prov.model.IDRef createIDRef() {
	return new IDRef();
    }

    public org.openprovenance.prov.xml.validation.ObjectFactory getValidationObjectFactory() {
        return vof;
    }

}
