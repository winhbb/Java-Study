package com.gaodun.storm.study.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BeanUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * copy specified properties
     *
     * @param src
     * @param trg
     * @param props
     */
    public static void copy(Object src, Object trg, Iterable<String> props) {
        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
        props.forEach(p -> trgWrap.setPropertyValue(p, srcWrap.getPropertyValue(p)));
    }

    /**
     * copy all properties
     *
     * @param src
     * @param trg
     */
    public static void copy(Object src, Object trg) {
        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
        PropertyDescriptor[] trgPds = trgWrap.getPropertyDescriptors();
        for (PropertyDescriptor pd : trgPds) {
            String pName = pd.getName();
            if (trgWrap.isWritableProperty(pName) && srcWrap.isReadableProperty(pName)) {
                trgWrap.setPropertyValue(pName, srcWrap.getPropertyValue(pName));
            }
        }
    }

    public static void copy(Object src, Object trg, String... ignores) {
        Set<String> ignoreSet = new HashSet<>(Arrays.asList(ignores));
        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
        PropertyDescriptor[] trgPds = trgWrap.getPropertyDescriptors();
        for (PropertyDescriptor pd : trgPds) {
            String pName = pd.getName();
            if (ignoreSet.contains(pName)) {
                continue;
            }
            if (trgWrap.isWritableProperty(pName) && srcWrap.isReadableProperty(pName)) {
                trgWrap.setPropertyValue(pName, srcWrap.getPropertyValue(pName));
            }
        }
    }

    /**
     * copy non null properties
     *
     * @param src
     * @param trg
     */
    public static void copyNonNull(Object src, Object trg) {
        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
        PropertyDescriptor[] trgPds = trgWrap.getPropertyDescriptors();
        for (PropertyDescriptor pd : trgPds) {
            String pName = pd.getName();
            if (trgWrap.isWritableProperty(pName) && srcWrap.isReadableProperty(pName)) {
                Object srcValue = srcWrap.getPropertyValue(pName);
                if (srcValue != null) {
                    trgWrap.setPropertyValue(pName, srcValue);
                }
            }
        }
    }

    public static void copyIgnoreAbsent(JsonNode src, Object trg) {
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);
        src.fieldNames().forEachRemaining(fieldName -> {
            if (trgWrap.isWritableProperty(fieldName)) {
                trgWrap.setPropertyValue(fieldName, mapper.convertValue(src.get(fieldName), trgWrap.getPropertyType(fieldName)));
            }
        });
    }
}
