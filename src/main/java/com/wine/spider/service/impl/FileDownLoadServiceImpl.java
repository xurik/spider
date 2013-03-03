package com.wine.spider.service.impl;

import com.wine.spider.service.FileDownLoadService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA. User: sunday Date: 12-12-27 Time: 下午5:32 To change this template use File | Settings |
 * File Templates.
 */
@Service
public class FileDownLoadServiceImpl implements FileDownLoadService {

    private static final Logger     logger = LoggerFactory.getLogger(FileDownLoadServiceImpl.class);
    private final static int        BUFFER = 1024;
    private final static HttpClient client = new HttpClient();

    @Override
    public void downLoad(String path, String url) {
        GetMethod httpGet = new GetMethod(url);
        InputStream in = null;
        FileOutputStream out = null;
        try {
            client.executeMethod(httpGet);
            in = httpGet.getResponseBodyAsStream();
            out = FileUtils.openOutputStream(new File(path));
            byte[] b = new byte[BUFFER];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }

        } catch (Exception e) {
            logger.error("Exception error", e);

        } finally {
            if (in != null) try {
                in.close();
            } catch (IOException e) {
                logger.error("IOException error", e);
            }
            if (out != null) try {
                out.close();
            } catch (IOException e) {
                logger.error("IOException error", e);
            }
            httpGet.releaseConnection();
        }
    }
}
