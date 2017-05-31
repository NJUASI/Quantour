package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StockSearchDataHelper;
import com.edu.nju.asi.model.SearchID;
import com.edu.nju.asi.model.StockSearch;
import com.edu.nju.asi.utilities.enums.Market;
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

        assertEquals("000001",list.get(0).getSearchID().getCode());
        assertEquals("sfza",list.get(0).getFirstLetters());
        assertEquals("深发展A",list.get(0).getSearchID().getName());
        assertEquals(Market.SZ,list.get(0).getSearchID().getMarket());

        assertEquals("000002",list.get(1).getSearchID().getCode());
        assertEquals("hsag",list.get(1).getFirstLetters());
        assertEquals("沪深A股",list.get(1).getSearchID().getName());
        assertEquals(Market.SZ,list.get(1).getSearchID().getMarket());

        assertEquals("000003",list.get(2).getSearchID().getCode());
        assertEquals("njbg",list.get(2).getFirstLetters());
        assertEquals("南京B股",list.get(2).getSearchID().getName());
        assertEquals(Market.SZ,list.get(2).getSearchID().getMarket());

        assertEquals("000011",list.get(3).getSearchID().getCode());
        assertEquals("sbbag",list.get(3).getFirstLetters());
        assertEquals("深宝宝A股",list.get(3).getSearchID().getName());
        assertEquals(Market.SZ,list.get(3).getSearchID().getMarket());

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
        StockSearch stockSearch1 = new StockSearch(new SearchID("000001","深发展A",Market.SZ),"sfza");
        stockSearches.add(stockSearch1);
        StockSearch stockSearch2 = new StockSearch(new SearchID("000002","沪深A股",Market.SZ),"hsag");
        stockSearches.add(stockSearch2);
        StockSearch stockSearch3 = new StockSearch(new SearchID("000003","南京B股",Market.SZ),"njbg");
        stockSearches.add(stockSearch3);
        StockSearch stockSearch4 = new StockSearch(new SearchID("000011","深宝宝A股",Market.SZ),"sbbag");
        stockSearches.add(stockSearch4);

        stockSearchDataHelper.addStockSearchAll(stockSearches);
    }

    @Test
    public void addClickAmount() throws Exception {
        stockSearchDataHelper.addClickAmount(new SearchID("000001","平安银行",Market.SZ));
    }

    @Test
    public void getRankingList() throws Exception {
        List<StockSearch> list = stockSearchDataHelper.getRankingList(2);
        assertEquals(2,list.get(0).getClickAmount());
    }

    @Test
    public void getClickRate() throws Exception {
        assertEquals(1.0,stockSearchDataHelper.getClickRate(new SearchID("000001","平安银行",Market.SZ)),0);
    }

}