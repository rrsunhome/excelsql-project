package com.sunhome.excelsql;

public interface ParserConfigSource {

    /**
     * 解析相关配置
     *
     * @return
     */
    RuleParserConfig initParserConfig();

    /**
     * 文件路径
     *
     * @return
     */
    String path();

    /**
     * 获取sql类型
     *
     * @return
     */
    Sql sql();


}
