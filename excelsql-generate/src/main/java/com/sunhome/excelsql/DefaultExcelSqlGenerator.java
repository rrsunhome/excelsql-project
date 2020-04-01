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

    private ExcelParser excelParser;

    private List<Viewer> viewers;


    public DefaultExcelSqlGenerator(ParserConfigStorage parserConfigStorage) {
        this.parserConfigStorage = parserConfigStorage;
        this.viewers = defaultViewers();
        excelParser = new ExcelParser();
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
    public void generate() throws Exception {
        SqlDefinition sqlDefinition = excelParser.parser(parserConfigStorage.getExcelPath(), parserConfigStorage.getRuleParserConfig());

        SqlHelper sqlHelp = new SqlHelper(sqlDefinition, parserConfigStorage.getSqlType());
        List<String> sqlList = sqlHelp.generate();

        viewers.forEach(viewer -> viewer.outPut(sqlList));
    }


}
