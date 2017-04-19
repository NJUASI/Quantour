package vo;

import utilities.enums.FormateType;
import utilities.enums.PickType;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 形成期的形成和挑选类型以及rank
 */
public class FormateAndPickVO {

    /**
     * 形成期形成的类型
     */
    public FormateType formateType;

    public FormateAndPickVO(FormateType formateType, PickType pickType, int rank) {
        this.formateType = formateType;
        this.pickType = pickType;
        this.rank = rank;
    }

    /**
     * 形成期挑选的类型
     */
    public PickType pickType;

    /**
     * 排名
     */
    public int rank;
}
