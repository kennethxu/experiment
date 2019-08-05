package com.sharneng.algorithm.median;

import com.sharneng.algorithm.median.MedianFinder;
import org.junit.Assert;
import org.junit.Test;

public class MedianFinder2Test {

    @Test
    public void test() {
        MedianFinder2 sut = new MedianFinder2();

        sut.insert(60);
        Assert.assertEquals(60, sut.getMedian());

        sut.insert(50);
        Assert.assertEquals(55, sut.getMedian());

        sut.insert(120);
        Assert.assertEquals(60, sut.getMedian());

        sut.insert(80);
        Assert.assertEquals(70, sut.getMedian());

        sut.insert(40);
        Assert.assertEquals(60, sut.getMedian());

        sut.insert(10);
        Assert.assertEquals(55, sut.getMedian());

        sut.insert(54);
        Assert.assertEquals(54, sut.getMedian());

        sut.insert(54);
        Assert.assertEquals(54, sut.getMedian());

    }
}
