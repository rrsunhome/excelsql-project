package com.github.rrsunhome.excelsql.util;

/**
 * @author : qijia.wang
 * create at:  2020/4/3  下午10:36
 * @description:
 */
public class PathMatcher {

    public static boolean matchUrl(String url) {
        if (url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://")) {
            return true;
        }
        return false;
    }
}
