package com.edu.nju.asi.service.serviceImpl.optimizationService;

import com.edu.nju.asi.infoCarrier.traceBack.FilterCondition;
import com.edu.nju.asi.infoCarrier.traceBack.RankCondition;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.serviceImpl.TraceBackService.TraceBackServiceImpl;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.AdjustCriteria;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.Genome;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.OptimizationCriteria;
import com.edu.nju.asi.utilities.enums.TargetFuncType;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Harvey on 2017/6/13.
 */
public class GenEngine {

    TraceBackService traceBackService;

    /**
     * 当前代的个体
     */
    List<Genome> curGeneration = new ArrayList<>();

    /**
     * 筛选条件基因的限制条件
     */
    List<AdjustCriteria> filterAdjustCriteria;

    /**
     * 排名条件基因的限制条件
     */
    List<AdjustCriteria> rankAdjustCriteria;

    /**
     * 种群个数
     */
    int popSize;

    /**
     * 交叉的概率
     */
    double crossoverRate;

    /**
     * 变异的概率
     */
    double mutationRate;

    /**
     * 最大回测代数
     */
    int maxGeneration;

    /**
     * 代数计数器
     */
    int generation;

    /**
     * 保存回测策略与回测结果之间的映射
     */
    Map<TraceBackCriteria, TraceBackInfo> allTraceBackInfo;

    public GenEngine() throws IOException {
        traceBackService = new TraceBackServiceImpl();

        //初始化变异率、交叉率、种群个数、最大代数
        init(10, 0.0075, 0.9, 10);
    }

    /**
     * 对多参数的策略进行优化，找到全局最优近似解
     *
     * @param optimizationCriteria 需要优化的参数标准
     * @return 每一个选出来的回测条件对应的回测的详细信息
     */
    public Map<TraceBackCriteria, TraceBackInfo> optimization(OptimizationCriteria optimizationCriteria) {

        //保存当前代的回测结果
        List<TraceBackInfo> curTracBackInfo = new ArrayList<>();
        //TODO 回测
        for (int i = 0; i < popSize; i++) {
            //原来回测筛选条件和排名条件
            List<FilterCondition> filterConditions = optimizationCriteria.originTraceBackCriteria.filterConditions;
            List<RankCondition> rankConditions = optimizationCriteria.originTraceBackCriteria.rankConditions;

            for (int j = 0; j < filterConditions.size(); j++) {
                filterConditions.get(i).value = curGeneration.get(i).filterGenome.get(j).intValue();
            }

            for (int j = 0; j < rankConditions.size(); j++) {
                rankConditions.get(i).weight = curGeneration.get(i).filterGenome.get(j).intValue();
            }

            curTracBackInfo.add(traceBackService.optimize(filterConditions, rankConditions));
        }

        //填入当前代的适应度
        //以年化收益率为目标函数
        if (optimizationCriteria.targetFuncType == TargetFuncType.ANNUALIZED_RETURN) {
            for (int i = 0; i < curGeneration.size(); i++) {
                curGeneration.get(i).fitness = curTracBackInfo.get(i).traceBackNumVal.annualizedRateOfReturn;
            }
            //以夏普比率为目标函数
        } else if (optimizationCriteria.targetFuncType == TargetFuncType.SHARP) {
            for (int i = 0; i < curGeneration.size(); i++) {
                curGeneration.get(i).fitness = curTracBackInfo.get(i).traceBackNumVal.sharpeRatio;
            }
        }

        //计算适应度，最大，最小，平均
        double[] thisGenFitness = new double[curGeneration.size()];
        for (int i = 0; i < curGeneration.size(); i++) {
            thisGenFitness[i] = curGeneration.get(i).fitness;
        }

        Sum sum = new Sum();
        Max max = new Max();
        Min min = new Min();
        Mean mean = new Mean();
        double totalFitness = sum.evaluate(thisGenFitness);
        double bestFitness = max.evaluate(thisGenFitness);
        double worstFitness = min.evaluate(thisGenFitness);
        double meanFitness = mean.evaluate(thisGenFitness);


        //选择运算,轮盘赌，选择出popSize个前代作为下一代的基础
        List<Genome> yieldGenomes = new ArrayList<>();
        for (int i = 0; i < popSize; i++){
            yieldGenomes.add(roulette(totalFitness));
        }
        curGeneration = yieldGenomes;

        //交叉运算
        for(int i = 0; i < popSize; i++){
            int firstPicked = (int) ((Math.random()) * popSize);
            int secondPicked = (int) ((Math.random()) * popSize);

            List<Genome> cross = crossover(firstPicked, secondPicked);

            //有发生交叉
            if(cross.size() > 0){
                curGeneration.set(firstPicked, cross.get(0));
                curGeneration.set(secondPicked, cross.get(1));
            }
        }

        //变异运算
        for (int i = 0; i < popSize; i++){
            curGeneration.set(i, mutate(curGeneration.get(i)));
        }

        //结果处理

        //原来回测筛选条件和排名条件
        TraceBackCriteria originTraceBackCriteria = optimizationCriteria.originTraceBackCriteria;
        List<FilterCondition> filterConditions = optimizationCriteria.originTraceBackCriteria.filterConditions;
        List<RankCondition> rankConditions = optimizationCriteria.originTraceBackCriteria.rankConditions;
        for(int i = 0; i < popSize; i++){

            for (int j = 0; j < filterConditions.size(); j++) {
                filterConditions.get(i).value = curGeneration.get(i).filterGenome.get(j).intValue();
            }

            for (int j = 0; j < rankConditions.size(); j++) {
                rankConditions.get(i).weight = curGeneration.get(i).filterGenome.get(j).intValue();
            }

            allTraceBackInfo.put(new TraceBackCriteria(originTraceBackCriteria, filterConditions, rankConditions ), curTracBackInfo.get(i));
        }

        return allTraceBackInfo;
    }


    private void init(int popsize, double mutationRate, double crossoverRate, int maxGeneration) {
        //默认为10
        this.popSize = popsize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        //默认为10代
        this.maxGeneration = maxGeneration;
        //初始为第一代
        this.generation = 1;

        //产生随机种群
        for (int i = 0; i < popSize; i++) {
            curGeneration.add(new Genome());
            for (int j = 0; j < filterAdjustCriteria.size(); j++) {
                int minVal = filterAdjustCriteria.get(j).minVal;
                int maxVal = filterAdjustCriteria.get(j).maxVal;

                //随机生成一个参数值
                curGeneration.get(i).filterGenome.add((int) (Math.random() * (maxVal - minVal)) + minVal);
            }

            for (int j = 0; j < rankAdjustCriteria.size(); j++) {
                int minVal = rankAdjustCriteria.get(j).minVal;
                int maxVal = rankAdjustCriteria.get(j).maxVal;

                //随机生成一个参数值
                curGeneration.get(i).rankGenome.add((int) (Math.random() * (maxVal - minVal)) + minVal);
            }
        }
    }

    /**
     * 使用轮盘赌选择法，选择进入下一代的个体
     */
    private Genome roulette(double totalFitness) {

        //产生一个0到适应性评分总和之间的随机数.
        double slice = (Math.random()) * totalFitness;

        //累计适应度
        double fitnessSoFar = 0;

        Genome chosenOne = null;
        for (int i = 0; i < popSize; i++) {
            //累计适应性分数.
            fitnessSoFar += curGeneration.get(i).fitness;

            //如果累计分数大于随机数,就选择此时的基因.
            if (fitnessSoFar >= slice) {
                chosenOne = curGeneration.get(i);
                break;
            }
        }
        return chosenOne;
    }

    private List<Genome> crossover(int firstPicked, int secondPicked){

        Genome first = curGeneration.get(firstPicked);
        Genome second = curGeneration.get(secondPicked);

        List<Genome> crossOvered = new ArrayList<>();

        //筛选条件交叉
        if((Math.random()) < crossoverRate && filterAdjustCriteria.size() > 0){
            int pos = (int) ((Math.random()) * first.filterGenome.size());

            int temp = second.filterGenome.get(pos).intValue();
            second.filterGenome.set(pos, first.filterGenome.get(pos));
            first.filterGenome.set(pos, new Integer(temp));

            crossOvered.add(first);
            crossOvered.add(second);
        }

        //选择条件交叉
        if((Math.random()) < crossoverRate && rankAdjustCriteria.size() > 0){
            int pos = (int) ((Math.random()) * first.rankGenome.size());

            int temp = second.rankGenome.get(pos).intValue();
            second.rankGenome.set(pos, first.rankGenome.get(pos));
            first.rankGenome.set(pos, new Integer(temp));

            crossOvered.add(first);
            crossOvered.add(second);
        }

        return crossOvered;

    }

    /**
     * 基因变异
     *
     * @return
     */
    private Genome mutate(Genome genome) {

        //先对筛选条件基因进行突变
        for (int i = 0; i < genome.filterGenome.size(); ++i) {

            double minVal = filterAdjustCriteria.get(i).minVal;
            double maxVal = filterAdjustCriteria.get(i).maxVal;
            int step = filterAdjustCriteria.get(i).step;

            //如果发生突变的话
            if (Math.random() < mutationRate) {
                double val = genome.filterGenome.get(i);

                double random = Math.random() - 0.5;
                if (random > 0) {
                    random = Math.ceil(random);
                } else if (random < 0) {
                    random = Math.floor(random);
                }

                //随机加减一个步长
                val += random * step;

                //保证不超出所给的限制,超出限制的暂时不做处理
                if (val < minVal) {
                    //TODO 超出限制应该怎么做？
                    continue;
                } else if (val > maxVal) {
                    //TODO
                    continue;
                }

            }
        }

        //先对筛选条件基因进行突变
        for (int i = 0; i < genome.rankGenome.size(); ++i) {

            double minVal = rankAdjustCriteria.get(i).minVal;
            double maxVal = rankAdjustCriteria.get(i).maxVal;
            int step = rankAdjustCriteria.get(i).step;

            //如果发生突变的话
            if (Math.random() < mutationRate) {
                double val = genome.rankGenome.get(i);

                double random = Math.random() - 0.5;
                if (random > 0) {
                    random = Math.ceil(random);
                } else if (random < 0) {
                    random = Math.floor(random);
                }

                //随机加减一个步长
                val += random * step;

                //保证不超出所给的限制,超出限制的暂时不做处理
                if (val < minVal) {
                    //TODO 超出限制应该怎么做？
                    continue;
                } else if (val > maxVal) {
                    //TODO
                    continue;
                }

            }
        }

        return genome;
    }
}
