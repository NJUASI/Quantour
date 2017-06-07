package com.edu.nju.asi.utilities.enums;

/**
 * Created by Harvey on 2017/6/7.
 */
public enum RankType {

    DESC_RANK("从大到小"),
    ASC_RANK("从小到大");


    private String repre;

    RankType(String repre) {
        this.repre = repre;
    }

    /**
     *
     * @return 该枚举相对应的文件中形式
     *
     * enum TO String
     * 便于写入数据库
     */
    public String getRepre() {
        return repre;
    }

    /**
     *
     * @return 该类型对应的枚举代码
     *
     * String TO enum
     * 便于从数据库读入
     */
    public static RankType getEnum(String a) {
        for (RankType thisEnum : RankType.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }


}
