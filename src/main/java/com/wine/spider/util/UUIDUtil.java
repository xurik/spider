package com.wine.spider.util;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/8/12 Time: 4:47 PM To change this template use File | Settings |
 * File Templates.
 */
public class UUIDUtil {

    public static String random() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
