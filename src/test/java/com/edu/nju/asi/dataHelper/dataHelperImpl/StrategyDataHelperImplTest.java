package com.edu.nju.asi.dataHelper.dataHelperImpl;

import com.edu.nju.asi.dataHelper.HelperManager;
import com.edu.nju.asi.dataHelper.StrategyDataHelper;
import com.edu.nju.asi.model.Strategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * StrategyDataHelperImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 9, 2017</pre>
 */
public class StrategyDataHelperImplTest {

    StrategyDataHelper helper;

    @Before
    public void before() throws Exception {
        helper = HelperManager.strategyDataHelper;
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getAllStrategy()
     */
    @Test
    public void testGetAllStrategy() throws Exception {
        List<Strategy> strategies = helper.getAllStrategy();
        System.out.println(strategies.get(0).getUsers());
    }

    /**
     * Method: getStrategy(String strategyID)
     */
    @Test
    public void testGetStrategy() throws Exception {
        Strategy strategy1 = helper.getStrategy("小策略");
        Strategy strategy2 = helper.getStrategy("qwerty");

    }

    /**
     * Method: isExist(String strategyID)
     */
    @Test
    public void testIsExist() throws Exception {
    }


} 
