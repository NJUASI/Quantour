package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockSearchDataHelper;
import com.edu.nju.asi.model.StockSearch;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/11.
 */
public class StockSearchDataHelperImplTest {

    private StockSearchDataHelper stockSearchDataHelper;

    @Before
    public void setUp() throws Exception {
        stockSearchDataHelper = HelperManager.stockSearchDataHelper;
    }

    @Test
    @Ignore
    public void createDB(){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    @Test
    public void getAllStocksFirstLetters() throws Exception {
        List<StockSearch> list = stockSearchDataHelper.getAllStocksFirstLetters();

        assertEquals("000001",list.get(0).getCode());
        assertEquals("sfza",list.get(0).getFirstLetters());
        assertEquals("深发展A",list.get(0).getName());

        assertEquals("000002",list.get(1).getCode());
        assertEquals("hsag",list.get(1).getFirstLetters());
        assertEquals("沪深A股",list.get(1).getName());

        assertEquals("000003",list.get(2).getCode());
        assertEquals("njbg",list.get(2).getFirstLetters());
        assertEquals("南京B股",list.get(2).getName());

        assertEquals("000011",list.get(3).getCode());
        assertEquals("sbbag",list.get(3).getFirstLetters());
        assertEquals("深宝宝A股",list.get(3).getName());

    }

    @Test
    public void getAllStocksCode() throws Exception {
        Map<String,String> map = stockSearchDataHelper.getAllStocksCode();

        assertEquals("深发展A",map.get("000001"));
        assertEquals("沪深A股",map.get("000002"));
        assertEquals("南京B股",map.get("000003"));
        assertEquals("深宝宝A股",map.get("000011"));
    }

    @Test
    public void getAllStocksName() throws Exception {

        Map<String,String> map = stockSearchDataHelper.getAllStocksName();

        assertEquals("000001",map.get("深发展A"));
        assertEquals("000002",map.get("沪深A股"));
        assertEquals("000003",map.get("南京B股"));
        assertEquals("000011",map.get("深宝宝A股"));
    }

    @Test
    public void addStockSearchAll() throws Exception {
        List<StockSearch> stockSearches = new ArrayList<>();
        StockSearch stockSearch1 = new StockSearch("深发展A","sfza");
        stockSearch1.setCode("000001");
        stockSearches.add(stockSearch1);
        StockSearch stockSearch2 = new StockSearch("沪深A股","hsag");
        stockSearch2.setCode("000002");
        stockSearches.add(stockSearch2);
        StockSearch stockSearch3 = new StockSearch("南京B股","njbg");
        stockSearch3.setCode("000003");
        stockSearches.add(stockSearch3);
        StockSearch stockSearch4 = new StockSearch("深宝宝A股","sbbag");
        stockSearch4.setCode("000011");
        stockSearches.add(stockSearch4);

        stockSearchDataHelper.addStockSearchAll(stockSearches);
    }

}