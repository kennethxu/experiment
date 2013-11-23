package com.sharneng.experiment.median;

import com.sharneng.algorithm.median.MedianFinder;

import org.junit.Test;

import junit.framework.Assert;

public class MedianFinderTest {

    @Test
    public void test() {
        MedianFinder sut = new MedianFinder();

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
