package com.sunhome.excelsql;

import com.sunhome.excelsql.storage.ParserConfigStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : qijia.wang
 * create at:  2020/3/31  下午7:23
 * @description:
 */
public class ExcelSqlBootstrap {


    public static void main(String[] args) throws Exception {


//        ParserConfigStorage parserConfigStorage = new ParserConfigStorage() {
//            @Override
//            public RuleParserConfig getRuleParserConfig() {
//                RuleParserConfig ruleParserConfig = new RuleParserConfig();
//                ruleParserConfig.addTableMapping("aaa", 1);
//
//                ruleParserConfig.addFieldMapping("business_type", 1);
//                ruleParserConfig.addFieldMapping("use", 3);
//
//                return ruleParserConfig;
//            }
//        };
//
//        ExcelSqlGenerator excelSqlGenerator = new DefaultExcelSqlGenerator(parserConfigStorage);
//
//        excelSqlGenerator.executeInsertSql("/Users/wanqijia/Documents/开心汽车集团境内账户汇总20.2.24-增加系统账号-增加账户简称.xlsx");
//


        ParserConfigStorage parserConfigStorage = new ParserConfigStorage() {
            @Override
            public RuleParserConfig getRuleParserConfig() {
                RuleParserConfig ruleParserConfig = new RuleParserConfig();
                ruleParserConfig.addTableMapping("aaa", 1);
                Map<Object, Object> businessType = new HashMap<>();
                businessType.put("上海人人融资租赁有限公司", 1);
                businessType.put("上海千橡畅达互联网信息科技发展有限公司", 2);

                ruleParserConfig.addFieldMapping("business_type", 1, new ArrayList<>(), false);
                ruleParserConfig.addFieldMapping("use", 3, new ArrayList<>(), true, businessType);

                return ruleParserConfig;
            }
        };

        ExcelSqlGenerator excelSqlGenerator = new DefaultExcelSqlGenerator(parserConfigStorage);

        excelSqlGenerator.executeUpdateSql("/Users/wanqijia/Documents/开心汽车集团境内账户汇总20.2.24-增加系统账号-增加账户简称.xlsx");

    }
}
