package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.TraceBackStockPoolDataHelper;
import com.edu.nju.asi.model.TraceBackStockID;
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
        assertEquals("000001",list.get(0).getTraceBackStockID().getStockCode());
        assertEquals("000002",list.get(1).getTraceBackStockID().getStockCode());
        assertEquals("000011",list.get(2).getTraceBackStockID().getStockCode());
    }

    @Test
    public void addTraceBackStock() throws Exception {
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("ByronDong","000001"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("ByronDong","000002"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("ByronDong","000011"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("Harvey","000002"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("Harvey","000011"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("61990","000003"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("CharlesFeng","000001"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("CharlesFeng","011111"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("高源","030020"));
        traceBackStockPoolDataHelper.addTraceBackStock(new TraceBackStockID("高源","040078"));
    }

    @Test
    public void addTraceBackStockAll() throws Exception {
        List<TraceBackStockID> list = new ArrayList<>();
        list.add(new TraceBackStockID("ByronDong","000001"));
        list.add(new TraceBackStockID("ByronDong","000002"));
        list.add(new TraceBackStockID("ByronDong","000011"));
        list.add(new TraceBackStockID("Harvey","000002"));
        list.add(new TraceBackStockID("Harvey","000011"));
        list.add(new TraceBackStockID("61990","000003"));
        list.add(new TraceBackStockID("CharlesFeng","000001"));
        list.add(new TraceBackStockID("CharlesFeng","011111"));
        list.add(new TraceBackStockID("高源","030020"));
        list.add(new TraceBackStockID("高源","040078"));
        traceBackStockPoolDataHelper.addTraceBackStockAll(list);
    }

    @Test
    public void deleteTraceBackStock() throws Exception {
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("ByronDong","000001"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("ByronDong","000002"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("ByronDong","000011"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("Harvey","000002"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("Harvey","000011"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("61990","000003"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("CharlesFeng","000001"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("CharlesFeng","011111"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("高源","030020"));
        traceBackStockPoolDataHelper.deleteTraceBackStock(new TraceBackStockID("高源","040078"));
    }

    @Test
    public void deleteTraceBackStockAll() throws Exception {
        List<TraceBackStockID> list = new ArrayList<>();
        list.add(new TraceBackStockID("ByronDong","000001"));
        list.add(new TraceBackStockID("ByronDong","000002"));
        list.add(new TraceBackStockID("ByronDong","000011"));
        list.add(new TraceBackStockID("Harvey","000002"));
        list.add(new TraceBackStockID("Harvey","000011"));
        list.add(new TraceBackStockID("61990","000003"));
        list.add(new TraceBackStockID("CharlesFeng","000001"));
        list.add(new TraceBackStockID("CharlesFeng","011111"));
        list.add(new TraceBackStockID("高源","030020"));
        list.add(new TraceBackStockID("高源","040078"));
        traceBackStockPoolDataHelper.deleteTraceBackStockAll(list);
    }

    @Test
    public void updateTraceBackStockPool() throws Exception {
        traceBackStockPoolDataHelper.updateTraceBackStockPool("HarveyGong","Harvey");
    }

}