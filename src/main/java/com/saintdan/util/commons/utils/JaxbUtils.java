package com.saintdan.util.commons.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Jaxb utilities.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 9/1/15
 * @since JDK1.8
 */
public class JaxbUtils {

    /**
     * Parse xml string to xml object.
     *
     * @param clazz         xml object
     * @param xmlStr        xml string
     * @param <T>           T
     * @return              T instance
     * @throws JAXBException
     */
    public static <T> T parserXml(Class<T> clazz, String xmlStr) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (T) jaxbUnmarshaller.unmarshal(new StringReader(xmlStr));
    }

    /**
     * Parse xml file to xml object.
     *
     * @param clazz         xml object
     * @param file          xml file
     * @param <T>           T
     * @return              T instance
     * @throws JAXBException
     */
    public static <T> T parserXml(Class<T> clazz, File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (T) jaxbUnmarshaller.unmarshal(file);
    }

    /**
     * Save xml object to file.
     *
     * @param xmlObj    xml object
     * @param file      xml file
     * @throws JAXBException
     */
    public static void save(Object xmlObj, File file) throws JAXBException {
        Class<?> clazz = xmlObj.getClass();

        if (clazz.getAnnotation(XmlRootElement.class) == null) {
            throw new IllegalArgumentException("Object must be generate by jaxb.");
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(xmlObj, file);
    }

    /**
     * Transform xml object to xml string.
     *
     * @param xmlObj        xml object
     * @throws JAXBException
     *
     * @return
     */
    public static String toXmlString(Object xmlObj) throws JAXBException {
        Class<?> clazz = xmlObj.getClass();

        if (clazz.getAnnotation(XmlRootElement.class) == null) {
            throw new IllegalArgumentException("Object must be generate by jaxb.");
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter w = new StringWriter();
        jaxbMarshaller.marshal(xmlObj, w);
        return w.toString();
    }
}
