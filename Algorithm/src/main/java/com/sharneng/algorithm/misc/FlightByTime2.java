package com.sharneng.algorithm.misc;

import java.util.Arrays;

public class FlightByTime2 {
    public static String[] sort(int[] times, String[] flights) {
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
}
