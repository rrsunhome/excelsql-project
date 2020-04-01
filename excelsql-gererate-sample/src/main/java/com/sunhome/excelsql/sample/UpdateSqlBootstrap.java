package com.sunhome.excelsql.sample;

import com.sunhome.excelsql.DefaultExcelSqlGenerator;
import com.sunhome.excelsql.RuleParserConfig;
import com.sunhome.excelsql.storage.ParserConfigStorage;
import com.sunhome.excelsql.view.FileViewer;

import java.util.ArrayList;

/**
 * @author : qijia.wang
 * create at:  2020/4/1  下午4:10
 * @description:
 */
public class UpdateSqlBootstrap {

    public static void main(String[] args) throws Exception {


        ParserConfigStorage parserConfigStorage = new ParserConfigStorage() {


            @Override
            public RuleParserConfig getRuleParserConfig() {
                RuleParserConfig ruleParserConfig = new RuleParserConfig();
                // 表名对excel sheet 的映射
                ruleParserConfig.addTableMapping("user", 0);
                // 字段名对excel title的映射
                ruleParserConfig.addFieldMapping("name", 0, true);
                ruleParserConfig.addFieldMapping("age", 1);

                return ruleParserConfig;
            }

            @Override
            public String getExcelPath() {
                return "/Users/wanqijia/Documents/test-sql.xlsx";
            }
        };

        DefaultExcelSqlGenerator excelSqlGenerator = new DefaultExcelSqlGenerator(parserConfigStorage);
        // 可以添加显示层，默认控制台打印
        excelSqlGenerator.addViewer(new FileViewer("/Users/wanqijia/Documents/user_sql.txt"));

        excelSqlGenerator.generateUpdateSql();

    }
}
