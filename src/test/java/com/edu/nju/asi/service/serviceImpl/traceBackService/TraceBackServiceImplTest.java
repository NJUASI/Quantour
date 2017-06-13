package com.edu.nju.asi.service.serviceImpl.traceBackService;

import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.utilities.enums.*;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.junit.Before;
import com.edu.nju.asi.service.TraceBackService;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by harvey on 17-4-2.
 */
public class TraceBackServiceImplTest {

    TraceBackService traceBackService;
    LocalDate start;
    LocalDate end;

    @Before
    public void setUp() throws IOException {
        traceBackService = new TraceBackServiceImpl();
        start = LocalDate.of(2017,4,21);
        end = LocalDate.of(2017,6,9);
    }

    @Test
    public void setTraceBackService(){
        List<BlockType> blockTypes = new LinkedList<>();
        blockTypes.add(BlockType.CYB);

        //筛选条件
        List<FilterCondition> filterConditions = new ArrayList<>();
        filterConditions.add(new FilterCondition(IndicatorType.SWING_RATE, ComparatorType.RANK_MAX, 10, 1));

        //排名条件
        List<RankCondition> rankConditions = new ArrayList<>();
//        rankConditions.add(new RankCondition(IndicatorType.MACD_DIF, RankType.DESC_RANK, 1, 5));

        //择时条件
        List<MarketSelectingCondition> marketSelectingConditions = new ArrayList<>();
//        marketSelectingConditions.add(new MarketSelectingCondition(MarketSelectingType.MAVol, "上证指数", 1, 5, 60, 0));

        TraceBackCriteria criteria = new TraceBackCriteria(start, end, 5, new StockPoolCriteria(StType.INCLUDE, blockTypes),5,
                "沪深300", false, filterConditions, rankConditions, marketSelectingConditions, 0.5, 1, 1);

        List<String> customisedStockPool = new LinkedList<>();
        customisedStockPool.add("000001");

        try {
             TraceBackInfo traceBackInfo = traceBackService.traceBack(criteria, customisedStockPool);
             System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataSourceFirstDayException e) {
            e.printStackTrace();
        } catch (DateNotWithinException e) {
            e.printStackTrace();
        } catch (NoDataWithinException e) {
            e.printStackTrace();
        } catch (UnhandleBlockTypeException e) {
            e.printStackTrace();
        }
    }
}