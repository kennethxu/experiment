package com.sharneng.experiment.misc;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FlightByTime2 {
    public String[] sort(int[] times, String[] flights) {
        final int len = times.length;
        final Flight[] order = new Flight[len];
        for (int i = 0; i < len; i++) {
            order[i] = new Flight(times[i], flights[i]);
        }
        Arrays.sort(order);
        final String result[] = new String[len];
        for (int i = 0; i < len; i++) {
            result[i] = order[i].flight;
        }
        return result;
    }

    private static class Flight implements Comparable<Flight> {
        final int time;
        final String flight;

        public Flight(int time, String flight) {
            this.time = time;
            this.flight = flight;
        }

        @Override
        public int compareTo(Flight o) {
            return time - o.time;
        }
    }

    String[] flights = new String[] { "AAA123", "EXD678", "FIX000", "WXX123" };
    int[] times = new int[] { 1234, 430, 1156, 15 };
    String[] expected = new String[] { "WXX123", "EXD678", "FIX000", "AAA123", };

    @Test
    public void test() {
        Assert.assertArrayEquals(expected, sort(times, flights));
    }
}
