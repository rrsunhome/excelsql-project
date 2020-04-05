package com.github.rrsunhome.excelsql;

public interface ParserConfigSource {

    /**
     * 解析相关配置
     *
     * @return RuleParserConfig
     */
    RuleParserConfig initParserConfig();

    /**
     * 文件路径
     *
     * @return 文件路径
     */
    String path();

    /**
     * 获取sql类型
     *
     * @return  sql类型
     */
    Sql sql();


}
