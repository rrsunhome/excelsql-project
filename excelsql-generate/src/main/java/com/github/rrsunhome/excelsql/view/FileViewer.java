package com.github.rrsunhome.excelsql.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

/**
 * @author : qijia.wang
 * create at:  2020/3/31  下午3:45
 * @description:
 */
public class FileViewer implements Viewer {

    private Writer writer;

    public FileViewer(String outPutPath, boolean append) {
        try {
            this.writer = new FileWriter(outPutPath, append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileViewer(String outPutPath) {
        this(outPutPath, false);
    }

    @Override
    public void outPut(String content) {
        outPut(Arrays.asList(content));
    }

    @Override
    public void outPut(List<String> contentList) {
        try {
            for (String content : contentList) {
                writer.write(content + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
