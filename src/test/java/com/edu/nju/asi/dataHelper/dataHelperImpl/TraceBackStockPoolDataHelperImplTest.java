package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.TraceBackStockPoolDataHelper;
import com.edu.nju.asi.model.OptionalStockID;
import com.edu.nju.asi.model.TraceBackStockPool;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/14.
 */
public class TraceBackStockPoolDataHelperImplTest {

    private TraceBackStockPoolDataHelper traceBackStockPoolDataHelper;

    @Before
    public void setUp() throws Exception {
        traceBackStockPoolDataHelper = HelperManager.traceBackStockPoolDataHelper;
    }

    @Test
    public void getTraceBackStockPool() throws Exception {
        List<TraceBackStockPool> list = traceBackStockPoolDataHelper.getTraceBackStockPool("ByronDong");
        assertEquals("000001",list.get(0).getOptionalStockID().getStockCode());
        assertEquals("000002",list.get(1).getOptionalStockID().getStockCode());
        assertEquals("000011",list.get(2).getOptionalStockID().getStockCode());
    }

    @Test
    public void getTraceBackStockPoolCodes() throws Exception {
        List<String> list = traceBackStockPoolDataHelper.getTraceBackStockPoolCodes("ByronDong");
        assertEquals("000001",list.get(0));
        assertEquals("000002",list.get(1));
        assertEquals("000011",list.get(2));
    }

    @Test
    public void addTraceBackStock() throws Exception {
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("ByronDong","000001"),"深发展A"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("ByronDong","000002"),"沪深A股"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("ByronDong","000011"),"南京A股"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("Harvey","000002"),"沪深A股"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("Harvey","000011"),"南京A股"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("61990","000003"),"深宝宝A"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("CharlesFeng","000001"),"深发展A"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("CharlesFeng","011111"),"武汉C股"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("高源","030020"),"上海财经"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockPool(new OptionalStockID("高源","040078"),"网易财经"));
    }

    @Test
    public void addTraceBackStockAll() throws Exception {
        List<TraceBackStockPool> list = new ArrayList<>();
        list.add(new TraceBackStockPool(new OptionalStockID("ByronDong","000001"),"深发展A"));
        list.add(new TraceBackStockPool(new OptionalStockID("ByronDong","000002"),"沪深A股"));
        list.add(new TraceBackStockPool(new OptionalStockID("ByronDong","000011"),"南京A股"));
        list.add(new TraceBackStockPool(new OptionalStockID("Harvey","000002"),"沪深A股"));
        list.add(new TraceBackStockPool(new OptionalStockID("Harvey","000011"),"南京A股"));
        list.add(new TraceBackStockPool(new OptionalStockID("61990","000003"),"深宝宝A"));
        list.add(new TraceBackStockPool(new OptionalStockID("CharlesFeng","000001"),"深发展A"));
        list.add(new TraceBackStockPool(new OptionalStockID("CharlesFeng","011111"),"武汉C股"));
        list.add(new TraceBackStockPool(new OptionalStockID("高源","030020"),"上海财经"));
        list.add(new TraceBackStockPool(new OptionalStockID("高源","040078"),"网易财经"));
        traceBackStockPoolDataHelper.addTraceBackStockAll(list);
    }

    @Test
    public void deleteTraceBackStock() throws Exception {
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("ByronDong","000001"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("ByronDong","000002"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("ByronDong","000011"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("Harvey","000002"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("Harvey","000011"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("61990","000003"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("CharlesFeng","000001"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("CharlesFeng","011111"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("高源","030020"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new OptionalStockID("高源","040078"));
    }

    @Test
    public void deleteTraceBackStockAll() throws Exception {
        List<OptionalStockID> list = new ArrayList<>();
        list.add(new OptionalStockID("ByronDong","000001"));
        list.add(new OptionalStockID("ByronDong","000002"));
        list.add(new OptionalStockID("ByronDong","000011"));
        list.add(new OptionalStockID("Harvey","000002"));
        list.add(new OptionalStockID("Harvey","000011"));
        list.add(new OptionalStockID("61990","000003"));
        list.add(new OptionalStockID("CharlesFeng","000001"));
        list.add(new OptionalStockID("CharlesFeng","011111"));
        list.add(new OptionalStockID("高源","030020"));
        list.add(new OptionalStockID("高源","040078"));
        traceBackStockPoolDataHelper.deleteTraceBackStockAll(list);
    }
}