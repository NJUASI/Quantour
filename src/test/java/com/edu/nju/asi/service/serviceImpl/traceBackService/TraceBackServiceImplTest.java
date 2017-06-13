package com.edu.nju.asi.service.serviceImpl.traceBackService;

import com.alibaba.fastjson.JSON;
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
        start = LocalDate.of(2017,5,10);
        end = LocalDate.of(2017,6,12);
    }

    @Test
    public void setTraceBackService(){

//        String bug1 = "{\"startDate\":\"2017-04-03\",\"endDate\":\"2017-06-13\",\"holdingPeriod\":\"5\",\"stockPoolCriteria\":{\"stType\":\"INCLUDE\",\"blockTypes\":[\"ZXB\"],\"industryTypes\":[\"金融行业\",\"房地产\",\"综合行业\",\"建筑建材\",\"玻璃行业\",\"家电行业\",\"纺织行业\",\"食品行业\",\"电子信息\",\"交通运输\",\"汽车制造\",\"商业百货\",\"电力行业\",\"酒店旅游\",\"机械行业\",\"农林牧渔\",\"电器行业\",\"电子器件\",\"石油行业\",\"有色金属\",\"生物制药\",\"医疗器械\",\"物资外贸\",\"传媒娱乐\",\"发电设备\",\"水泥行业\",\"塑料制品\",\"钢铁行业\",\"化纤行业\",\"农药化肥\",\"公路桥梁\",\"造纸行业\",\"化工行业\",\"环保行业\",\"煤炭行业\",\"酿酒行业\",\"供水供气\",\"开发区\",\"印刷包装\",\"纺织机械\",\"仪器仪表\",\"飞机制造\",\"其它行业\",\"家具行业\",\"摩托车\",\"服装鞋类\",\"陶瓷行业\",\"船舶制造\",\"次新股\",\"none\"],\"areaTypes\":[\"深圳\"]},\"maxHoldingNum\":\"5\",\"baseStockName\":\"沪深300\",\"isCustomized\":false,\"filterConditions\":[{\"indicatorType\":\"BIAS\",\"comparatorType\":\"RANK_MAX\",\"value\":\"10\",\"formativePeriod\":\"5\"}],\"rankConditions\":[],\"marketSelectingConditions\":[],\"adjustPositionPercent\":\"\",\"bearToBull_num\":\"\",\"bullToBear_num\":\"\"}\n";
        String bug = "{\"startDate\":\"2017-04-07\",\"endDate\":\"2017-06-13\",\"holdingPeriod\":\"5\",\"stockPoolCriteria\":{\"stType\":\"INCLUDE\",\"blockTypes\":[\"ZXB\"],\"industryTypes\":[\"金融行业\",\"房地产\",\"综合行业\",\"建筑建材\",\"玻璃行业\",\"家电行业\",\"纺织行业\",\"食品行业\",\"电子信息\",\"交通运输\",\"汽车制造\",\"商业百货\",\"电力行业\",\"酒店旅游\",\"机械行业\",\"农林牧渔\",\"电器行业\",\"电子器件\",\"石油行业\",\"有色金属\",\"生物制药\",\"医疗器械\",\"物资外贸\",\"传媒娱乐\",\"发电设备\",\"水泥行业\",\"塑料制品\",\"钢铁行业\",\"化纤行业\",\"农药化肥\",\"公路桥梁\",\"造纸行业\",\"化工行业\",\"环保行业\",\"煤炭行业\",\"酿酒行业\",\"供水供气\",\"开发区\",\"印刷包装\",\"纺织机械\",\"仪器仪表\",\"飞机制造\",\"其它行业\",\"家具行业\",\"摩托车\",\"服装鞋类\",\"陶瓷行业\",\"船舶制造\",\"次新股\",\"none\"],\"areaTypes\":[\"深圳\"]},\"maxHoldingNum\":\"5\",\"baseStockName\":\"沪深300\",\"isCustomized\":false,\"filterConditions\":[{\"indicatorType\":\"PE_TTM\",\"comparatorType\":\"RANK_MAX\",\"value\":\"10\",\"formativePeriod\":1}],\"rankConditions\":[],\"marketSelectingConditions\":[{\"type\":\"MACD\",\"baseStockName\":\"沪深300\",\"cycle\":\"10\",\"criteria1\":\"12\",\"criteria2\":\"26\",\"criteria3\":\"9\"}],\"adjustPositionPercent\":\"12\",\"bearToBull_num\":\"1\",\"bullToBear_num\":\"1\"}";
        TraceBackCriteria criteria = JSON.parseObject(bug, TraceBackCriteria.class);

//        List<BlockType> blockTypes = new LinkedList<>();
//        blockTypes.add(BlockType.CYB);
//
//        //筛选条件
//        List<FilterCondition> filterConditions = new ArrayList<>();
//        filterConditions.add(new FilterCondition(IndicatorType.SWING_RATE, ComparatorType.RANK_MAX, 10, 1));
//
//        //排名条件
//        List<RankCondition> rankConditions = new ArrayList<>();
////        rankConditions.add(new RankCondition(IndicatorType.MACD_DIF, RankType.DESC_RANK, 1, 5));
//
//        //择时条件
//        List<MarketSelectingCondition> marketSelectingConditions = new ArrayList<>();
////        marketSelectingConditions.add(new MarketSelectingCondition(MarketSelectingType.MAVol, "上证指数", 1, 5, 60, 0));
//
//        TraceBackCriteria criteria = new TraceBackCriteria(start, end, 5, new StockPoolCriteria(StType.INCLUDE, blockTypes),5,
//                "沪深300", false, filterConditions, rankConditions, marketSelectingConditions, 0.5, 1, 1);
//
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