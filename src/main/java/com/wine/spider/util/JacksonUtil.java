package com.wine.spider.util;

import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/6/12 Time: 4:51 PM To change this template use File | Settings |
 * File Templates.
 */
public class JacksonUtil {

    private final static Logger logger       = Logger.getLogger(JacksonUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JacksonUtil(){
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    @SuppressWarnings("unchecked")
    public static <T> T toObject(String content, TypeReference<T> valueTypeRef) {
        if (StringUtils.isBlank(content) || valueTypeRef == null) return null;
        try {
            return (T) getObjectMapper().readValue(content, valueTypeRef);
        } catch (Exception e) {
            String msg = "stringToObject error!check the json!";
            logger.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    /**
     * 将对象转换成Json字符串
     * 
     * @param value 非空
     * @return
     */
    public static String toJson(Object value) {
        if (value == null) return null;
        Writer writer = new StringWriter();
        try {
            getObjectMapper().writeValue(writer, value);
            return writer.toString();
        } catch (Exception e) {
            String msg = "objectToString error!value:" + value;
            logger.error(msg, e);
            throw new RuntimeException(msg, e);
        } finally {
            WriterUtil.clossWriter(writer);
        }
    }

}
