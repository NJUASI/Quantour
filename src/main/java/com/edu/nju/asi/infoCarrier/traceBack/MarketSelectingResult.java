package com.edu.nju.asi.infoCarrier.traceBack;

/**
 * Created by cuihua on 2017/6/11.
 *
 * 进行市场择时后的结果
 */
public class MarketSelectingResult {

    /**
     * 满足金叉条件
     */
    public boolean isGoldenFork;

    /**
     * 满足死叉条件
     */
    public boolean isDeathCross;

    public MarketSelectingResult() {
    }

    public MarketSelectingResult(boolean isGoldenFork, boolean isDeathCross) {
        this.isGoldenFork = isGoldenFork;
        this.isDeathCross = isDeathCross;
    }
}
