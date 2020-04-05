package com.github.rrsunhome.excelsql.util;

/**
 * @author : qijia.wang
 * create at:  2020/4/3  下午10:36
 */
public class PathMatcher {

    /**
     * 匹配路径是否为网络路径
     *
     * @param url url
     * @return boolean
     */
    public static boolean matchUrl(String url) {
        if (url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://")) {
            return true;
        }
        return false;
    }
}
