package com.sharneng.algorithm.euler;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class P1Test {

    @Test
    public void sumOf2to10evenNumberIs30() {
        assertThat(P1.sumArithmeticSeries(2, 11), is(30));
    }

    @Test
    public void sumOf1to100is5500() {
        assertThat(P1.sumArithmeticSeries(1, 101), is(5050));
    }

}
