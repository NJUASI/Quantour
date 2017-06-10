package com.edu.nju.asi.service.serviceImpl.strategyService;

import com.edu.nju.asi.infoCarrier.strategy.StrategyRankResult;
import com.edu.nju.asi.infoCarrier.traceBack.CumulativeReturn;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

import java.util.List;

/**
 * Created by cuihua on 2017/6/9.
 * <p>
 * 对策略进行打分
 */
public class StrategyRanker {

    NormalDistribution normalDistribution;

    /**
     * @param info 需要被评分的策略
     * @return 策略在多维度（收益、抗风险、流动性、稳定性、实盘）上的评分
     */
    public StrategyRankResult getRank(TraceBackCriteria criteria, TraceBackInfo info) {
        // 年化收益率和收益波动率
        double annualizedRateOfReturn = info.traceBackNumVal.annualizedRateOfReturn;
        double returnVolatility = info.traceBackNumVal.returnVolatility;

        // 最大回撤率和赢率
        double maxDrawDown = info.maxTraceBack.maxStrategyTraceBackRate;
        double abWinRate = info.absoluteReturnPeriod.winRate;
        double reWinRate = info.relativeReturnPeriod.winRate;


        return new StrategyRankResult(rankProfit(annualizedRateOfReturn), rankAntiRisk(maxDrawDown, abWinRate, reWinRate),
                rankFluidity(criteria), rankStability(returnVolatility), rankReality(info.baseCumulativeReturn, info.strategyCumulativeReturn));
    }

    /**
     * 对收益分数打分
     */
    private double rankProfit(double annualizedRateOfReturn) {
        normalDistribution = new NormalDistribution(0.05, 0.2);
        double probability = normalDistribution.cumulativeProbability(annualizedRateOfReturn);
        if (probability <= 0) return 0;
        return probability;
    }

    /**
     * 对抗风险分数打分
     */
    private double rankAntiRisk(double maxDrawDown, double abWinRate, double reWinRate) {
        final int score = (int) ((1 - maxDrawDown) * 5 + abWinRate * 2 + reWinRate * 8) * 100;
        normalDistribution = new NormalDistribution(450, 100);
        return normalDistribution.cumulativeProbability(score);

    }

    /**
     * 对流动性分数打分
     */
    private double rankFluidity(TraceBackCriteria criteria) {
        int holdingPeriod = criteria.holdingPeriod;
        int maxHoldingNum = criteria.maxHoldingNum;

        int filterNum = criteria.filterConditions.size();
        int rankNum = criteria.rankConditions.size();

        int score = 20 / holdingPeriod * 5 + maxHoldingNum * 2;
        if (filterNum != 0) {
            score += 5 / filterNum * 12;
        } else {
            score += 72;
        }
        if (rankNum != 0) {
            score += 5 / rankNum * 10;
        } else {
            score += 60;
        }

        NormalDistribution normalDistribution = new NormalDistribution(140, 60);
        return normalDistribution.cumulativeProbability(score);
    }

    /**
     * 对稳定性分数打分
     */
    private double rankStability(double returnVolatility) {
        normalDistribution = new NormalDistribution(0.2, 0.2);
        double probability = 1 - normalDistribution.cumulativeProbability(returnVolatility);
        if (probability >= 0.5) return 0;
        return probability;
    }

    /**
     * 对实盘分数打分
     */
    private double rankReality(List<CumulativeReturn> base, List<CumulativeReturn> strategy) {
        Mean mean = new Mean();
        double meanBase = mean.evaluate(getProfits(base));
        double meanStrategy = mean.evaluate(getProfits(strategy));

        normalDistribution = new NormalDistribution(0, 0.1);
        return normalDistribution.cumulativeProbability(meanStrategy - meanBase);
    }

    private double[] getProfits(List<CumulativeReturn> cumulativeReturns) {
        double[] profits = new double[cumulativeReturns.size()];
        for (int i = 0; i < cumulativeReturns.size(); i++) {
            profits[i] = cumulativeReturns.get(i).cumulativeReturn;
        }
        return profits;
    }

}
