package com.github.rrsunhome.excelsql.viewer;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author : qijia.wang
 * create at:  2020/3/31  下午3:45
 */
public class ConsoleViewer implements Viewer {

    @Override
    public void outPut(String content) {
        outPut(Arrays.asList(content));
    }

    @Override
    public void outPut(List<String> contentList) {
        if (CollectionUtils.isNotEmpty(contentList)) {
            contentList.forEach(System.out::println);
        }
    }
}
