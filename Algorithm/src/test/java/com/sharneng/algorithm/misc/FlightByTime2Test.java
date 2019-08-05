package com.sharneng.algorithm.misc;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FlightByTime2Test {

    String[] flights = new String[] { "AAA123", "EXD678", "FIX000", "WXX123" };
    int[] times = new int[] { 1234, 430, 1156, 15 };
    String[] expected = new String[] { "WXX123", "EXD678", "FIX000", "AAA123", };

    @Test
    public void test() {
        Assert.assertArrayEquals(expected, FlightByTime2.sort(times, flights));
    }
}
