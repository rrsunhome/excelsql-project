# excelsql 

## 目的
利用简单的配置，快速把excel的数据生成对应的sql

## 相关文档说明
* 配置信息->
    * 配置title行->RuleParserConfig.setStartRowIndex(0);
    * table映射excel sheet->RuleParserConfig.addTableMapping("user", 0); 
    * 字段名对excel title的映射-> RuleParserConfig.addFieldMapping("name", 0, nameFilters,true, dataMapping); 
    * 文件路径：支持本地和远程地址
    * 详情配置见 -> [配置详情](excelsql-gererate-sample/src/main/java/com/sunhome/excelsql/sample/ParserConfigExplain.java)


## 快速开始

### 生成 insert语句
demo代码地址：
https://github.com/rrsunhome/excelsql-project/blob/master/excelsql-gererate-sample/src/main/java/com/sunhome/excelsql/sample/InsertSqlBootstrap.java
```java
       ParserConfigSource parserConfigSource = new ParserConfigSourceAdapter() {
            @Override
            protected void addParserConfig(RuleParserConfig ruleParserConfig) {
                // 表名对excel sheet 的映射
                ruleParserConfig.addTableMapping("user", 0);
                // 字段名对excel title的映射
                ruleParserConfig.addFieldMapping("name", 0);
                ruleParserConfig.addFieldMapping("age", 1);
            }

            @Override
            public String path() {
                return "test-sql.xlsx";
            }
            @Override
            public Sql sql() {
                return Sql.INSERT;
            }
        };

        DefaultExcelSqlGenerator excelSqlGenerator = new DefaultExcelSqlGenerator(parserConfigSource);
        // 可以添加显示层，默认控制台打印
       // excelSqlGenerator.addViewer(new FileViewer("/Users/xxx/Documents/user_sql.txt"));

        excelSqlGenerator.generate();
```

### 生成 update语句
demo代码地址：
https://github.com/rrsunhome/excelsql-project/blob/master/excelsql-gererate-sample/src/main/java/com/sunhome/excelsql/sample/UpdateSqlBootstrap.java
```java
        ParserConfigSource parserConfigSource = new ParserConfigSourceAdapter() {
            @Override
            protected void addParserConfig(RuleParserConfig ruleParserConfig) {
                ruleParserConfig.setStartRowIndex(0);
                // 表名对excel sheet 的映射
                ruleParserConfig.addTableMapping("user", 0);
                // 字段名对excel title的映射
                ruleParserConfig.addFieldMapping("name", 0, true);
                ruleParserConfig.addFieldMapping("age", 1);
            }

            @Override
            public String path() {
                return "test-sql.xlsx";
            }
            @Override
            public Sql sql() {
                return Sql.UPDATE;
            }
        };

        DefaultExcelSqlGenerator excelSqlGenerator = new DefaultExcelSqlGenerator(parserConfigSource);
        // 可以添加显示层，默认控制台打印
        //excelSqlGenerator.addViewer(new FileViewer("/Users/xxx/Documents/user_sql.txt"));

        excelSqlGenerator.generate();
```

### 使用问题
    有任何bug,git留言
