package com.github.rrsunhome.excelsql;

/**
 * @author : qijia.wang
 * create at:  2020/4/3  下午9:31
 */
public abstract class ParserConfigSourceAdapter implements ParserConfigSource {

    /**
     * 初始化配置
     * @return RuleParserConfig
     */
    @Override
    public final RuleParserConfig initParserConfig() {
        RuleParserConfig ruleParserConfig = new RuleParserConfig();
        addParserConfig(ruleParserConfig);
        return ruleParserConfig;
    }

    /**
     * 添加配置
     *
     * @param ruleParserConfig 配置信息
     */
    protected abstract void addParserConfig(RuleParserConfig ruleParserConfig);

}
