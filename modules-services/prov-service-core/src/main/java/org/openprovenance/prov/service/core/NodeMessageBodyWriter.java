package org.openprovenance.prov.service.core;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.openprovenance.prov.interop.InteropMediaType;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces({ InteropMediaType.MEDIA_APPLICATION_JSON })
public class NodeMessageBodyWriter implements MessageBodyWriter<ObjectNode> {
    
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    public String trimCharSet(MediaType mediaType) {
        String med=mediaType.toString();
        int ind=med.indexOf(";");
        if (ind>0) med=med.substring(0,ind);
        return med;
    }

    public NodeMessageBodyWriter () {
        System.out.println("*********** Node MessageBodyWriter  ************");
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
    		Annotation[] annotations, MediaType mediaType) {
    	return true;
    }
    
    
    

    @Override
    public long getSize(ObjectNode t, Class<?> type, Type genericType,
    		Annotation[] annotations, MediaType mediaType) {
    	return -1;
    }


    @Override
    public void writeTo(ObjectNode node,
                        Class<?> arg1,
                        Type arg2,
                        Annotation[] arg3,
                        MediaType arg4,
                        MultivaluedMap<String, Object> arg5,
                        OutputStream out) throws IOException, WebApplicationException {

        writer.writeValue(out,node);
        
        
    }

}
