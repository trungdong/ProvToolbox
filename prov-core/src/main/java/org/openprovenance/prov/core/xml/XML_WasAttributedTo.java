package org.openprovenance.prov.core.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.openprovenance.prov.model.NamespacePrefixMapper;
import org.openprovenance.prov.model.QualifiedName;

@JsonPropertyOrder({ "@id", "entity", "agent"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface XML_WasAttributedTo extends XML_Generic2 {

    @REF_Qualified_Name
    @JacksonXmlProperty( namespace = NamespacePrefixMapper.PROV_NS)
    public QualifiedName getAgent();

    @REF_Qualified_Name
    @JacksonXmlProperty( namespace = NamespacePrefixMapper.PROV_NS)
    public QualifiedName getEntity();


}
