package com.sunhome.excelsql.sample;

import com.sunhome.excelsql.RuleParserConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : qijia.wang
 * create at:  2020/4/1  下午4:10
 * @description:
 */
public class FieldBootstrap {

    public static void main(String[] args) throws Exception {

        RuleParserConfig ruleParserConfig = new RuleParserConfig();
        // 表名对excel sheet 的映射
        //  第1列：table名称
        //  第2列：sheet 所在下表

        ruleParserConfig.addTableMapping("user", 0);
        // 字段名对excel title的映射
        List<String> nameFilters = Arrays.asList("李三");
        Map<Object, Object> dataMapping = new HashMap<>();
        dataMapping.put("李三", 1);

        // 第1列：表中-字段名称
        // 第2列：excel-title所在列数
        // 第3列：excel-value过滤那些内容
        // 第4列：当为update时有效，该字段作为WHERE 后拼接条件
        // 第5列：数据和真实数据转换（excel中内容为李三，表中可能存uid int类型）

        ruleParserConfig.addFieldMapping("name", 0, nameFilters,true, dataMapping);


        // 第2列：excel-title titleName 名称
        ruleParserConfig.addFieldMapping("name", "", nameFilters,true, dataMapping);

    }
}
