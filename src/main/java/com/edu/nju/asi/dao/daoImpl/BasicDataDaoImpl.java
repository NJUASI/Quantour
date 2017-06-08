package com.edu.nju.asi.dao.daoImpl;

import com.edu.nju.asi.dao.BasicDataDao;
import com.edu.nju.asi.dataHelper.BasicDataHelper;
import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.BasicDataID;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
     * 根据已知的codeList获取所有基本数据
     *
     * @param codes 代码列表
     * @return 以code作为键值的Map
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/6/4
     */
    @Override
    public Map<String, List<BasicData>> getAllBasicData(List<String> codes) {
        return basicDataHelper.getAllBasicData(codes);
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
