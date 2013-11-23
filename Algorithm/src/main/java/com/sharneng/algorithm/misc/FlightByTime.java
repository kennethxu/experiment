package com.sharneng.algorithm.misc;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FlightByTime {
    public String[] sort(int[] times, String[] flights) {
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

    String[] flights = new String[] { "AAA123", "EXD678", "FIX000", "WXX123" };
    int[] times = new int[] { 1234, 430, 1156, 15 };
    String[] expected = new String[] { "WXX123", "EXD678", "FIX000", "AAA123", };

    @Test
    public void test() {
        Assert.assertArrayEquals(expected, sort(times, flights));
    }
}
