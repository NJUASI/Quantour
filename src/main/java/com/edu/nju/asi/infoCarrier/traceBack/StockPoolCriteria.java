package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.enums.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by harvey on 17-3-31.
 *
 * 当需要增加板块的时候，可以在这里面增加参数，随之增加策略即可
 */
public class StockPoolCriteria {

    /**
     * 是否包含st
     */
    public StType stType;

    /**
     * 基准对比股票代码,可根据此确定股票的板块
     */
    public List<BlockType> blockTypes;

    /**
     * 需要的行业类型
     */
    public List<IndustryType> industryTypes;

    /**
     * 需要的地域类型
     */
    public List<AreaType> areaTypes;


    public StockPoolCriteria() {
    }

    public StockPoolCriteria(StType stType, List<BlockType> blockTypes, List<IndustryType> industryTypes, List<AreaType> areaTypes) {
        this.stType = stType;
        this.blockTypes = blockTypes;
        this.industryTypes = industryTypes;
        this.areaTypes = areaTypes;
    }

    public StockPoolCriteria(StType stType, List<BlockType> blockTypes) {
        this.stType = stType;
        this.blockTypes = blockTypes;

        industryTypes = new LinkedList<>();
        for (IndustryType temp : IndustryType.values()) {
            industryTypes.add(temp);
        }

        areaTypes = new LinkedList<>();
        for (AreaType temp : AreaType.values()) {
            areaTypes.add(temp);
        }
    }

    public StockPoolCriteria(IndustryType industryType, AreaType areaType) {
        industryTypes = new LinkedList<>();
        areaTypes = new LinkedList<>();

        industryTypes.add(industryType);
        areaTypes.add(areaType);

        this.stType = StType.INCLUDE;
        this.blockTypes = new LinkedList<>();
        blockTypes.add(BlockType.ZB);
        blockTypes.add(BlockType.ZXB);
        blockTypes.add(BlockType.CYB);
    }
}
