package com.github.rrsunhome.excelsql.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * @author : qijia.wang
 * create at:  2020/4/3  下午10:31
 */
public class HttpUtils {

    /**
     * 连接超时
     */
    private static final int CONNECT_TIMEOUT = 10 * 1000;
    /**
     * 读取超时
     */
    private static final int READ_TIMEOUT = 10 * 1000;


    /**
     *
     * @param path 网络路径
     * @return InputStream
     * @throws IOException 网络异常
     */
    public static InputStream read(String path) throws IOException {
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            System.out.printf("remote url: %s read ...\n", path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.connect();
            is = conn.getInputStream();

        } catch (SocketTimeoutException e) {
            throw new SocketTimeoutException("连接超时,读取远程资源失败(10s)");
        } catch (IOException e) {
            throw new IOException("读取异常,读取远程资源失败");
        }
        return is;
    }

}
