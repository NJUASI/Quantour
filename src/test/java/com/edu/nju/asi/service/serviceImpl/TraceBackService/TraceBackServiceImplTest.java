package com.edu.nju.asi.service.serviceImpl.TraceBackService;

import com.edu.nju.asi.infoCarrier.traceBack.*;
import com.edu.nju.asi.utilities.enums.BlockType;
import com.edu.nju.asi.utilities.enums.IndicatorType;
import com.edu.nju.asi.utilities.enums.ComparotorType;
import com.edu.nju.asi.utilities.enums.StType;
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
        start = LocalDate.of(2017,4,1);
        end = LocalDate.of(2017,5,1);
    }

    @Test
    public void setTraceBackService(){
        List<BlockType> blockTypes = new LinkedList<>();
        blockTypes.add(BlockType.ZB);

        List<FilterCondition> filterConditions = new ArrayList<>();
        filterConditions.add(new FilterCondition(IndicatorType.INCEREASE_AMOUNT, ComparotorType.RANK_MAX, 5, 1, 5));

        TraceBackCriteria criteria = new TraceBackCriteria(start, end, 5, 10, new StockPoolCriteria(StType.INCLUDE, blockTypes),
                "沪深300", filterConditions);

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