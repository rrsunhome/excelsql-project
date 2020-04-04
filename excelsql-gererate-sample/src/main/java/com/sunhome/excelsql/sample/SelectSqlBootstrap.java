package com.sunhome.excelsql.sample;

import com.sunhome.excelsql.*;

/**
 * @author : qijia.wang
 * create at:  2020/4/1  下午4:10
 * @description:
 */
public class SelectSqlBootstrap {

    public static void main(String[] args) throws Exception {


        ParserConfigSource parserConfigSource = new ParserConfigSourceAdapter() {
            @Override
            protected void addParserConfig(RuleParserConfig ruleParserConfig) {
                ruleParserConfig.setStartRowIndex(1);
                // 表名对excel sheet 的映射
                ruleParserConfig.addTableMapping("user", 0);

                // 字段名对excel title的映射
                ruleParserConfig.addFieldMapping("name", 0,true);
            }

            @Override
            public String path() {
                return "/Users/wanqijia/Documents/test-sql.xlsx";

            }
            @Override
            public Sql sql() {
                return Sql.SELECT;
            }
        };


        DefaultExcelSqlGenerator excelSqlGenerator = new DefaultExcelSqlGenerator(parserConfigSource);
        // 可以添加显示层，默认控制台打印
       // excelSqlGenerator.addViewer(new FileViewer("/Users/xxx/Documents/user_sql.txt"));

        excelSqlGenerator.generate();

    }



}
