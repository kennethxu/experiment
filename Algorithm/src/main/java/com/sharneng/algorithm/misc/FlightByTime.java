package com.sharneng.algorithm.misc;

import java.util.Arrays;

public class FlightByTime {
    public static String[] sort(int[] times, String[] flights) {
        final int len = times.length;
        final int[] order = new int[len];
        for (int i = 0; i < len; i++) {
            order[i] = times[i] * len + i;
        }
        Arrays.sort(order);
        final String result[] = new String[len];
        for (int i = 0; i < len; i++) {
            int index = order[i] % len;
            result[i] = flights[index];
        }
        return result;
    }
}
