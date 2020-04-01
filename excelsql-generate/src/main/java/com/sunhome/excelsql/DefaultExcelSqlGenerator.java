package com.sunhome.excelsql;

import com.sunhome.excelsql.parse.ExcelParser;
import com.sunhome.excelsql.storage.ParserConfigStorage;
import com.sunhome.excelsql.view.ConsoleViewer;
import com.sunhome.excelsql.view.Viewer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : qijia.wang
 * create at:  2020/3/31  下午3:42
 * @description:
 */
public class DefaultExcelSqlGenerator implements ExcelSqlGenerator {

    private ParserConfigStorage parserConfigStorage;

    private List<Viewer> viewers;


    public DefaultExcelSqlGenerator(ParserConfigStorage parserConfigStorage) {
        this.parserConfigStorage = parserConfigStorage;
        this.viewers = defaultViewers();
    }

    public void addViewer(Viewer viewer) {
        this.viewers.add(viewer);
    }

    private List<Viewer> defaultViewers() {
        List<Viewer> defaultViewers = new ArrayList<>();
        defaultViewers.add(new ConsoleViewer());
        return defaultViewers;
    }

    @Override
    public void generate(Sql sql) throws Exception {

        ExcelParser excelParser = new ExcelParser(parserConfigStorage.getRuleParserConfig());

        SqlDefinition sqlDefinition = excelParser.parser(parserConfigStorage.getExcelPath());

        SqlHelper sqlHelp = new SqlHelper(sqlDefinition, sql);
        List<String> sqlList = sqlHelp.generate();

        viewers.forEach(viewer -> viewer.outPut(sqlList));
    }

    @Override
    public void generateInsertSql() throws Exception {
        generate(Sql.INSERT);
    }

    @Override
    public void generateUpdateSql() throws Exception {
        generate(Sql.UPDATE);
    }
}
