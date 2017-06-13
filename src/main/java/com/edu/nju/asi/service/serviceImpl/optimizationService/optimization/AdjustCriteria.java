package com.edu.nju.asi.service.serviceImpl.optimizationService.optimization;

/**
 * Created by Harvey on 2017/6/13.
 */
public class AdjustCriteria {

    /**
     * 最小
     */
    public int minVal;

    /**
     * 最大
     */
    public int maxVal;

    /**
     * 步长(强制步长为正整数)
     */
    public int step;

    public AdjustCriteria(int minVal, int maxVal, int step) {
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.step = step;
    }
}
