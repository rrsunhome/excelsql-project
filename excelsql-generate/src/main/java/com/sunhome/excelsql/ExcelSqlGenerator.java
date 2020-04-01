package com.sunhome.excelsql;

public interface ExcelSqlGenerator {

    void execute(String excelFilePath, Sql sql) throws Exception;

    void executeInsertSql(String excelFilePath) throws Exception;

    void executeUpdateSql(String excelFilePath) throws Exception;


}
