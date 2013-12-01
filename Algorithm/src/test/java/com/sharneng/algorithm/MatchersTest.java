package com.sharneng.algorithm;

import static com.sharneng.algorithm.Matchers.*;
import static org.junit.Assert.*;

import com.sharneng.algorithm.SortOrder;

import org.junit.Test;

public class MatchersTest {

    @Test
    public void ascending() {
        int[] sut = new int[] { 1, 4, 3, 5, 9, 6, 7 };
        assertThat(sut, isHeap(int[].class));
    }

    @Test
    public void ascending_oversize() {
        int[] sut = new int[] { 1, 4, 3, 5, 9, 6, 7 };
        assertThat(sut, isHeap(int[].class, 100));
    }

    @Test
    public void ascending_sizeLimited() {
        int[] sut = new int[] { 1, 4, 3, 5, 9, 6, 0 };
        assertThat(sut, isHeap(int[].class, 6));
    }

    @Test
    public void ascending_empty() {
        int[] sut = new int[0];
        assertThat(sut, isHeap(int[].class));
    }

    @Test
    public void same() {
        int[] sut = new int[] { 2, 2, 2, 2, 2, 2, 2 };
        assertThat(sut, isHeap(int[].class));
        assertThat(sut, isHeap(int[].class, SortOrder.DESCENDING));
    }

    @Test
    public void descending() {
        int[] sut = new int[] { 7, 6, 5, 4, 3, 2, 1 };
        assertThat(sut, isHeap(int[].class, SortOrder.DESCENDING));
    }

    @Test
    public void descending_oversize() {
        int[] sut = new int[] { 7, 6, 5, 4, 3, 2, 1 };
        assertThat(sut, isHeap(int[].class, 100, SortOrder.DESCENDING));
    }

    @Test
    public void descending_sizeLimited() {
        int[] sut = new int[] { 7, 6, 5, 4, 3, 2, 1 };
        assertThat(sut, isHeap(int[].class, 6, SortOrder.DESCENDING));
    }

    @Test
    public void descending_empty() {
        int[] sut = new int[0];
        assertThat(sut, isHeap(int[].class, SortOrder.DESCENDING));
    }

}
