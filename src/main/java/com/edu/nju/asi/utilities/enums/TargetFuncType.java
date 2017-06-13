package com.edu.nju.asi.utilities.enums;

import com.edu.nju.asi.utilities.exceptions.NoMatchEnumException;

/**
 * Created by Harvey on 2017/6/13.
 *
 * 目标函数
 */
public enum TargetFuncType {

    SHARP("夏普比率"),
    ANNUALIZED_RETURN("年化收益率");

    private String repre;

    TargetFuncType(String repre) {
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
    public static TargetFuncType getEnum(String a) throws NoMatchEnumException {
        for (TargetFuncType thisEnum : TargetFuncType.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        throw new NoMatchEnumException();
    }

}
