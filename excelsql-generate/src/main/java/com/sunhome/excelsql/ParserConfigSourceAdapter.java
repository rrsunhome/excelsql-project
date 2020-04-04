package com.sunhome.excelsql;

/**
 * @author : qijia.wang
 * create at:  2020/4/3  下午9:31
 * @description:
 */
public abstract class ParserConfigSourceAdapter implements ParserConfigSource {

    @Override
    public final RuleParserConfig initParserConfig() {
        RuleParserConfig ruleParserConfig = new RuleParserConfig();
        addParserConfig(ruleParserConfig);
        return ruleParserConfig;
    }

    /**
     * 添加配置
     *
     * @param ruleParserConfig
     */
    protected abstract void addParserConfig(RuleParserConfig ruleParserConfig);

}
