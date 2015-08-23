package com.saintdan.util.commons.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Some useful json utilities base on jackson.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 4/9/15
 * @since JDK1.8
 */
public final class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

    /**
     * Object convert to json string.
     * @param obj javabean, list, array
     * @return    json string
     * @throws Exception
     */
    public static String obj2json(Object obj) throws Exception{
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Json string convert to javabean.
     * @param jsonStr json string
     * @param clazz   T class
     * @param <T>     type T
     * @return        javabean
     * @throws Exception
     */
    public static <T> T json2pojo(String jsonStr,Class<T> clazz) throws Exception{
        return objectMapper.readValue(jsonStr, clazz);
    }

    /**
     * Json string convert to map default.
     * @param jsonStr json string
     * @param <T>     type T
     * @return        Map
     * @throws Exception
     */
    public static <T> Map<String,Object> json2map(String jsonStr)throws Exception{
        return objectMapper.readValue(jsonStr, Map.class);
    }

    /**
     * Json string convert to map with Class<T>.
     * @param jsonStr json string
     * @param clazz   T class
     * @param <T>     type T
     * @return        Map
     * @throws Exception
     */
    public static <T> Map<String,T> json2map(String jsonStr,Class<T> clazz)throws Exception{
        Map<String,Map<String,Object>> map =  objectMapper.readValue(jsonStr, new TypeReference<Map<String,T>>() {
        });
        Map<String,T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String,Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * Json string convert to list with Class<T>.
     * @param jsonArrayStr json string
     * @param clazz        T class
     * @param <T>          type T
     * @return             List
     * @throws Exception
     */
    public static <T> List<T> json2list(String jsonArrayStr,Class<T> clazz)throws Exception{
        List<Map<String,Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
        });
        List<T> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    /**
     * Map convert to javabean.
     * @param map   map
     * @param clazz T class
     * @param <T>   type T
     * @return      javabean
     */
    public static <T> T map2pojo(Map map,Class<T> clazz){
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * Json string convert to xml.
     * @param jsonStr json string
     * @return        xml string
     * @throws Exception
     */
    public static String json2xml(String jsonStr)throws Exception{
        JsonNode root = objectMapper.readTree(jsonStr);
        String xml = xmlMapper.writeValueAsString(root);
        return xml;
    }

    /**
     * Xml string convert to json string.
     * @param xml xml string
     * @return    json string
     * @throws Exception
     */
    public static String xml2json(String xml)throws Exception{
        StringWriter w = new StringWriter();
        JsonParser jp = xmlMapper.getFactory().createParser(xml);
        JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
        while (jp.nextToken() != null) {
            jg.copyCurrentEvent(jp);
        }
        jp.close();
        jg.close();
        return w.toString();
    }
}
