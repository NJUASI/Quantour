package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.PrivateStockDataHelper;
import com.edu.nju.asi.model.PrivateStock;
import com.edu.nju.asi.model.PrivateStockID;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/11.
 */
public class PrivateStockDataHelperImplTest {

    private PrivateStockDataHelper privateStockDataHelper;

    @Before
    public void setUp() throws Exception {
        privateStockDataHelper = HelperManager.privateStockDataHelper;
    }

    @Test
    public void getPrivateStock() throws Exception {
        List<PrivateStock> list = privateStockDataHelper.getPrivateStock("ByronDong");
        assertEquals("000001",list.get(0).getPrivateStockID().getStockCode());
        assertEquals("000002",list.get(1).getPrivateStockID().getStockCode());
        assertEquals("000011",list.get(2).getPrivateStockID().getStockCode());
    }

    @Test
    public void addPrivateStock() throws Exception {
        privateStockDataHelper.addPrivateStock(new PrivateStockID("ByronDong","000001"));
        privateStockDataHelper.addPrivateStock(new PrivateStockID("ByronDong","000002"));
        privateStockDataHelper.addPrivateStock(new PrivateStockID("ByronDong","000011"));
        privateStockDataHelper.addPrivateStock(new PrivateStockID("Harvey","000002"));
        privateStockDataHelper.addPrivateStock(new PrivateStockID("Harvey","000011"));
        privateStockDataHelper.addPrivateStock(new PrivateStockID("61990","000003"));
        privateStockDataHelper.addPrivateStock(new PrivateStockID("CharlesFeng","000001"));
        privateStockDataHelper.addPrivateStock(new PrivateStockID("CharlesFeng","011111"));
        privateStockDataHelper.addPrivateStock(new PrivateStockID("高源","030020"));
        privateStockDataHelper.addPrivateStock(new PrivateStockID("高源","040078"));
    }

    @Test
    public void addPrivateStockAll() throws Exception {
        List<PrivateStockID> list = new ArrayList<>();
        list.add(new PrivateStockID("ByronDong","000001"));
        list.add(new PrivateStockID("ByronDong","000002"));
        list.add(new PrivateStockID("ByronDong","000011"));
        list.add(new PrivateStockID("Harvey","000002"));
        list.add(new PrivateStockID("Harvey","000011"));
        list.add(new PrivateStockID("61990","000003"));
        list.add(new PrivateStockID("CharlesFeng","000001"));
        list.add(new PrivateStockID("CharlesFeng","011111"));
        list.add(new PrivateStockID("高源","030020"));
        list.add(new PrivateStockID("高源","040078"));
        privateStockDataHelper.addPrivateStockAll(list);
    }

    @Test
    public void deletePrivateStock() throws Exception {
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("ByronDong","000001"));
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("ByronDong","000002"));
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("ByronDong","000011"));
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("Harvey","000002"));
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("Harvey","000011"));
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("61990","000003"));
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("CharlesFeng","000001"));
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("CharlesFeng","011111"));
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("高源","030020"));
        privateStockDataHelper.deletePrivateStock(new PrivateStockID("高源","040078"));
    }

    @Test
    public void deletePrivateStockAll() throws Exception {
        List<PrivateStockID> list = new ArrayList<>();
        list.add(new PrivateStockID("ByronDong","000001"));
        list.add(new PrivateStockID("ByronDong","000002"));
        list.add(new PrivateStockID("ByronDong","000011"));
        list.add(new PrivateStockID("Harvey","000002"));
        list.add(new PrivateStockID("Harvey","000011"));
        list.add(new PrivateStockID("61990","000003"));
        list.add(new PrivateStockID("CharlesFeng","000001"));
        list.add(new PrivateStockID("CharlesFeng","011111"));
        list.add(new PrivateStockID("高源","030020"));
        list.add(new PrivateStockID("高源","040078"));
        privateStockDataHelper.deletePrivateStockAll(list);
    }

}