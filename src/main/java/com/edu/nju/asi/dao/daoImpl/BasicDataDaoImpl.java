package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.BasicDataDao;
import com.edu.nju.asi.dataHelper.BasicDataHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.BasicDataID;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Byron Dong on 2017/6/7.
 */

@Component("BasicDataDao")
public class BasicDataDaoImpl implements BasicDataDao {

    private BasicDataHelper basicDataHelper;

    public BasicDataDaoImpl() {
        basicDataHelper = HelperManager.basicDataHelper;
    }

    /**
     * 获取指定code,年份和季度的基本数据（单个季度）
     *
     * @param basicDataID 基本数据的ID
     * @return 指定ID的基本数据
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public BasicData get(BasicDataID basicDataID) {
        return basicDataHelper.get(basicDataID);
    }

    /**
     * 获取指定code和年份的所有季度的基本数据（4个季度）
     *
     * @param code 股票代号
     * @param year 制定的年份
     * @return 基本数据列表
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public List<BasicData> getBasicDatas(String code, int year) {
        return basicDataHelper.getBasicDatas(code,year);
    }

    /**
     * 获取指定code，年份和当前季度后的N（number）个季度的基本数据（传入的当前季度也会获取,N个季度）
     *
     * @param basicDataID 基本数据的ID
     * @param number      需要获取数据的季度数
     * @return 基本数据列表
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public List<BasicData> getBasicDatas(BasicDataID basicDataID, int number) {
        return basicDataHelper.getBasicDatas(basicDataID,number);
    }

    /**
     * 批量进行添加基本数据
     *
     * @param basicDataList 基本数据列表
     * @return 是否成功添加
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public boolean addAll(List<BasicData> basicDataList) {
        return basicDataHelper.addAll(basicDataList);
    }
}
