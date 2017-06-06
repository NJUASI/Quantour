package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.UserDataHelper;
import com.edu.nju.asi.model.Strategy;
import com.edu.nju.asi.model.User;
import com.edu.nju.asi.utilities.util.MD5Util;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/5/11.
 */
public class UserDataHelperImplTest {
    private UserDataHelper userDataHelper;

    @Before
    public void setUp() throws Exception {
        userDataHelper = HelperManager.userDataHelper;
    }

    @Test
    public void add() throws Exception {
        User user1 = new User();
        user1.setUserName("ByronDong");
        user1.setPassword(MD5Util.encodeMD5("qwertyuiop123456"));

        User user2 = new User();
        user2.setUserName("Harvey Gong");
        user2.setPassword(MD5Util.encodeMD5("asdfghjkl789012"));

        User user3 = new User();
        user3.setUserName("61990");
        user3.setPassword(MD5Util.encodeMD5("zxcvbnm345678"));

        User user4 = new User();
        user4.setUserName("CharlesFeng47");
        user4.setPassword(MD5Util.encodeMD5("zxcvbnm901234"));

        userDataHelper.add(user1);
        userDataHelper.add(user2);
        userDataHelper.add(user3);
        userDataHelper.add(user4);
    }

    @Test
    public void get() throws Exception {
        assertEquals("8e523cd5ef475ab6834f0598f4a502f8",userDataHelper.get("ByronDong").getPassword());
//        assertEquals("2fbed987fcaedad037df73d70cd5e422",userDataHelper.get("Harvey Gong").getPassword());
//        assertEquals("b7bfe0c070d6fb3d9acc375d317dcfb5",userDataHelper.get("61990").getPassword());
//        assertEquals("e78a98a93547e180cc7bf5323f1b6b66",userDataHelper.get("CharlesFeng47").getPassword());
    }

    @Test
    public void update() throws Exception {
        User user4 = new User();
        user4.setUserName("CharlesFeng47");
        user4.setPassword(MD5Util.encodeMD5("qwertyuiop0987654321"));
        userDataHelper.update(user4);
    }

    @Test
    public void getAllUserNames() throws Exception {
        List<String> list = userDataHelper.getAllUserNames();
        assertEquals("ByronDong",list.get(1));
        assertEquals("Harvey Gong",list.get(3));
        assertEquals("61990",list.get(0));
        assertEquals("CharlesFeng47",list.get(2));
    }

    @Test
    public void addStrategy() throws Exception {
        Strategy strategy1 = new Strategy(LocalDate.of(2017,1,1)
                ,"ByronDong",true,"涨幅","hhhhhhhhhhh");
        strategy1.setStrategyID("小策略");
        Strategy strategy2 = new Strategy(LocalDate.of(2017,3,1)
                ,"CharlesFeng47",false,"乖离率","aaaaaaaaaaaaaa");
        strategy2.setStrategyID("大策略");
        userDataHelper.addStrategyByCreator("ByronDong",strategy1);
        userDataHelper.addStrategyByChecker("CharlesFeng47","小策略");
        userDataHelper.addStrategyByChecker("Harvey Gong",strategy1.getStrategyID());
//        userDataHelper.addStrategyByChecker("Harvey Gong","大策略");
    }

    @Test
    public void updateStrategy() throws Exception {
        Strategy strategy1 = new Strategy(LocalDate.of(2017,1,1)
                ,"Byron",true,"涨幅","cccccccccc");
        strategy1.setStrategyID("小策略");
        userDataHelper.updateStrategy("Byron",strategy1);
    }

    @Test
    public void deleteStrategy() throws Exception {
        Strategy strategy1 = new Strategy(LocalDate.of(2017,1,1)
                ,"Byron",true,"涨幅","hhhhhhhhhhh");
        strategy1.setStrategyID("小策略");
        Strategy strategy2 = new Strategy(LocalDate.of(2017,3,1)
                ,"Dong",false,"乖离率","aaaaaaaaaaaaaa");
        strategy2.setStrategyID("大策略");
        userDataHelper.deleteStrategy("Dong",strategy2.getStrategyID());
    }

    @Test
    public void getStrategy() throws Exception {
        List<Strategy> strategies = userDataHelper.getStrategy("CharlesFeng47");
        Strategy strategy = strategies.get(0);
        assertEquals("涨幅",strategy.getContent());
        assertEquals("ByronDong",strategy.getCreater());
        assertEquals(LocalDate.of(2017,1,1),strategy.getDate());
        assertEquals("hhhhhhhhhhh",strategy.getDescription());
        assertEquals(true,strategy.isPrivate());
    }

}