package com.sunhome.excelsql.storage;

import com.sunhome.excelsql.RuleParserConfig;
import com.sunhome.excelsql.Sql;

public interface ParserConfigStorage {

    /**
     * 解析相关配置
     *
     * @return
     */
    RuleParserConfig getRuleParserConfig();

    /**
     * 文件路径
     *
     * @return
     */
    String getExcelPath();

    /**
     * 获取sql类型
     *
     * @return
     */
    Sql getSqlType();


}
