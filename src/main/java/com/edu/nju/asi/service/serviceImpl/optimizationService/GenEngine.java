package com.edu.nju.asi.service.serviceImpl.optimizationService;

import com.edu.nju.asi.infoCarrier.traceBack.FilterCondition;
import com.edu.nju.asi.infoCarrier.traceBack.RankCondition;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackCriteria;
import com.edu.nju.asi.infoCarrier.traceBack.TraceBackInfo;
import com.edu.nju.asi.service.TraceBackService;
import com.edu.nju.asi.service.serviceImpl.traceBackService.TraceBackServiceImpl;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.AdjustCriteria;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.Genome;
import com.edu.nju.asi.service.serviceImpl.optimizationService.optimization.OptimizationCriteria;
import com.edu.nju.asi.utilities.enums.TargetFuncType;
import com.edu.nju.asi.utilities.exceptions.DataSourceFirstDayException;
import com.edu.nju.asi.utilities.exceptions.DateNotWithinException;
import com.edu.nju.asi.utilities.exceptions.NoDataWithinException;
import com.edu.nju.asi.utilities.exceptions.UnhandleBlockTypeException;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import java.io.IOException;
import java.util.*;


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

    public GenEngine(TraceBackService traceBackService) throws IOException {
        this.traceBackService = traceBackService;
        allTraceBackInfo = new TreeMap<>(new Comparator<TraceBackCriteria>() {
            @Override
            public int compare(TraceBackCriteria o1, TraceBackCriteria o2) {
                return 0;
            }
        });
    }

    /**
     * 对多参数的策略进行优化，找到全局最优近似解
     *
     * @param optimizationCriteria 需要优化的参数标准
     * @return 每一个选出来的回测条件对应的回测的详细信息
     */
    public Map<TraceBackCriteria, TraceBackInfo> optimization(OptimizationCriteria optimizationCriteria) throws IOException, UnhandleBlockTypeException, NoDataWithinException, DateNotWithinException, DataSourceFirstDayException {

        long start = System.currentTimeMillis();

        this.filterAdjustCriteria = optimizationCriteria.filterAdjust;
        this.rankAdjustCriteria = optimizationCriteria.rankAdjust;

        //如果搜索空间大小大于默认最大的数量
        if (!(optimizationCriteria.searchNodes >= 20 * 10)) {
            popSize = (int) (optimizationCriteria.searchNodes / 10);
        } else {
            popSize = 20;
        }

        //初始化变异率、交叉率、种群个数、最大代数
        init(0.0075, 0.9, 10);

        //先初始化service
        traceBackService.setOriginTraceBackCriteria(optimizationCriteria.originTraceBackCriteria);

        while (generation <= maxGeneration) {
            System.out.println("--------------------------第"+generation+"代-------------------------");
            //保存当前代的回测结果
            List<TraceBackInfo> curTracBackInfo = new ArrayList<>();
            //TODO 回测
            for (int i = 0; i < popSize; i++) {
                //原来回测筛选条件和排名条件
                List<FilterCondition> filterConditions = optimizationCriteria.originTraceBackCriteria.filterConditions;
                List<RankCondition> rankConditions = optimizationCriteria.originTraceBackCriteria.rankConditions;

                for (int j = 0; j < filterConditions.size(); j++) {
                    filterConditions.get(j).value = curGeneration.get(i).filterGenome.get(j).intValue();
                }

                for (int j = 0; j < rankConditions.size(); j++) {
                    rankConditions.get(j).weight = curGeneration.get(i).rankGenome.get(j).intValue();
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
            for (int i = 0; i < popSize; i++) {
                Genome picked = roulette(totalFitness);
                //说明累计函数始终不能大于轮盘赌生成的数
                if(picked == null){
                    picked = curGeneration.get(i);
                }
                yieldGenomes.add(picked);
            }
            curGeneration = yieldGenomes;

            //交叉运算
            for (int i = 0; i < popSize; i++) {
                //TODO
                int firstPicked = (int)(Math.random() * popSize);
                int secondPicked = (int)(Math.random() * popSize);

                System.out.println("交叉开始:第"+(i+1)+"次");
                List<Genome> cross = crossover(firstPicked, secondPicked);
                System.out.println("交叉完成:第"+(i+1)+"次");

                //有发生交叉
                if (cross.size() > 0) {
                    curGeneration.set(firstPicked, cross.get(0));
                    curGeneration.set(secondPicked, cross.get(1));
                }
            }

            //变异运算
            for (int i = 0; i < popSize; i++) {
                System.out.println("变异开始:第"+(i+1)+"次");
                curGeneration.set(i, mutate(curGeneration.get(i)));
                System.out.println("变异完成:第"+(i+1)+"次");
            }

            //结果处理

            //原来回测筛选条件和排名条件
            TraceBackCriteria originTraceBackCriteria = optimizationCriteria.originTraceBackCriteria;
            List<FilterCondition> filterConditions = optimizationCriteria.originTraceBackCriteria.filterConditions;
            List<RankCondition> rankConditions = optimizationCriteria.originTraceBackCriteria.rankConditions;
            for (int i = 0; i < popSize; i++) {

                for (int j = 0; j < filterConditions.size(); j++) {
                    filterConditions.get(j).value = curGeneration.get(i).filterGenome.get(j).intValue();
                }

                for (int j = 0; j < rankConditions.size(); j++) {
                    rankConditions.get(j).weight = curGeneration.get(i).rankGenome.get(j).intValue();
                }

                allTraceBackInfo.put(new TraceBackCriteria(originTraceBackCriteria, filterConditions, rankConditions), curTracBackInfo.get(i));
            }

            generation++;
        }
        //搜索空间比较小，直接用穷举法
//        else {
            //TODO
//            List<int[]> allPosibleVals = new ArrayList<>();
//
//            int[] values = new int[filterAdjustCriteria.size()+rankAdjustCriteria.size()];
//            if(filterAdjustCriteria.size() > 0 && rankAdjustCriteria.size() > 0){
//                allPosibleVals = recursive1(0, filterAdjustCriteria, rankAdjustCriteria, values, allPosibleVals);
//            }
//            else if(rankAdjustCriteria.size() > 0){
//
//            }
//
//            for(int i = 0; i < rankAdjustCriteria.size(); i++){
//
//            }
//        }

        System.out.println("优化用时:"+(System.currentTimeMillis()-start)+"毫秒");

        //代数自增
        return allTraceBackInfo;
    }

//    private List<int[]> recursive1(int index, List<AdjustCriteria> first, List<AdjustCriteria> second, int[] values, List<int[]> allPosibleVals){
//        //进入第二个递归
//        if(index == first.size()){
//            return recursive2(0, second, values);
//        }
//        else {
//            AdjustCriteria adjustCriteria = first.get(index);
//            int minVal = adjustCriteria.minVal;
//            int maxVal = adjustCriteria.maxVal;
//            int step = adjustCriteria.step;
//            for(int i = minVal; i <= maxVal; i = i+step){
//                values[index] = i;
//                values = recursive1(index++, first, second, values, );
//            }
//        }
//    }
//
//    private List<int[]> recursive2(int index, List<AdjustCriteria> criteria, int[] values){
//
//    }


    private void init(double mutationRate, double crossoverRate, int maxGeneration) {
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
        double slice = Math.random() * totalFitness;

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

    private List<Genome> crossover(int firstPicked, int secondPicked) {

        Genome first = curGeneration.get(firstPicked);
        Genome second = curGeneration.get(secondPicked);

        List<Genome> crossovered = new ArrayList<>();

        //筛选条件交叉
        if ((Math.random()) < crossoverRate && filterAdjustCriteria.size() > 0) {
            int pos = (int) (Math.random() * first.filterGenome.size());

            int temp = second.filterGenome.get(pos).intValue();
            second.filterGenome.set(pos, first.filterGenome.get(pos));
            first.filterGenome.set(pos, new Integer(temp));

            crossovered.add(first);
            crossovered.add(second);
        }

        //选择条件交叉
        if ((Math.random()) < crossoverRate && rankAdjustCriteria.size() > 0) {
            int pos = (int) (Math.random() * first.rankGenome.size());

            System.out.println("选择交叉pos:"+pos);

            int temp = second.rankGenome.get(pos).intValue();
            second.rankGenome.set(pos, first.rankGenome.get(pos));
            first.rankGenome.set(pos, new Integer(temp));

            crossovered.add(first);
            crossovered.add(second);
        }

        return crossovered;

    }

    /**
     * 基因变异
     *
     * @return
     */
    private Genome mutate(Genome genome) {

        //先对筛选条件基因进行突变
        for (int i = 0; i < genome.filterGenome.size(); i++) {

            System.out.println("mutate次数:"+(i+1));

            double minVal = filterAdjustCriteria.get(i).minVal;
            double maxVal = filterAdjustCriteria.get(i).maxVal;
            int step = filterAdjustCriteria.get(i).step;

            //如果发生突变的话
            if (Math.random() < mutationRate) {
                int val = genome.filterGenome.get(i);

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

                genome.filterGenome.set(i, new Integer(val));
            }
        }

        //再对排名条件基因进行突变
        for (int i = 0; i < genome.rankGenome.size(); i++) {

            double minVal = rankAdjustCriteria.get(i).minVal;
            double maxVal = rankAdjustCriteria.get(i).maxVal;
            int step = rankAdjustCriteria.get(i).step;

            //如果发生突变的话
            if (Math.random() < mutationRate) {
                int val = genome.rankGenome.get(i);

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

                genome.rankGenome.set(i, val);
            }
        }

        return genome;
    }
}
