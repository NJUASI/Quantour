package com.edu.nju.asi.utilities;

import com.edu.nju.asi.utilities.enums.DataSourceState;

/**
 * Created by cuihua on 2017/4/15.
 *
 * 单例保存当前数据源来源
 */
public class DataSourceStateKeeper {

    private static DataSourceStateKeeper keeper;

    private DataSourceState state = DataSourceState.ORIGINAL;

    private DataSourceStateKeeper() {
    }

    public static DataSourceStateKeeper getInstance() {
        if (keeper == null) {
            keeper = new DataSourceStateKeeper();
            return keeper;
        } else {
            return keeper;
        }
    }

    public DataSourceState getState() {
        return state;
    }

    public void setState(DataSourceState state) {
        this.state = state;
    }
}
