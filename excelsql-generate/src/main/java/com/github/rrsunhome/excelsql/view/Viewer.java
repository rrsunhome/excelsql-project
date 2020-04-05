package com.github.rrsunhome.excelsql.view;

import java.util.List;

public interface Viewer {

    void outPut(String content);

    void outPut(List<String> contentList);
}
