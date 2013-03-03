package com.wine.spider.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import net.sf.cglib.beans.BeanMap;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-27 Time: 下午2:26 To change this template use File | Settings |
 * File Templates.
 */
public class BeanCopyUtil {

    public static <T> T copyWithoutNull(T dest, Object orig) {
        if (dest == null || orig == null) {
            return dest;
        }
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(dest.getClass());
        } catch (IntrospectionException e) {
            return dest;
        }
        BeanMap beanMap = BeanMap.create(orig);
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            String name = propertyDescriptor.getName();
            if (!beanMap.containsKey(name)) {
                continue;
            }
            Object value = beanMap.get(name);
            if (value != null) {
                try {
                    propertyDescriptor.getWriteMethod().invoke(dest, value);
                } catch (IllegalAccessException e) {
                    continue;
                } catch (InvocationTargetException e) {
                    continue;
                }
            }
        }
        return dest;
    }
}
