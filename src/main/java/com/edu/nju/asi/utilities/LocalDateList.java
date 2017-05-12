package com.edu.nju.asi.utilities;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Harvey on 2017/4/18.
 *
 * 优化indexOf算法和contains算法
 */
public class LocalDateList extends ArrayList<LocalDate>{


    /**
     * 用二分法查找localdate
     * @param o
     */
    @Override
    public int indexOf(Object o) {
        LocalDate date = (LocalDate)o;

        int start = 0;
        int end = this.size() - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (date.isBefore(get(middle))) {
                end = middle - 1;
            } else if (date.isAfter(get(middle))) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
