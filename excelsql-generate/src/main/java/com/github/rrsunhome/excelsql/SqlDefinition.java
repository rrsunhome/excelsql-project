package com.github.rrsunhome.excelsql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : qijia.wang
 * create at:  2020/3/31  下午4:03
 * @description:
 */
public class SqlDefinition {

    private String tableName;

    private List<RowDefinition> rowDefinitions;

    public SqlDefinition(String tableName, List<RowDefinition> rowDefinitions) {
        this.tableName = tableName;
        this.rowDefinitions = rowDefinitions;
    }

    public SqlDefinition(String tableName) {
        this.tableName = tableName;
        this.rowDefinitions = new ArrayList<>();
    }


    public static class RowDefinition {
        private List<FieldDefinition> fieldDefinitions = new ArrayList<>();

        public RowDefinition() {
        }

        public RowDefinition(List<FieldDefinition> fieldDefinitions) {
            this.fieldDefinitions = fieldDefinitions;
        }

        public List<FieldDefinition> getFieldDefinitions() {
            return fieldDefinitions;
        }

        public void setFieldDefinitions(List<FieldDefinition> fieldDefinitions) {
            this.fieldDefinitions = fieldDefinitions;
        }

        public void addCellDefinition(String field, String fieldValue) {
            fieldDefinitions.add(new FieldDefinition(field, fieldValue));
        }

        public void addCellDefinition(String field, String fieldValue, boolean isCondition) {
            fieldDefinitions.add(new FieldDefinition(field, fieldValue, isCondition));
        }

        public List<String> getFields() {
            List<String> list = fieldDefinitions.stream()
                    .map(x -> x.getField())
                    .collect(Collectors.toList());
            return list;

        }

        public List<String> getFieldValues() {
            List<String> list = fieldDefinitions.stream()
                    .map(x -> x.getFieldValue())
                    .collect(Collectors.toList());
            return list;

        }
    }


    public static class FieldDefinition {

        private String field;

        private String fieldValue;

        private boolean isCondition;

        public FieldDefinition(String field, String fieldValue) {
            this.field = field;
            this.fieldValue = fieldValue;
        }

        public FieldDefinition(String field, String fieldValue, boolean isCondition) {
            this.field = field;
            this.fieldValue = fieldValue;
            this.isCondition = isCondition;
        }

        public String getField() {
            return field;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public boolean isCondition() {
            return isCondition;
        }

        public void setCondition(boolean condition) {
            isCondition = condition;
        }
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<RowDefinition> getRowDefinitions() {
        return rowDefinitions;
    }

    public void setRowDefinitions(List<RowDefinition> rowDefinitions) {
        this.rowDefinitions = rowDefinitions;
    }
}
