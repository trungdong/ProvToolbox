//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.05 at 11:52:42 PM GMT 
//


package org.openprovenance.prov.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

import org.openprovenance.prov.model.DOMProcessing;


/**
 * @author lavm
 *
 */
public class AnyAdapter
    extends XmlAdapter<Object,org.openprovenance.prov.model.Attribute>
{

    ProvFactory pFactory=new ProvFactory();
    
    DOMProcessing dom=new DOMProcessing();
    
    ValueConverter vconv=new ValueConverter(pFactory);
    

    /** Converts a string to a QName, extracting namespace from the DOM.
     * @param str string to convert to QName
     * @param el current Element in which this string was found (as attribute or attribute value)
     * @return
     */
    public QName stringToQName(String str, org.w3c.dom.Element el) {
        if (str == null) return null;
        int index = str.indexOf(':');
        if (index == -1) {
            QName qn= new QName(el.lookupNamespaceURI(null),  // find default namespace
                                str);
            return qn;
        }
        String prefix = str.substring(0, index);
        String local = str.substring(index + 1, str.length());
        QName qn= new QName(el.lookupNamespaceURI(prefix),
                            local,
                            prefix);
        return qn;
    } 


    //TODO: unmarshalling, to create subclass of Attributes according to prov type.

   
    public org.openprovenance.prov.model.Attribute unmarshallAttribute(org.w3c.dom.Element el) {
	String prefix=el.getPrefix();
	String namespace=el.getNamespaceURI();
	String local=el.getLocalName();
	String child=el.getTextContent();
	String typeAsString=el.getAttributeNS(NamespacePrefixMapper.XSI_NS, "type");
	String lang=el.getAttributeNS(NamespacePrefixMapper.XML_NS, "lang");
	QName type=((typeAsString==null) || (typeAsString.equals(""))) ? null : stringToQName(typeAsString, el);
	if (type==null) type=ValueConverter.QNAME_XSD_STRING;
	if (type.equals(ValueConverter.QNAME_XSD_QNAME)) {
	    QName qn=stringToQName(child,el);
	    return pFactory.newAttribute(namespace,local,prefix, qn, type);
	} else if ((lang==null) || (lang.equals(""))) {
	    return pFactory.newAttribute(namespace,local,prefix, vconv.convertToJava(type, child), type);
	} else {
	    return pFactory.newAttribute(namespace,local,prefix, pFactory.newInternationalizedString(child,lang), type);
	}
    }
    

    public org.openprovenance.prov.model.Attribute unmarshal(Object value) {
        //System.out.println("AnyAdapter unmarshalling for " + value);
        if (value instanceof org.w3c.dom.Element) {
            org.w3c.dom.Element el=(org.w3c.dom.Element)value;
            return unmarshallAttribute(el);
        } 
        if (value instanceof JAXBElement) {
            JAXBElement<?> je=(JAXBElement<?>) value;
            return pFactory.newAttribute(je.getName(),je.getValue(),vconv);
        }
        return null;
    }

    
    public Object marshal(org.openprovenance.prov.model.Attribute attribute) {
        //System.out.println("AnyAdapter marshalling for " + attribute);
        //System.out.println("AnyAdapter2 marshalling for " + attribute
        //                .getClass());
        //TODO: this call creates a DOM but does not encode the type as xsi:type
	Object value=attribute.getValue();
	if (value instanceof InternationalizedString) {
	    InternationalizedString istring=((InternationalizedString)value);
	    return dom.newElement(attribute.getElementName(), 
				       istring.getValue(),
				       attribute.getType(),
				       istring.getLang());
	} else if (value instanceof QName) {
            return dom.newElement(attribute.getElementName(), 
                                       (QName)value);

	} else {
	    return dom.newElement(attribute.getElementName(), 
				       value.toString(),
				       attribute.getType());
	}
        //JAXBElement<?> je=new JAXBElement(value.getElementName(),value.getValue().getClass(),value.getValue());
        //return je;
    }

    static AnyAdapter me=new AnyAdapter();
    
    static org.openprovenance.prov.model.Attribute parseMethod(Object o) {
	return me.unmarshal(o);
    }
    
    static Object printMethod(org.openprovenance.prov.model.Attribute a) {
	return me.marshal(a);
    }

}
