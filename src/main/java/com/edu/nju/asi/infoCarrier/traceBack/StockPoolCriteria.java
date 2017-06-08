package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.enums.AreaType;
import com.edu.nju.asi.utilities.enums.BlockType;
import com.edu.nju.asi.utilities.enums.IndustryType;
import com.edu.nju.asi.utilities.enums.StType;

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
    public IndustryType industryType;

    /**
     * 需要的地域类型
     */
    public AreaType areaType;


    public StockPoolCriteria() {
    }

    public StockPoolCriteria(StType stType, List<BlockType> blockTypes) {
        this.stType = stType;
        this.blockTypes = blockTypes;
    }

    public StockPoolCriteria(IndustryType industryType, AreaType areaType) {
        this.industryType = industryType;
        this.areaType = areaType;
    }
}
