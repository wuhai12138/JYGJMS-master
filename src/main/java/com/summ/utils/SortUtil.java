package com.summ.utils;

import org.apache.poi.ss.formula.functions.T;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by summ on 18/1/26.
 */
public class SortUtil {
    public static <T> List<T> sort(List<T> list) {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Collator collator = Collator.getInstance(Locale.CHINA);
                return 0;
            }
        });
        return null;
    }
}
