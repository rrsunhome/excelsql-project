package com.github.rrsunhome.excelsql.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author : qijia.wang
 * create at:  2020/4/1  下午1:10
 */
public abstract class SqlUtils {

    /**
     * 生成字符并且,隔开
     * @param values 集合
     * @return 返回字符串
     */
    public static String specialChars(List<String> values) {
        return specialChars(StringUtils.join(values, "','"));
    }

    /**
     * 为内容增加特殊字符
     * @param value 字符
     * @return 返回处理好的字符串
     */
    public static String specialChars(String value) {
        return "'" + value + "'";
    }

    /**
     * 给集合中每个字符特殊处理
     * @param values 字符集合
     * @return 返回处理好的字符串
     */
    public static String keywordSpecialChars(List<String> values) {
        return keywordSpecialChars(StringUtils.join(values, "`,`"));
    }

    /**
     * 特殊处理内容
     * @param value 字符
     * @return 返回处理好的字符串
     */
    public static String keywordSpecialChars(String value) {
        return "`" + value + "`";
    }


}
