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
        }
        return null;

    }

    private List<String> updateSql() {
        List<String> sqlList = new ArrayList<>();
        List<SqlDefinition.RowDefinition> rowDefinitions = sqlDefinition.getRowDefinitions();
        for (SqlDefinition.RowDefinition rowDefinition : rowDefinitions) {
            List<SqlDefinition.FieldDefinition> fieldDefinitions = rowDefinition.getFieldDefinitions();

            StringBuffer updateSql = new StringBuffer().
                    append(Sql.UPDATE.name())
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
            updateSql.append(" where ");

            if (conditionDefinitions.isEmpty()) {
                throw new RuntimeException("配置规则无条件确定");
            }

            for (SqlDefinition.FieldDefinition conditionDefinition : conditionDefinitions) {
                updateSql.append(conditionDefinition.getField())
                        .append("=")
                        .append(SqlUtils.specialChars(conditionDefinition.getFieldValue()))
                        .append(" and ");
            }
            updateSql.delete(updateSql.lastIndexOf("and"), updateSql.length());
            updateSql.append(";");

            sqlList.add(updateSql.toString());
        }
        return sqlList;
    }


    private List<String> inertSql() {
        List<String> sqlList = new ArrayList<>();
        List<SqlDefinition.RowDefinition> rowDefinitions = sqlDefinition.getRowDefinitions();
        for (SqlDefinition.RowDefinition rowDefinition : rowDefinitions) {
            StringBuffer insertSql = new StringBuffer().append(Sql.INSERT.name())
                    .append(" into ")
                    .append(sqlDefinition.getTableName())
                    .append(" (")
                    .append(SqlUtils.keywordSpecialChars(rowDefinition.getFields()))
                    .append(") ")
                    .append(" values(")
                    .append(SqlUtils.specialChars(rowDefinition.getFieldValues()))
                    .append(") ")
                    .append(";");
            sqlList.add(insertSql.toString());
        }
        return sqlList;
    }


}
