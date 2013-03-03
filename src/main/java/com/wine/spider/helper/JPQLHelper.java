package com.wine.spider.helper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.springframework.util.Assert;

import com.wine.spider.entity.SiteEntity;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/3/12 Time: 10:10 PM To change this template use File | Settings |
 * File Templates.
 */
public class JPQLHelper {

    public static String entityTo(Object entity) {
        Assert.notNull(entity);
        String className = entity.getClass().getSimpleName();
        String entityName = className.substring(0, 1).toLowerCase() + className.substring(1);

        StringBuffer stringBuffer = new StringBuffer();
        String and = " and ";
        stringBuffer.append("select ").append(entityName);
        stringBuffer.append(" from ").append(className).append(" ").append(entityName).append(" ");
        Boolean first = false;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass(), Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor p : pds) {
                Object v = p.getReadMethod().invoke(entity);
                if (v != null) {
                    first = true;
                    String name = p.getName();
                    if (first) {
                        stringBuffer.append(" where ");

                    }
                    stringBuffer.append(entityName).append(".").append(name).append("=").append(":").append(name).append(and);
                    first = false;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("build jpql error!class=" + entity.getClass());
        }
        int l = stringBuffer.length();
        stringBuffer = stringBuffer.replace(l - and.length(), l, "");

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        SiteEntity site = new SiteEntity();
        site.setName("name");
        site.setNote("note");
        String l = JPQLHelper.entityTo(site);
        System.out.println(l);
    }
}
