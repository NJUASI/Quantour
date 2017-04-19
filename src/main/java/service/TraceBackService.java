package service;

import utilities.exceptions.*;
import vo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by harvey on 17-3-28.
 *
 * 回测功能的接口
 */
public interface TraceBackService {


    /**
     *
     * @param traceBackCriteriaVO 回测标准
     * @param stockPool 自选股的代码列表
     * @return 回测所要展示的数据
     */
    TraceBackVO traceBack(TraceBackCriteriaVO traceBackCriteriaVO, List<String> stockPool) throws IOException, NoDataWithinException, DateNotWithinException, DateShortException, CodeNotFoundException, NoMatchEnumException, UnhandleBlockTypeException, DataSourceFirstDayException, InvalidInputException;

}
