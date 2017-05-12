package com.edu.nju.asi.service;

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
     * @param stockPool 自选股的代码列表
     * @return 回测所要展示的数据
     */
    TraceBackInfo traceBack(TraceBackCriteria traceBackCriteria, List<String> stockPool)  throws IOException, DataSourceFirstDayException, DateNotWithinException, NoDataWithinException, UnhandleBlockTypeException;

}
