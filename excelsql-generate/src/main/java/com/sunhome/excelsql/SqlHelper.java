package com.sunhome.excelsql;

import com.sunhome.excelsql.util.SqlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : qijia.wang
 * create at:  2020/3/31  下午3:45
 * @description:
 */
public class SqlHelper {

    private SqlDefinition sqlDefinition;

    private Sql sql;

    public SqlHelper(SqlDefinition sqlDefinition, Sql sql) {
        this.sqlDefinition = sqlDefinition;
        this.sql = sql;

    }

    public List<String> generate() {
        if (Sql.INSERT.equals(sql)) {
            return inertSql();
        } else if (Sql.UPDATE.equals(sql)) {
            return updateSql();
        } else if (Sql.DELETE.equals(sql)) {
            return deleteSql();
        } else if (Sql.SELECT.equals(sql)) {
            return selectSql();
        }

        throw new IllegalArgumentException("sql 类型 配置有误");
    }

    private List<String> selectSql() {
        List<String> sqlList = new ArrayList<>();
        List<SqlDefinition.RowDefinition> rowDefinitions = sqlDefinition.getRowDefinitions();
        for (SqlDefinition.RowDefinition rowDefinition : rowDefinitions) {
            List<SqlDefinition.FieldDefinition> fieldDefinitions = rowDefinition.getFieldDefinitions();

            StringBuffer selectSql = new StringBuffer().
                    append(Sql.SELECT.name().toLowerCase())
                    .append(" * ")
                    .append(" from ")
                    .append(sqlDefinition.getTableName());


            List<SqlDefinition.FieldDefinition> conditionDefinitions = new ArrayList<>();
            for (SqlDefinition.FieldDefinition fieldDefinition : fieldDefinitions) {
                if (fieldDefinition.isCondition()) {
                    conditionDefinitions.add(fieldDefinition);
                }
            }
            appendCondition(selectSql, conditionDefinitions);
            sqlList.add(selectSql.toString());
        }
        return sqlList;

    }

    private List<String> deleteSql() {
        List<String> sqlList = new ArrayList<>();
        List<SqlDefinition.RowDefinition> rowDefinitions = sqlDefinition.getRowDefinitions();
        for (SqlDefinition.RowDefinition rowDefinition : rowDefinitions) {
            List<SqlDefinition.FieldDefinition> fieldDefinitions = rowDefinition.getFieldDefinitions();
            StringBuffer selectSql = new StringBuffer().
                    append(Sql.DELETE.name().toLowerCase())
                    .append(" from ")
                    .append(sqlDefinition.getTableName());

            List<SqlDefinition.FieldDefinition> conditionDefinitions = new ArrayList<>();
            for (SqlDefinition.FieldDefinition fieldDefinition : fieldDefinitions) {
                if (fieldDefinition.isCondition()) {
                    conditionDefinitions.add(fieldDefinition);
                }
            }

            appendCondition(selectSql, conditionDefinitions);
            sqlList.add(selectSql.toString());
        }
        return sqlList;

    }


    private List<String> updateSql() {
        List<String> sqlList = new ArrayList<>();
        List<SqlDefinition.RowDefinition> rowDefinitions = sqlDefinition.getRowDefinitions();
        for (SqlDefinition.RowDefinition rowDefinition : rowDefinitions) {
            List<SqlDefinition.FieldDefinition> fieldDefinitions = rowDefinition.getFieldDefinitions();

            StringBuffer updateSql = new StringBuffer().
                    append(Sql.UPDATE.name().toLowerCase())
                    .append(" ")
                    .append(sqlDefinition.getTableName())
                    .append(" set ");

            List<SqlDefinition.FieldDefinition> conditionDefinitions = new ArrayList<>();

            for (SqlDefinition.FieldDefinition fieldDefinition : fieldDefinitions) {
                if (fieldDefinition.isCondition()) {
                    conditionDefinitions.add(fieldDefinition);
                }
                updateSql.append(SqlUtils.keywordSpecialChars(fieldDefinition.getField()))
                        .append("=")
                        .append(SqlUtils.specialChars(fieldDefinition.getFieldValue()))
                        .append(",");
            }

            updateSql.deleteCharAt(updateSql.length() - 1);

            appendCondition(updateSql, conditionDefinitions);

            sqlList.add(updateSql.toString());
        }
        return sqlList;
    }


    private List<String> inertSql() {
        List<String> sqlList = new ArrayList<>();
        List<SqlDefinition.RowDefinition> rowDefinitions = sqlDefinition.getRowDefinitions();
        for (SqlDefinition.RowDefinition rowDefinition : rowDefinitions) {
            StringBuffer insertSql = new StringBuffer().append(Sql.INSERT.name().toLowerCase())
                    .append(" into ")
                    .append(sqlDefinition.getTableName())
                    .append(" (")
                    .append(SqlUtils.keywordSpecialChars(rowDefinition.getFields()))
                    .append(") ")
                    .append(" values(")
                    .append(SqlUtils.specialChars(rowDefinition.getFieldValues()))
                    .append(");");
            sqlList.add(insertSql.toString());
        }
        return sqlList;
    }


    private void appendCondition(StringBuffer sql, List<SqlDefinition.FieldDefinition> conditionDefinitions) {
        if (conditionDefinitions.isEmpty()) {
            throw new IllegalArgumentException("配置错误,无法完成解析");
        }
        sql.append(" where ");
        for (SqlDefinition.FieldDefinition conditionDefinition : conditionDefinitions) {
            sql.append(conditionDefinition.getField())
                    .append("=")
                    .append(SqlUtils.specialChars(conditionDefinition.getFieldValue()))
                    .append(" and ");
        }
        sql.delete(sql.lastIndexOf(" and "), sql.length());
        sql.append(";");
    }

}
