package com.edu.nju.asi.service.serviceImpl.optimizationService;

import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.serviceImpl.traceBackService.TraceBackServiceImpl;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.AdjustCriteria;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.OptimizationCriteria;
import com.edu.nju.asi.utilities.enums.*;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Harvey on 2017/6/13.
 */
public class GenEngineTest {
    GenEngine genEngine;
    TraceBackService traceBackService;
    LocalDate start;
    LocalDate end;

    @Before
    public void setUp() throws IOException {
        traceBackService = new TraceBackServiceImpl();
        start = LocalDate.of(2017,2,1);
        end = LocalDate.of(2017,5,29);
        genEngine = new GenEngine();
    }

    @Test
    public void optimization() throws UnhandleBlockTypeException, DateNotWithinException, DataSourceFirstDayException, NoDataWithinException, IOException {
        List<BlockType> blockTypes = new LinkedList<>();
        blockTypes.add(BlockType.ZB);
        blockTypes.add(BlockType.CYB);
        blockTypes.add(BlockType.ZXB);


        List<IndustryType> industryTypes = new ArrayList<>();
        List<AreaType> areaTypes = new ArrayList<>();

        industryTypes.add(IndustryType.金融行业);
        areaTypes.add(AreaType.all);

        //筛选条件
        List<FilterCondition> filterConditions = new ArrayList<>();
//        filterConditions.add(new FilterCondition(IndicatorType.TURNOVER_RATE, ComparatorType.RANK_MAX, 10, 5));

        //排名条件
        List<RankCondition> rankConditions = new ArrayList<>();
        rankConditions.add(new RankCondition(IndicatorType.TURNOVER_RATE, RankType.DESC_RANK, 1, 5));

        //择时条件
        List<MarketSelectingCondition> marketSelectingConditions = new ArrayList<>();
//        marketSelectingConditions.add(new MarketSelectingCondition(MarketSelectingType.MA, "上证指数", 1, 3, 20, 0));

        TraceBackCriteria criteria = new TraceBackCriteria(start, end, 10, new StockPoolCriteria(StType.EXCLUDE, blockTypes, industryTypes, areaTypes), 5,
                "沪深300", false, filterConditions, rankConditions, marketSelectingConditions, 0.5, 1, 1);


        List<AdjustCriteria> filterAdjustCriteria = new ArrayList<>();
        List<AdjustCriteria> rankAdjustCriteria = new ArrayList<>();

        rankAdjustCriteria.add(new AdjustCriteria(0, 100, 2));

        OptimizationCriteria optimizationCriteria = new OptimizationCriteria(criteria,filterAdjustCriteria,rankAdjustCriteria,TargetFuncType.ANNUALIZED_RETURN,51);

        Map<TraceBackCriteria, TraceBackInfo> map = genEngine.optimization(optimizationCriteria);
        System.out.println();


    }
}