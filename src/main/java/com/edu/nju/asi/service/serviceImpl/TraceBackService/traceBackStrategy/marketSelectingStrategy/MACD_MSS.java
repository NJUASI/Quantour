package com.edu.nju.asi.service.serviceImpl.TraceBackService.traceBackStrategy.marketSelectingStrategy;

import com.edu.nju.asi.infoCarrier.traceBack.MarketSelectingResult;
import com.edu.nju.asi.model.BaseStock;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cuihua on 2017/6/11.
 * <p>TODO 算得有点问题
 * DIF(快线) = EMA短线 - EMA长线 （EMA短线默认为上证12日指数移动平均线，EMA长线默认为上证26日指数移动平均线。）
 * DEA(慢线)= EMA(DIF, M ) (DEA 默认为DIF的9日指数移动平均线。)
 * 金叉条件：DIF(t) > 0 and DIF(t) > DIF(t-1) and DIF (t) > DEA(t) and DIF (t -1) < DEA(t-1)
 * 死叉条件：DIF(t) < 0 and DIF(t) < DIF(t-1) and DIF (t) < DEA(t) and DIF (t -1) > DEA(t-1)
 */
public class MACD_MSS extends AllMarketSelectingStrategy {

    public MACD_MSS(List<LocalDate> allDatesWithData, List<BaseStock> baseStocks) {
        super(allDatesWithData, baseStocks);
    }

    @Override
    public MarketSelectingResult marketSelecting(int neededSelectDayIndex, int criteria1, int criteria2, int criteria3) {
        Double dif_today = computeDIF(neededSelectDayIndex, criteria1, criteria2);
        Double dea_today = computeDEA(neededSelectDayIndex, criteria1, criteria2, criteria3);

        Double dif_pre = computeDIF(neededSelectDayIndex - 1, criteria1, criteria2);
        Double dea_pre = computeDEA(neededSelectDayIndex - 1, criteria1, criteria2, criteria3);

        MarketSelectingResult result = new MarketSelectingResult();
        if (dif_today > 0 && dif_today > dif_pre && dif_today > dea_today && dif_pre < dea_pre) {
            result.isGoldenFork = true;
        }

        if (dif_today < 0 && dif_today < dif_pre && dif_today < dea_today && dif_pre > dea_pre) {
            result.isGoldenFork = true;
        }

        return result;
    }

    private Double computeDIF(int neededSelectDayIndex, int shortLineCri, int longLineCri) {
        Double shortLine = ema(neededSelectDayIndex, shortLineCri);
        Double longLine = ema(neededSelectDayIndex, longLineCri);

        if (longLine == null || shortLine == null) {
            return null;
        }

        return shortLine - longLine;
    }

    private Double computeDEA(int neededSelectDayIndex, int shortLineCri, int longLineCri, int deaCri) {
        // 平滑指数, 一般取作2/(N+1) MID = 9
        double k = 2.0 / (deaCri + 1);

        // 第一天的macd_dea以第一次计算的dif为准
        Double macd_dea = computeDIF(neededSelectDayIndex - deaCri + 1, shortLineCri, longLineCri);

        if (macd_dea == null) {
            return null;
        }

        for (int j = 1; j < deaCri; j++) {
            Double next = computeDIF(neededSelectDayIndex - j, shortLineCri, longLineCri);

            // 数据不存在，返回null
            if (next == null) {
                return null;
            }

            macd_dea = k * next + (1 - k) * macd_dea;
        }
        return macd_dea;
    }
}
