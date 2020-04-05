package com.github.rrsunhome.excelsql;

import com.github.rrsunhome.excelsql.parser.ExcelParser;
import com.github.rrsunhome.excelsql.util.ExcelInputStream;
import com.github.rrsunhome.excelsql.viewer.ConsoleViewer;
import com.github.rrsunhome.excelsql.viewer.Viewer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : qijia.wang
 * create at:  2020/3/31  下午3:42
 */
public class DefaultExcelSqlGenerator implements ExcelSqlGenerator {

    private ParserConfigSource parserConfigSource;

    private ExcelParser excelParser;

    private List<Viewer> viewers;

    /**
     * @param parserConfigSource 配置信息
     */
    public DefaultExcelSqlGenerator(ParserConfigSource parserConfigSource) {
        this.parserConfigSource = parserConfigSource;
        this.viewers = defaultViewers();
        excelParser = new ExcelParser();
    }

    /**
     * @param viewer 展示层
     */
    public void addViewer(Viewer viewer) {
        this.viewers.add(viewer);
    }

    /**
     * @return 默认的展示层
     */
    private List<Viewer> defaultViewers() {
        List<Viewer> defaultViewers = new ArrayList<>();
        defaultViewers.add(new ConsoleViewer());
        return defaultViewers;
    }

    /**
     * 生成对应的SQL
     *
     * @throws Exception 解析异常
     */
    @Override
    public void generate() throws Exception {
        InputStream is = ExcelInputStream.createInputStream(parserConfigSource.path());
        SqlDefinition sqlDefinition = excelParser.parser(is, parserConfigSource.initParserConfig());

        SqlHelper sqlHelp = new SqlHelper(sqlDefinition, parserConfigSource.sql());
        List<String> sqlList = sqlHelp.generate();

        viewers.forEach(viewer -> viewer.outPut(sqlList));
    }


}
