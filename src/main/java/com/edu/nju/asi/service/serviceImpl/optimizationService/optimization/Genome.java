package com.edu.nju.asi.service.serviceImpl.optimizationService.optimization;

import com.edu.nju.asi.utilities.enums.ComparatorType;
import com.edu.nju.asi.utilities.enums.IndicatorType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harvey on 2017/4/19.
 *
 */
public class Genome {

    /**
     * 筛选条件的基因容器
     */
    public List<Integer> filterGenome;

    /**
     * 选择条件的基因容器
     */
    public List<Integer> rankGenome;

    /**
     * 适应度
     */
    public double fitness;

    public Genome() {
        filterGenome = new ArrayList<>();
        rankGenome = new ArrayList<>();

        fitness = 0;
    }
}
