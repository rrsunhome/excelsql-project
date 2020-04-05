package com.github.rrsunhome.excelsql.viewer;

import java.util.List;

public interface Viewer {

    void outPut(String content);

    void outPut(List<String> contentList);
}
