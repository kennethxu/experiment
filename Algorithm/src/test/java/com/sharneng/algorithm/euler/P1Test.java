package com.sharneng.algorithm.euler;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class P1Test {

    @Test
    public void sumOf_2_4_6_to_10_is_30() {
        assertThat(P1.sumArithmeticSeries(2, 11, 2), is(30));
        assertThat(P1.sumArithmeticSeries(2, 12, 2), is(30));
    }

    @Test
    public void sumOf_1_2_3_to_100_is_5050() {
        assertThat(P1.sumArithmeticSeries(1, 101, 1), is(5050));
    }

    @Test
    public void sumOf_2_5_8_11_is_26() {
        assertThat(P1.sumArithmeticSeries(2, 12, 3), is(26));
        assertThat(P1.sumArithmeticSeries(2, 13, 3), is(26));
        assertThat(P1.sumArithmeticSeries(2, 14, 3), is(26));
    }

    @Test
    public void sumOfMultiplesOf3and5_is23_onLimit10() {
        assertThat(P1.sumOfMultiplesOf3and5(10), is(3 + 5 + 6 + 9));
    }

    @Test
    public void sumOfMultiplesOf3and5_is119_onLimit22() {
        assertThat(P1.sumOfMultiplesOf3and5(22), is(3 + 5 + 6 + 9 + 10 + 12 + 15 + 18 + 20 + 21));
    }

}
