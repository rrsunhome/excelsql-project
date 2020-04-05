package com.github.rrsunhome.excelsql.util;


import java.io.*;

/**
 * @author : qijia.wang
 * create at:  2020/4/3  下午10:44
 */
public class ExcelInputStream {

    /**
     * 根据路径创建输入流
     * @param path 路径
     * @return 输入流
     * @throws IOException io异常
     */
    public static InputStream createInputStream(String path) throws IOException {
        if (PathMatcher.matchUrl(path)) {
            return HttpUtils.read(path);
        }

        InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("path " + path + "路径下资源不存在");
        }

        return is;

    }
}
