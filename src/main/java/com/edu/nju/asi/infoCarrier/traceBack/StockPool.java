package com.edu.nju.asi.infoCarrier.traceBack;

import com.edu.nju.asi.utilities.enums.AreaType;
import com.edu.nju.asi.utilities.enums.BlockType;
import com.edu.nju.asi.utilities.enums.IndustryType;

/**
 * Created by harvey on 17-4-2.
 *
 * 用于保存每只股票的股票池信息，具体见果仁网择股设置选择股票池那些信息,用于筛选股票用
 * 以后若有更多类型，则直接添加
 */
public class StockPool {

    /**
     * 股票代码
     */
    public String stockCode;

    /**
     * 所属板块
     */
    public BlockType blockType;

    /**
     * 是否是st
     */
    public boolean isSt;

    /**
     * 所属行业
     */
    public IndustryType industryType;

    /**
     * 所属地域
     */
    public AreaType areaType;




    public StockPool(String stockCode, BlockType blockType, boolean isSt, IndustryType industryType, AreaType areaType) {
        this.stockCode = stockCode;
        this.blockType = blockType;
        this.isSt = isSt;
        this.industryType = industryType;
        this.areaType = areaType;
    }
}
