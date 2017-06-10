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
        String strategyContent = "{\"startDate\":\"2017-04-01\",\"endDate\":\"2017-05-01\",\"holdingPeriod\":\"10\",\"stockPoolCriteria\":{\"stType\":\"EXCLUDE\",\"blockTypes\":[\"ZXB\"]},\"maxHoldingNum\":\"5\",\"baseStockName\":\"沪深300\",\"filterConditions\":[{\"indicatorType\":\"VOLUME\",\"comparatorType\":\"RANK_MAX\",\"value\":\"10\",\"formativePeriod\":\"5\"}],\"rankConditions\":[]}";
        Strategy strategy2 = new Strategy(LocalDate.of(2017,3,1),
                "CharlesFeng47",false, strategyContent,"aaaaaaaaaaaaaa", null);
        strategy2.setStrategyID("大策略");
        userDataHelper.addStrategyByCreator(strategy2);
        userDataHelper.addStrategyByChecker("ByronDong","大策略");
    }

    @Test
    public void updateStrategy() throws Exception {
        String strategyContent = "{\"startDate\":\"2017-04-01\",\"endDate\":\"2017-05-01\",\"holdingPeriod\":\"10\",\"stockPoolCriteria\":{\"stType\":\"EXCLUDE\",\"blockTypes\":[\"ZXB\"]},\"maxHoldingNum\":\"5\",\"baseStockName\":\"沪深300\",\"filterConditions\":[{\"indicatorType\":\"VOLUME\",\"comparatorType\":\"RANK_MAX\",\"value\":\"10\",\"formativePeriod\":\"5\"}],\"rankConditions\":[]}";
        String traceBackInfo = "{\"absoluteReturnPeriod\":{\"negativeNums\":{2.0:1,6.0:1},\"negativePeriodNum\":2,\"positiveNums\":{},\"positivePeriodsNum\":0,\"winRate\":0.0},\"baseCumulativeReturn\":[{\"cumulativeReturn\":0.0,\"currentDate\":\"2017-04-05\",\"isTraceBack\":true},{\"cumulativeReturn\":0.002897833758175005,\"currentDate\":\"2017-04-06\",\"isTraceBack\":false},{\"cumulativeReturn\":0.003872892016229294,\"currentDate\":\"2017-04-07\",\"isTraceBack\":false},{\"cumulativeReturn\":3.556044459346234E-4,\"currentDate\":\"2017-04-10\",\"isTraceBack\":false},{\"cumulativeReturn\":0.003834249285908599,\"currentDate\":\"2017-04-11\",\"isTraceBack\":false},{\"cumulativeReturn\":0.001582668101870278,\"currentDate\":\"2017-04-12\",\"isTraceBack\":false},{\"cumulativeReturn\":0.0030460403302247424,\"currentDate\":\"2017-04-13\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.004962679798573761,\"currentDate\":\"2017-04-14\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.0068356820921368616,\"currentDate\":\"2017-04-17\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.011744165033340567,\"currentDate\":\"2017-04-18\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.016557896228115637,\"currentDate\":\"2017-04-19\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.012085157161753676,\"currentDate\":\"2017-04-20\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.010590134425330036,\"currentDate\":\"2017-04-21\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.020729655801152876,\"currentDate\":\"2017-04-24\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.017956854392708124,\"currentDate\":\"2017-04-25\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.01675564768926177,\"currentDate\":\"2017-04-26\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.016316992766788604,\"currentDate\":\"2017-04-27\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.018305409537025794,\"currentDate\":\"2017-04-28\",\"isTraceBack\":false}],\"holdingDetails\":[{\"baseReturn\":-0.0166,\"buyNum\":0,\"endDate\":\"2017-04-19\",\"excessReturn\":0.0034,\"holdingNum\":0,\"periodSerial\":1,\"remainInvestment\":986.8,\"sellNum\":0,\"startDate\":\"2017-04-05\",\"strategyReturn\":-0.0132},{\"baseReturn\":-0.0018,\"buyNum\":0,\"endDate\":\"2017-04-28\",\"excessReturn\":-0.057,\"holdingNum\":0,\"periodSerial\":2,\"remainInvestment\":928.8,\"sellNum\":0,\"startDate\":\"2017-04-19\",\"strategyReturn\":-0.0588}],\"maxTraceBack\":{\"maxBaseTraceBackRate\":0.02460254781738217,\"maxEndDay\":\"2017-04-24\",\"maxEndIndex\":13,\"maxStartDay\":\"2017-04-11\",\"maxStartIndex\":4,\"maxStrategyTraceBackRate\":0.19959702449764916},\"relativeReturnPeriod\":{\"negativeNums\":{6.0:1},\"negativePeriodNum\":1,\"positiveNums\":{1.0:1},\"positivePeriodsNum\":1,\"winRate\":0.5},\"strategyCumulativeReturn\":[{\"cumulativeReturn\":0.0,\"currentDate\":\"2017-04-05\",\"isTraceBack\":false},{\"cumulativeReturn\":0.02189674345281345,\"currentDate\":\"2017-04-06\",\"isTraceBack\":false},{\"cumulativeReturn\":0.012578171759199286,\"currentDate\":\"2017-04-07\",\"isTraceBack\":false},{\"cumulativeReturn\":0.04172517250285823,\"currentDate\":\"2017-04-10\",\"isTraceBack\":false},{\"cumulativeReturn\":0.07600371093158631,\"currentDate\":\"2017-04-11\",\"isTraceBack\":true},{\"cumulativeReturn\":0.019656177170593203,\"currentDate\":\"2017-04-12\",\"isTraceBack\":false},{\"cumulativeReturn\":0.03710016548799322,\"currentDate\":\"2017-04-13\",\"isTraceBack\":false},{\"cumulativeReturn\":0.02291580219655276,\"currentDate\":\"2017-04-14\",\"isTraceBack\":false},{\"cumulativeReturn\":0.013661161897750018,\"currentDate\":\"2017-04-17\",\"isTraceBack\":false},{\"cumulativeReturn\":0.030646697602995543,\"currentDate\":\"2017-04-18\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.013200459159647293,\"currentDate\":\"2017-04-19\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.08847877157616824,\"currentDate\":\"2017-04-20\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.10996416916709228,\"currentDate\":\"2017-04-21\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.12359331356606285,\"currentDate\":\"2017-04-24\",\"isTraceBack\":true},{\"cumulativeReturn\":-0.11694406004552704,\"currentDate\":\"2017-04-25\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.0837395552272312,\"currentDate\":\"2017-04-26\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.11366298013224552,\"currentDate\":\"2017-04-27\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.07120491098995341,\"currentDate\":\"2017-04-28\",\"isTraceBack\":false}],\"traceBackNumVal\":{\"alpha\":-0.597430818928158,\"annualizedRateOfReturn\":-0.6905758337045702,\"baseAnnualizedRateOfReturn\":-0.2542698371665659,\"baseReturnVolatility\":0.06230100725739198,\"baseSharpeRatio\":-4.723356011738493,\"baseSumRate\":-0.018305409537025794,\"beta\":0.45245892701210816,\"informationRatio\":-0.8468991659428852,\"returnVolatility\":0.5148222685633816,\"sharpeRatio\":null,\"sumRate\":-0.07120491098995341},\"transferDayDetails\":[{\"buyDate\":\"2017-04-05\",\"buyPrice\":8.52,\"changeRate\":-0.008215962441314588,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002340\",\"stockName\":\"格林美\"},{\"buyDate\":\"2017-04-05\",\"buyPrice\":5.3,\"changeRate\":0.5943396226415093,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002204\",\"stockName\":\"大连重工\"},{\"buyDate\":\"2017-04-05\",\"buyPrice\":20.15,\"changeRate\":-0.5806451612903226,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002302\",\"stockName\":\"西部建设\"},{\"buyDate\":\"2017-04-05\",\"buyPrice\":9.98,\"changeRate\":-0.153306613226453,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002497\",\"stockName\":\"雅化集团\"},{\"buyDate\":\"2017-04-05\",\"buyPrice\":18.83,\"changeRate\":-0.5512480084970791,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002807\",\"stockName\":\"江阴银行\"}]}";

        Strategy strategy2 = new Strategy(LocalDate.of(2017,3,15),
                "CharlesFeng47",false, strategyContent,"bbb", traceBackInfo);
        strategy2.setStrategyID("大策略");
        assertEquals(true, userDataHelper.updateStrategy(strategy2));
    }

    @Test
    public void deleteStrategy() throws Exception {
        String strategyContent = "{\"startDate\":\"2017-04-01\",\"endDate\":\"2017-05-01\",\"holdingPeriod\":\"10\",\"stockPoolCriteria\":{\"stType\":\"EXCLUDE\",\"blockTypes\":[\"ZXB\"]},\"maxHoldingNum\":\"5\",\"baseStockName\":\"沪深300\",\"filterConditions\":[{\"indicatorType\":\"VOLUME\",\"comparatorType\":\"RANK_MAX\",\"value\":\"10\",\"formativePeriod\":\"5\"}],\"rankConditions\":[]}";
        String traceBackInfo = "{\"absoluteReturnPeriod\":{\"negativeNums\":{2.0:1,6.0:1},\"negativePeriodNum\":2,\"positiveNums\":{},\"positivePeriodsNum\":0,\"winRate\":0.0},\"baseCumulativeReturn\":[{\"cumulativeReturn\":0.0,\"currentDate\":\"2017-04-05\",\"isTraceBack\":true},{\"cumulativeReturn\":0.002897833758175005,\"currentDate\":\"2017-04-06\",\"isTraceBack\":false},{\"cumulativeReturn\":0.003872892016229294,\"currentDate\":\"2017-04-07\",\"isTraceBack\":false},{\"cumulativeReturn\":3.556044459346234E-4,\"currentDate\":\"2017-04-10\",\"isTraceBack\":false},{\"cumulativeReturn\":0.003834249285908599,\"currentDate\":\"2017-04-11\",\"isTraceBack\":false},{\"cumulativeReturn\":0.001582668101870278,\"currentDate\":\"2017-04-12\",\"isTraceBack\":false},{\"cumulativeReturn\":0.0030460403302247424,\"currentDate\":\"2017-04-13\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.004962679798573761,\"currentDate\":\"2017-04-14\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.0068356820921368616,\"currentDate\":\"2017-04-17\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.011744165033340567,\"currentDate\":\"2017-04-18\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.016557896228115637,\"currentDate\":\"2017-04-19\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.012085157161753676,\"currentDate\":\"2017-04-20\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.010590134425330036,\"currentDate\":\"2017-04-21\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.020729655801152876,\"currentDate\":\"2017-04-24\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.017956854392708124,\"currentDate\":\"2017-04-25\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.01675564768926177,\"currentDate\":\"2017-04-26\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.016316992766788604,\"currentDate\":\"2017-04-27\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.018305409537025794,\"currentDate\":\"2017-04-28\",\"isTraceBack\":false}],\"holdingDetails\":[{\"baseReturn\":-0.0166,\"buyNum\":0,\"endDate\":\"2017-04-19\",\"excessReturn\":0.0034,\"holdingNum\":0,\"periodSerial\":1,\"remainInvestment\":986.8,\"sellNum\":0,\"startDate\":\"2017-04-05\",\"strategyReturn\":-0.0132},{\"baseReturn\":-0.0018,\"buyNum\":0,\"endDate\":\"2017-04-28\",\"excessReturn\":-0.057,\"holdingNum\":0,\"periodSerial\":2,\"remainInvestment\":928.8,\"sellNum\":0,\"startDate\":\"2017-04-19\",\"strategyReturn\":-0.0588}],\"maxTraceBack\":{\"maxBaseTraceBackRate\":0.02460254781738217,\"maxEndDay\":\"2017-04-24\",\"maxEndIndex\":13,\"maxStartDay\":\"2017-04-11\",\"maxStartIndex\":4,\"maxStrategyTraceBackRate\":0.19959702449764916},\"relativeReturnPeriod\":{\"negativeNums\":{6.0:1},\"negativePeriodNum\":1,\"positiveNums\":{1.0:1},\"positivePeriodsNum\":1,\"winRate\":0.5},\"strategyCumulativeReturn\":[{\"cumulativeReturn\":0.0,\"currentDate\":\"2017-04-05\",\"isTraceBack\":false},{\"cumulativeReturn\":0.02189674345281345,\"currentDate\":\"2017-04-06\",\"isTraceBack\":false},{\"cumulativeReturn\":0.012578171759199286,\"currentDate\":\"2017-04-07\",\"isTraceBack\":false},{\"cumulativeReturn\":0.04172517250285823,\"currentDate\":\"2017-04-10\",\"isTraceBack\":false},{\"cumulativeReturn\":0.07600371093158631,\"currentDate\":\"2017-04-11\",\"isTraceBack\":true},{\"cumulativeReturn\":0.019656177170593203,\"currentDate\":\"2017-04-12\",\"isTraceBack\":false},{\"cumulativeReturn\":0.03710016548799322,\"currentDate\":\"2017-04-13\",\"isTraceBack\":false},{\"cumulativeReturn\":0.02291580219655276,\"currentDate\":\"2017-04-14\",\"isTraceBack\":false},{\"cumulativeReturn\":0.013661161897750018,\"currentDate\":\"2017-04-17\",\"isTraceBack\":false},{\"cumulativeReturn\":0.030646697602995543,\"currentDate\":\"2017-04-18\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.013200459159647293,\"currentDate\":\"2017-04-19\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.08847877157616824,\"currentDate\":\"2017-04-20\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.10996416916709228,\"currentDate\":\"2017-04-21\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.12359331356606285,\"currentDate\":\"2017-04-24\",\"isTraceBack\":true},{\"cumulativeReturn\":-0.11694406004552704,\"currentDate\":\"2017-04-25\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.0837395552272312,\"currentDate\":\"2017-04-26\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.11366298013224552,\"currentDate\":\"2017-04-27\",\"isTraceBack\":false},{\"cumulativeReturn\":-0.07120491098995341,\"currentDate\":\"2017-04-28\",\"isTraceBack\":false}],\"traceBackNumVal\":{\"alpha\":-0.597430818928158,\"annualizedRateOfReturn\":-0.6905758337045702,\"baseAnnualizedRateOfReturn\":-0.2542698371665659,\"baseReturnVolatility\":0.06230100725739198,\"baseSharpeRatio\":-4.723356011738493,\"baseSumRate\":-0.018305409537025794,\"beta\":0.45245892701210816,\"informationRatio\":-0.8468991659428852,\"returnVolatility\":0.5148222685633816,\"sharpeRatio\":null,\"sumRate\":-0.07120491098995341},\"transferDayDetails\":[{\"buyDate\":\"2017-04-05\",\"buyPrice\":8.52,\"changeRate\":-0.008215962441314588,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002340\",\"stockName\":\"格林美\"},{\"buyDate\":\"2017-04-05\",\"buyPrice\":5.3,\"changeRate\":0.5943396226415093,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002204\",\"stockName\":\"大连重工\"},{\"buyDate\":\"2017-04-05\",\"buyPrice\":20.15,\"changeRate\":-0.5806451612903226,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002302\",\"stockName\":\"西部建设\"},{\"buyDate\":\"2017-04-05\",\"buyPrice\":9.98,\"changeRate\":-0.153306613226453,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002497\",\"stockName\":\"雅化集团\"},{\"buyDate\":\"2017-04-05\",\"buyPrice\":18.83,\"changeRate\":-0.5512480084970791,\"sellDate\":\"2017-04-19\",\"sellPrice\":8.45,\"stockCode\":\"002807\",\"stockName\":\"江阴银行\"}]}";

        Strategy strategy2 = new Strategy(LocalDate.of(2017,3,15),
                "CharlesFeng47",false, strategyContent,"bbb", traceBackInfo);
        strategy2.setStrategyID("大策略");

        assertEquals(false, userDataHelper.deleteStrategy("ByronDong",strategy2.getStrategyID()));
        assertEquals(true, userDataHelper.deleteStrategy("CharlesFeng47", strategy2.getStrategyID()));
    }

    @Test
    public void getStrategy() throws Exception {
        List<Strategy> strategies = userDataHelper.getStrategy("Charleskkkk");
    }

}