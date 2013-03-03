package com.wine.spider.util;

import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12/6/12 Time: 4:54 PM To change this template use File | Settings |
 * File Templates.
 */
public class WriterUtil {

    private final static Logger logger = Logger.getLogger(WriterUtil.class);

    public static void clossWriter(Writer writer) {
        if (writer == null) return;
        try {
            writer.close();
        } catch (IOException e) {
            logger.error("clossWriter error!", e);
        }
    }
}
