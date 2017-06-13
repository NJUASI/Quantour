package com.edu.nju.asi.service.serviceImpl.stockService;

import com.edu.nju.asi.model.Stock;
import com.edu.nju.asi.utilities.enums.StocksSortCriteria;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * StockSortComparatorFactory Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 8, 2017</pre>
 */
public class StockSortComparatorFactoryTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: createSortComparator(StocksSortCriteria comparisionCriteria)
     */
    @Test
    public void testCreateSortComparator() throws Exception {
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        Stock stock3 = new Stock();

//        stock1.setStockID(new StockID("123456", LocalDate.now() ));
//        stock2.setStockID(new StockID("000300", LocalDate.now() ));
//        stock3.setStockID(new StockID("000399", LocalDate.now() ));

//        stock1.setOpen(1.2);
//        stock2.setOpen(0.5);
//        stock3.setOpen(0.88);
//
//        stock1.setTransactionAmount("258852703.32");
//        stock2.setTransactionAmount("347527135.92");
//        stock3.setTransactionAmount("223742895.38");

        stock1.setVolume("14791098");
        stock2.setVolume("13315115");
        stock3.setVolume("24408005");

        List<Stock> a = new LinkedList<>();
        a.add(stock1);
        a.add(stock2);
        a.add(stock3);

        Comparator<Stock> comparator = new StockSortComparatorFactory().createSortComparator(StocksSortCriteria.VOLUME_DES);
        a.sort(comparator);



    }


} 
