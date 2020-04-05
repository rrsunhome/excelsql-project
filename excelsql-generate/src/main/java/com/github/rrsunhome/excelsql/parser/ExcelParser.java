package com.github.rrsunhome.excelsql.parser;

import com.github.rrsunhome.excelsql.RuleParserConfig;
import com.github.rrsunhome.excelsql.SqlDefinition;
import com.github.rrsunhome.excelsql.util.ExcelUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author : qijia.wang
 * create at:  2020/3/31  下午3:43
 */
public class ExcelParser {

    /**
     * @param path             路径
     * @param ruleParserConfig 解析规则
     * @return sql定义的对象
     * @throws Exception 解析异常
     */
    public SqlDefinition parser(String path, RuleParserConfig ruleParserConfig) throws Exception {
        return parser(new File(path), ruleParserConfig);
    }

    /**
     * @param file             文件
     * @param ruleParserConfig 解析规则
     * @return sql定义的对象
     * @throws Exception 解析异常
     */
    public SqlDefinition parser(File file, RuleParserConfig ruleParserConfig) throws Exception {
        return parser(new FileInputStream(file), ruleParserConfig);
    }

    /**
     * @param is               文件
     * @param ruleParserConfig 解析规则
     * @return sql定义的对象
     * @throws Exception 解析异常
     */
    public SqlDefinition parser(InputStream is, RuleParserConfig ruleParserConfig) throws Exception {

        List<RuleParserConfig.FieldMapping> fieldMappings = ruleParserConfig.getFieldMappings();

        RuleParserConfig.TableMapping tableMapping = ruleParserConfig.getTableMapping();
        List<List<Object>> lists = null;
        if (StringUtils.isEmpty(tableMapping.getSheetName())) {
            lists = ExcelUtils.readExcel(is, tableMapping.getSheetIndex(), ruleParserConfig.getStartRowIndex());
        } else {
            lists = ExcelUtils.readExcel(is, tableMapping.getSheetName(), ruleParserConfig.getStartRowIndex());
        }

        SqlDefinition sqlDefinition = new SqlDefinition(tableMapping.getTableName());


        processConfig(lists.get(0), fieldMappings);


        lists.remove(0);
        List<SqlDefinition.RowDefinition> rowDefinitions = sqlDefinition.getRowDefinitions();

        for (int i = 0; i < lists.size(); i++) {
            List<Object> values = lists.get(i);
            SqlDefinition.RowDefinition rowDefinition = new SqlDefinition.RowDefinition();

            for (RuleParserConfig.FieldMapping fieldMapping : fieldMappings) {
                String fieldValue = String.valueOf(values.get(fieldMapping.getCellNum()));
                // 过滤预先设置过滤属性
                if (fieldMapping.isFilter()) {
                    if (fieldMapping.containFilterValue(fieldValue)) {
                        break;
                    }
                }
                // 映射具体的内容
                if (fieldMapping.getValueMappings() != null) {
                    Object mappingValue = fieldMapping.getValueMappings().get(fieldValue);
                    if (mappingValue != null && !mappingValue.toString().isEmpty()) {
                        fieldValue = mappingValue.toString();
                    }

                }
                rowDefinition.addCellDefinition(fieldMapping.getFieldName(), fieldValue, fieldMapping.isCondition());
            }
            rowDefinitions.add(rowDefinition);
        }
        sqlDefinition.setRowDefinitions(rowDefinitions);
        return sqlDefinition;
    }

    private void processConfig(List<Object> heads, List<RuleParserConfig.FieldMapping> fieldMappings) {
        for (int i = 0; i < heads.size(); i++) {
            for (RuleParserConfig.FieldMapping fieldMapping : fieldMappings) {
                if (fieldMapping.getTitleName().equals(String.valueOf(heads.get(i)))) {
                    fieldMapping.setCellNum(i);
                }
                if (fieldMapping.getCellNum() == i) {
                    fieldMapping.setTitleName(String.valueOf(heads.get(i)));
                }
            }
        }
    }

}
