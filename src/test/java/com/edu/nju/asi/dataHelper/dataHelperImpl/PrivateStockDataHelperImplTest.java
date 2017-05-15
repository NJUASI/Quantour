package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.PrivateStockDataHelper;
import com.edu.nju.asi.model.PrivateStock;
import com.edu.nju.asi.model.OptionalStockID;
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
        assertEquals("000001",list.get(0).getOptionalStockID().getStockCode());
        assertEquals("000002",list.get(1).getOptionalStockID().getStockCode());
        assertEquals("000011",list.get(2).getOptionalStockID().getStockCode());
    }

    @Test
    public void addPrivateStock() throws Exception {
        privateStockDataHelper.addPrivateStock(new OptionalStockID("ByronDong","000001"));
        privateStockDataHelper.addPrivateStock(new OptionalStockID("ByronDong","000002"));
        privateStockDataHelper.addPrivateStock(new OptionalStockID("ByronDong","000011"));
        privateStockDataHelper.addPrivateStock(new OptionalStockID("Harvey","000002"));
        privateStockDataHelper.addPrivateStock(new OptionalStockID("Harvey","000011"));
        privateStockDataHelper.addPrivateStock(new OptionalStockID("61990","000003"));
        privateStockDataHelper.addPrivateStock(new OptionalStockID("CharlesFeng","000001"));
        privateStockDataHelper.addPrivateStock(new OptionalStockID("CharlesFeng","011111"));
        privateStockDataHelper.addPrivateStock(new OptionalStockID("高源","030020"));
        privateStockDataHelper.addPrivateStock(new OptionalStockID("高源","040078"));
    }

    @Test
    public void addPrivateStockAll() throws Exception {
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
        privateStockDataHelper.addPrivateStockAll(list);
    }

    @Test
    public void deletePrivateStock() throws Exception {
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("ByronDong","000001"));
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("ByronDong","000002"));
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("ByronDong","000011"));
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("Harvey","000002"));
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("Harvey","000011"));
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("61990","000003"));
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("CharlesFeng","000001"));
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("CharlesFeng","011111"));
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("高源","030020"));
        privateStockDataHelper.deletePrivateStock(new OptionalStockID("高源","040078"));
    }

    @Test
    public void deletePrivateStockAll() throws Exception {
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
        privateStockDataHelper.deletePrivateStockAll(list);
    }
}