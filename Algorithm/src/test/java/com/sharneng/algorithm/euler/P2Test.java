package com.sharneng.algorithm.euler;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class P2Test {

    @Test
    public void sumOf2to10evenBelow100() {
        assertThat(P2.fibonacciSeries(100), is(44));
    }

}
