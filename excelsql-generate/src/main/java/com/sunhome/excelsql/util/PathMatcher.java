package com.sunhome.excelsql.util;

/**
 * @author : qijia.wang
 * create at:  2020/4/3  下午10:36
 * @description:
 */
public class PathMatcher {

    public static  boolean matchUrl(String url) {
        if ("http".startsWith(url) || "https".startsWith(url)) {
            return true;
        }
        return false;
    }
}
