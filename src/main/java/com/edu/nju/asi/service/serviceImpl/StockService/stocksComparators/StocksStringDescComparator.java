package com.edu.nju.asi.service.serviceImpl.StockService.stocksComparators;

import com.edu.nju.asi.model.Stock;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * Created by cuihua on 2017/6/8.
 */
public class StocksStringDescComparator implements Comparator<Stock> {

    private String stockAttribute;

    public StocksStringDescComparator(String stockAttribute) {
        this.stockAttribute = stockAttribute;
    }

    @Override
    public int compare(Stock o1, Stock o2) {
        double tempResult = 0;

        // 反射拿到对象的值,并抑制java对修饰符的检查
        Class<Stock> stockClass = Stock.class;
        try {
            Field field = stockClass.getDeclaredField(stockAttribute);
            field.setAccessible(true);

            tempResult = field.get(o1).toString().compareTo(field.get(o2).toString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (tempResult < 0) return 1;
        else if (tempResult > 0) return -1;
        else return 0;
    }
}
