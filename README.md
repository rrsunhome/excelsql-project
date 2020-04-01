# excelsql 

## excel中数据对SQL的转换，可以快速将SQL生成


## 使用方法

### insert
```java
 ParserConfigStorage parserConfigStorage = new ParserConfigStorage() {
            @Override
            public RuleParserConfig getRuleParserConfig() {
                RuleParserConfig ruleParserConfig = new RuleParserConfig();
                // 表名对excel sheet 的映射
                ruleParserConfig.addTableMapping("user", 0);
                // 字段名对excel title的映射
                ruleParserConfig.addFieldMapping("name", 0);
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

        excelSqlGenerator.generateInsertSql();
```
### update
```java
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
```

### 属性介绍
```java
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
```