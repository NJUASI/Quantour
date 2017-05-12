package com.edu.nju.asi.utilities.comparator;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Created by cuihua on 2017/4/9.
 */
public class LocalDateComparator implements Comparator<LocalDate> {

    @Override
    public int compare(LocalDate o1, LocalDate o2) {
        if (o1.isBefore(o2)) return -1;
        if (o1.isAfter(o2)) return 1;
        return 0;
    }
}
