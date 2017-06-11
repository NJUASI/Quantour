package com.edu.nju.asi.task.storer.financialStatement;

import com.edu.nju.asi.model.BasicData;

import java.util.List;

/**
 * Created by Byron Dong on 2017/6/5.
 */
public interface Statement {

    void write(List<BasicData> statement);

    List<BasicData> get(String path);

    boolean update(List<BasicData> basicDataList);
}
