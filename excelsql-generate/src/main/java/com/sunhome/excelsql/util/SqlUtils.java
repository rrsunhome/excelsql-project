package com.sunhome.excelsql.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author : qijia.wang
 * create at:  2020/4/1  下午1:10
 * @description:
 */
public abstract class SqlUtils {

    public static String specialChars(List<String> values) {
        return specialChars(StringUtils.join(values, "','"));
    }

    public static String specialChars(String value) {
        return "'" + value + "'";
    }

    public static String keywordSpecialChars(List<String> values) {
        return keywordSpecialChars(StringUtils.join(values, "`,`"));
    }

    public static String keywordSpecialChars(String value) {
        return "`" + value + "`";
    }


}
