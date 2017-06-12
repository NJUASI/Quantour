package com.edu.nju.asi.service.serviceImpl.TraceBackService;

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
        start = LocalDate.of(2017,2,1);
        end = LocalDate.of(2017,5,29);
    }

    @Test
    public void setTraceBackService(){
        List<BlockType> blockTypes = new LinkedList<>();
        blockTypes.add(BlockType.ZXB);

        //筛选条件
        List<FilterCondition> filterConditions = new ArrayList<>();
//        filterConditions.add(new FilterCondition(IndicatorType.TURNOVER_RATE, ComparatorType.RANK_MAX, 10, 5));

        //排名条件
        List<RankCondition> rankConditions = new ArrayList<>();
        rankConditions.add(new RankCondition(IndicatorType.TURNOVER_RATE, RankType.DESC_RANK, 1, 5));

        //择时条件
        List<MarketSelectingCondition> marketSelectingConditions = new ArrayList<>();

        TraceBackCriteria criteria = new TraceBackCriteria(start, end, 10, new StockPoolCriteria(StType.EXCLUDE, blockTypes),5,
                "沪深300", filterConditions, rankConditions, marketSelectingConditions, 0.5, 1, 1);

        try {
             TraceBackInfo traceBackInfo = traceBackService.traceBack(criteria);
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