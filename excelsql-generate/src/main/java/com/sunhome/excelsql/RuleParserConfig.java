package com.sunhome.excelsql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : qijia.wang
 * create at:  2020/3/31  下午3:43
 * @description:
 */
public class RuleParserConfig {

    private static final int DEFAULT_READ_ROW = 0;

    /**
     * 数据起始行
     */
    private int startRowIndex = DEFAULT_READ_ROW;

    private TableMapping tableMapping;

    private List<FieldMapping> fieldMappings = new ArrayList<>();


    public RuleParserConfig(int startRowIndex, TableMapping tableMapping, List<FieldMapping> fieldMappings) {
        this.startRowIndex = startRowIndex;
        this.tableMapping = tableMapping;
        this.fieldMappings = fieldMappings;
    }

    public RuleParserConfig(TableMapping tableMapping, List<FieldMapping> fieldMappings) {
        this(DEFAULT_READ_ROW, tableMapping, fieldMappings);
    }

    public RuleParserConfig() {
    }


    public void addTableMapping(String tableName, String sheetName) {
        tableMapping = new TableMapping(tableName, sheetName);
    }

    public void addTableMapping(String tableName, int sheetIndex) {
        tableMapping = new TableMapping(tableName, sheetIndex);
    }


    public void addFieldMapping(String fieldName, String titleName) {
        addFieldMapping(fieldName, titleName, new ArrayList<>());
    }

    public void addFieldMapping(String fieldName, String titleName, boolean isCondition) {
        addFieldMapping(fieldName, titleName, new ArrayList<>(), isCondition);
    }

    public void addFieldMapping(String fieldName, String titleName, List<String> filterValues) {
        addFieldMapping(fieldName, titleName, filterValues, false);

    }

    public void addFieldMapping(String fieldName, String titleName, List<String> filterValues, boolean isCondition) {
        addFieldMapping(fieldName, titleName, filterValues, isCondition);
    }

    public void addFieldMapping(String fieldName, int cellNum) {
        addFieldMapping(fieldName, cellNum, new ArrayList<>());
    }

    public void addFieldMapping(String fieldName, int cellNum, List<String> filterValues) {
        addFieldMapping(fieldName, cellNum, filterValues, false);
    }

    public void addFieldMapping(String fieldName, int cellNum, boolean isCondition) {
        addFieldMapping(fieldName, cellNum, new ArrayList<>(), isCondition);
    }

    public void addFieldMapping(String fieldName, String titleName, int cellNum, List<String> filterValues, boolean isCondition, Map<Object, Object> valueMappings) {
        fieldMappings.add(new FieldMapping(fieldName, titleName, cellNum, filterValues, isCondition, valueMappings));
    }

    public void addFieldMapping(String fieldName, int cellNum, List<String> filterValues, boolean isCondition, Map<Object, Object> valueMappings) {
        fieldMappings.add(new FieldMapping(fieldName, cellNum, filterValues, isCondition, valueMappings));
    }

    public void addFieldMapping(String fieldName, String titleName, List<String> filterValues, boolean isCondition, Map<Object, Object> valueMappings) {
        fieldMappings.add(new FieldMapping(fieldName, titleName, filterValues, isCondition, valueMappings));
    }

    public void addFieldMapping(String fieldName, int cellNum, List<String> filterValues, boolean isCondition) {
        fieldMappings.add(new FieldMapping(fieldName, cellNum, filterValues, isCondition));
    }

    public int getStartRowIndex() {
        return startRowIndex;
    }

    public void setStartRowIndex(int startRowIndex) {
        this.startRowIndex = startRowIndex;
    }

    public class FieldMapping {

        private String fieldName = "";

        private String titleName = "";

        private int cellNum;

        private boolean isFilter;

        private List<String> filterValues;

        private boolean isCondition;

        private Map<Object, Object> valueMappings;

        public boolean containFilterValue(String filterValue) {
            return filterValues.contains(filterValue);
        }

        public FieldMapping(String fieldName, String titleName) {
            this(fieldName, titleName, new ArrayList<>(), false);
        }

        public FieldMapping(String fieldName, int cellNum) {
            this(fieldName, cellNum, new ArrayList<>(), false);
        }

        public FieldMapping(String fieldName, String titleName, List<String> filterValues) {
            this(fieldName, titleName, filterValues, false);
        }

        public FieldMapping(String fieldName, String titleName, List<String> filterValues, boolean isCondition) {
            this(fieldName, titleName, 0, filterValues, isCondition);

        }

        public FieldMapping(String fieldName, int cellNum, List<String> filterValues) {
            this(fieldName, cellNum, filterValues, false);

        }

        public FieldMapping(String fieldName, int cellNum, List<String> filterValues, boolean isCondition) {
            this(fieldName, "", cellNum, filterValues, isCondition);

        }

        public FieldMapping(String fieldName, String titleName, int cellNum, List<String> filterValues, boolean isCondition) {
            this(fieldName, titleName, cellNum, filterValues, isCondition, new HashMap<>());
        }

        public FieldMapping(String fieldName, int cellNum, List<String> filterValues, boolean isCondition, Map<Object, Object> valueMappings) {
            this(fieldName, "", cellNum, filterValues, isCondition, valueMappings);
        }


        public FieldMapping(String fieldName, String titleName, List<String> filterValues, boolean isCondition, Map<Object, Object> valueMappings) {
            this(fieldName, titleName, 0, filterValues, isCondition, valueMappings);
        }

        public FieldMapping(String fieldName, String titleName, int cellNum, List<String> filterValues, boolean isCondition, Map<Object, Object> valueMappings) {
            this.fieldName = fieldName;
            this.titleName = titleName;
            this.cellNum = cellNum;
            this.filterValues = filterValues;
            this.isCondition = isCondition;
            this.valueMappings = valueMappings;
            if (filterValues != null && !filterValues.isEmpty()) {
                this.isFilter = true;

            }
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public int getCellNum() {
            return cellNum;
        }

        public void setCellNum(int cellNum) {
            this.cellNum = cellNum;
        }

        public boolean isFilter() {
            return isFilter;
        }

        public void setFilter(boolean filter) {
            isFilter = filter;
        }

        public List<String> getFilterValues() {
            return filterValues;
        }

        public void setFilterValues(List<String> filterValues) {
            this.filterValues = filterValues;
        }

        public boolean isCondition() {
            return isCondition;
        }

        public void setCondition(boolean condition) {
            isCondition = condition;
        }

        public Map<Object, Object> getValueMappings() {
            return valueMappings;
        }

        public void setValueMappings(Map<Object, Object> valueMappings) {
            this.valueMappings = valueMappings;
        }
    }


    public class TableMapping {

        private String tableName;

        private int sheetIndex;

        private String sheetName;

        public TableMapping(String tableName, String sheetName) {
            this.tableName = tableName;
            this.sheetName = sheetName;
        }

        public TableMapping(String tableName, int sheetIndex) {
            this.tableName = tableName;
            this.sheetIndex = sheetIndex;
        }

        public String getTableName() {
            return tableName;
        }

        public String getSheetName() {
            return sheetName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public int getSheetIndex() {
            return sheetIndex;
        }

        public void setSheetIndex(int sheetIndex) {
            this.sheetIndex = sheetIndex;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }
    }


    public TableMapping getTableMapping() {
        return tableMapping;
    }

    public List<FieldMapping> getFieldMappings() {
        return fieldMappings;
    }


    public void setTableMapping(TableMapping tableMapping) {
        this.tableMapping = tableMapping;
    }

    public void setFieldMappings(List<FieldMapping> fieldMappings) {
        this.fieldMappings = fieldMappings;
    }
}
