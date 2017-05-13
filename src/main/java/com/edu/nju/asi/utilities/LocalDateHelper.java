package com.edu.nju.asi.utilities;

import java.time.LocalDate;

/**
 * Created by cuihua on 2017/5/13.
 */
public class LocalDateHelper {

    public static LocalDate convertString(String date) {
        String[] parts = date.split("-");
        return LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}
