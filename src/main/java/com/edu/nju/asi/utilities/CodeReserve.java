package com.edu.nju.asi.utilities;

/**
 * Created by 61990 on 2017/4/15.
 */
public class CodeReserve {
    private static CodeReserve codeReserve = new CodeReserve();

    private String code;
    private String name;
    /**
     * 获得单件CodeReserve
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/4/15
     */
    public static CodeReserve getInstance(){
        return codeReserve;
    }
    /**
     * code
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/4/15
     */
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

    /**
     * 设置ID
     *
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public void setCode(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
