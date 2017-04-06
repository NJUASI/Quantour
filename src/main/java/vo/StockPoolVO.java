package vo;

import utilities.enums.BlockType;

/**
 * Created by harvey on 17-4-2.
 *
 * 用于保存每只股票的股票池信息，具体见果仁网择股设置选择股票池那些信息,用于筛选股票用
 * 以后若有更多类型，则直接添加
 */
public class StockPoolVO {

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
     *
     * @param stockCode 股票代码
     * @param blockType 所属版块，主板，中小板，创业板
     * @param isSt 是否是st
     */
    public StockPoolVO(String stockCode, BlockType blockType, boolean isSt) {
        this.stockCode = stockCode;
        this.blockType = blockType;
        this.isSt = isSt;
    }
}