package com.edu.nju.asi.dataHelper;

import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.BasicDataID;

import java.util.List;

/**
 * Created by Byron Dong on 2017/6/4.
 */
public interface BasicDataHelper {

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
     * 获取指定code和年份的所有季度的基本数据（4个季度）
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     * @param code 股票代号
     * @param year 制定的年份
     * @return 基本数据列表
     */
    List<BasicData> getBasicDatas(String code,int year);

    /**
     * 获取指定code，年份和当前季度后的N（number）个季度的基本数据（传入的当前季度也会获取,N个季度）
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     * @param basicDataID 基本数据的ID
     * @param number 需要获取数据的季度数
     * @return 基本数据列表
     */
    List<BasicData> getBasicDatas(BasicDataID basicDataID,int number);

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

    /**
     * 批量进行更新基本数据
     *
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     * @param basicDataList 基本数据列表
     * @return 是否成功添加
     */
    boolean updateAll(List<BasicData> basicDataList);
}
