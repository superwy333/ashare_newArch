package com.zynsun.openplatform.dubbo.extension;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.jaxrs.Annotations;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @Author tanyx
 * @Date 2017/7/2822:52.
 */
@Provider
@Consumes({"application/json", "text/json"})
@Produces({"application/json", "text/json"})
public class DubboJacksonJsonProvider extends JacksonJsonProvider {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public DubboJacksonJsonProvider() {
        super(new Annotations[]{Annotations.JACKSON, Annotations.JAXB});
    }

    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException {
        ObjectMapper mapper = this.locateMapper(type, mediaType);
        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(JsonMethod.GETTER, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(JsonMethod.SETTER, JsonAutoDetect.Visibility.NONE);

        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

        JsonParser jp = mapper.getJsonFactory().createJsonParser(entityStream);
        jp.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return mapper.readValue(jp, mapper.constructType(genericType));
    }
}