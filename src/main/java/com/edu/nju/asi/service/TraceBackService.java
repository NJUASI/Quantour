package com.edu.nju.asi.service;

import com.edu.nju.asi.infoCarrier.traceBack.FilterCondition;
import com.edu.nju.asi.infoCarrier.traceBack.RankCondition;
import com.edu.nju.asi.utilities.exceptions.*;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;

import java.io.IOException;
import java.util.List;

/**
 * Created by harvey on 17-3-28.
 *
 * 回测功能的接口
 */
public interface TraceBackService {


    /**
     *
     * @param traceBackCriteria 回测标准
     * @return 回测所要展示的数据
     */
    public TraceBackInfo traceBack(TraceBackCriteria traceBackCriteria)  throws IOException, DataSourceFirstDayException, DateNotWithinException, NoDataWithinException, UnhandleBlockTypeException;

    /**
     * 策略优化时，先设置原策略的条件
     * @param traceBackCriteria 原策略的标准
     * @return
     */
    public void setOriginTraceBackCriteria(TraceBackCriteria traceBackCriteria) throws IOException;

    /**
     * 进行一次适应函数的计算
     * @param filterConditions 经过遗传算法变更过参数的筛选条件
     * @param rankConditions 经过遗传算法变更过参数的排名条件
     * @return 回测结果
     */
    public TraceBackInfo optimize(List<FilterCondition> filterConditions, List<RankCondition> rankConditions) throws IOException, UnhandleBlockTypeException, DataSourceFirstDayException, NoDataWithinException, DateNotWithinException;



}
