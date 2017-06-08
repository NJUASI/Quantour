package com.edu.nju.asi.dao;

import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.BasicDataID;

import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/6/7.
 */
public interface BasicDataDao {

    /**
     * 获取指定code,年份和季度的基本数据（单个季度）
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     * @param basicDataID 基本数据的ID
     * @return 指定ID的基本数据
     */
    BasicData get(BasicDataID basicDataID);

    /**
     * 根据已知的codeList获取所有基本数据
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     * @param codes 代码列表
     * @return 以code作为键值的Map
     */
    Map<String,List<BasicData>> getAllBasicData(List<String> codes);

    /**
     * 批量进行添加基本数据
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     * @param basicDataList 基本数据列表
     * @return 是否成功添加
     */
    boolean addAll(List<BasicData> basicDataList);
}
