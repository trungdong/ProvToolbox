package org.openprovenance.prov.core.xml;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.openprovenance.prov.core.vanilla.*;
import org.openprovenance.prov.core.xml.serialization.Constants;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.StatementOrBundle;

import java.util.List;

@JsonPropertyOrder({ "context", "statements"})
@JacksonXmlRootElement(localName="document", namespace="http://www.w3.org/ns/prov#")
public interface Document {
    @JsonFilter("nsFilter")
    @JsonProperty("context")
    @JacksonXmlProperty(localName="context",namespace="http://www.w3.org/ns/prov#")
    @JsonIgnore
    Namespace getNamespace();

    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT, property = Constants.PROPERTY_BLOCK_TYPE)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = WasEndedBy.class,         name = Constants.PROPERTY_PROV_END),
            @JsonSubTypes.Type(value = WasStartedBy.class,       name = Constants.PROPERTY_PROV_START),
            @JsonSubTypes.Type(value = WasInvalidatedBy.class,   name = Constants.PROPERTY_PROV_INVALIDATION),
            @JsonSubTypes.Type(value = HadMember.class,          name = Constants.PROPERTY_PROV_MEMBERSHIP),
            @JsonSubTypes.Type(value = WasInfluencedBy.class,    name = Constants.PROPERTY_PROV_INFLUENCE),
            @JsonSubTypes.Type(value = WasInformedBy.class,      name = Constants.PROPERTY_PROV_COMMUNICATION),
            @JsonSubTypes.Type(value = WasDerivedFrom.class,     name = Constants.PROPERTY_PROV_DERIVATION),
            @JsonSubTypes.Type(value = AlternateOf.class,        name = Constants.PROPERTY_PROV_ALTERNATE),
            @JsonSubTypes.Type(value = SpecializationOf.class,   name = Constants.PROPERTY_PROV_SPECIALIZATION),
            @JsonSubTypes.Type(value = WasAttributedTo.class,    name = Constants.PROPERTY_PROV_ATTRIBUTION),
            @JsonSubTypes.Type(value = WasAssociatedWith.class,  name = Constants.PROPERTY_PROV_ASSOCIATION),
            @JsonSubTypes.Type(value = WasGeneratedBy.class,     name = Constants.PROPERTY_PROV_GENERATION),
            @JsonSubTypes.Type(value = Used.class,               name = Constants.PROPERTY_PROV_USED),
            @JsonSubTypes.Type(value = Activity.class,           name = Constants.PROPERTY_PROV_ACTIVITY),
            @JsonSubTypes.Type(value = Agent.class,              name = Constants.PROPERTY_PROV_AGENT),
            @JsonSubTypes.Type(value = Entity.class,             name = Constants.PROPERTY_PROV_ENTITY),
            @JsonSubTypes.Type(value = ActedOnBehalfOf.class,    name = Constants.PROPERTY_PROV_DELEGATION)
    })
    @JsonProperty("statements")
    @JacksonXmlProperty(localName="statements",namespace="http://www.w3.org/ns/prov#")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<StatementOrBundle> getStatementOrBundle();
}
