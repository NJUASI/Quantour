package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.infoCarrier.traceBack.FormateAndPickCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.StockPoolCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.utilities.enums.BlockType;
import com.edu.nju.asi.utilities.enums.FormateType;
import com.edu.nju.asi.utilities.enums.PickType;
import com.edu.nju.asi.utilities.enums.StType;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.junit.Before;
import org.junit.Test;
import com.edu.nju.asi.service.TraceBackService;

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
    List<String> stockCodes;
    TraceBackCriteria TraceBackCriteria;

    @Before
    public void setUp() throws IOException {
        traceBackService = new TraceBackServiceImpl();
        start = LocalDate.of(2017,5,1);
        end = LocalDate.of(2017,5,10);

        //设置TraceBackCriteriaVO
        TraceBackCriteria = new TraceBackCriteria();
        TraceBackCriteria.baseStockName = "深发展Ａ";
        TraceBackCriteria.startDate = start;
        TraceBackCriteria.endDate = end;


        //设置自选股的股票代码
        stockCodes = new ArrayList<>();
        stockCodes.add("000001");
        stockCodes.add("000011");
    }

    @Test
    public void getStrategyCumulativeReturn() throws Exception {
    }

//    @Test
//    public void getCustomizedCumulativeReturn() throws Exception {
//
//        List<CumulativeReturn> cumulativeReturnVOS = traceBackService.getCustomizedCumulativeReturn(TraceBackCriteria.startDate,TraceBackCriteria.endDate,stockCodes, stockMap);
//        assertEquals(-0.021,cumulativeReturnVOS.get(1).cumulativeReturn,0.001);
//
//    }

    @Test
    public void setTraceBackService(){
        List<BlockType> blockTypes = new LinkedList<>();
        blockTypes.add(BlockType.CYB);

        TraceBackCriteria criteria = new TraceBackCriteria(start, end, 10, 5, new StockPoolCriteria(StType.INCLUDE, blockTypes),"沪深300",false,
                new FormateAndPickCriteria(FormateType.BIAS, PickType.RANK_MAX, 10));

        try {
            traceBackService.traceBack(criteria, null);
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


    @Test
    public void getNumericalVal() throws Exception {
    }

    @Test
    public void getRelativeIndexReturn() throws Exception {
    }

}