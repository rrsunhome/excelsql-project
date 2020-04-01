package com.sunhome.excelsql;

public interface ExcelSqlGenerator {

    void generate(Sql sql) throws Exception;

    void generateInsertSql() throws Exception;

    void generateUpdateSql() throws Exception;


}
