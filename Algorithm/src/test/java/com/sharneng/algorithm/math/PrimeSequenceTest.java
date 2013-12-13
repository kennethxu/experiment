package com.sharneng.algorithm.math;

import org.junit.Test;

public class PrimeSequenceTest {
    @Test
    public void test2() {
        test();
    }

    @Test
    public void test() {
        PrimeSequence sut = PrimeSequence.INSTANCE;
        int i = 0;
        long x = 0;
        for (Long l : sut) {
            if (++i >= 1000000) {
                x = l;
                break;
            }
        }
        System.out.println(i + ". " + x);
    }
}
