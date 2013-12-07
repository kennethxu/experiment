package com.sharneng.algorithm.heap;

import static org.junit.Assert.*;


import static com.sharneng.algorithm.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class IntHeapSifterTest {

    private static final int LIMIT = 9999;
    private static final int length = 1000;
    private Random random;
    private int[] heap;
    private IntHeap sut;

    @Before
    public void setup() {
        random = new Random(0);
        heap = new int[length];
        sut = new IntHeap(heap);
    }

    @Test
    public void shitDown_replacesFirstElement() {
        for (int i = 0; i < length; i++) {
            sut.siftDown(0, random.nextInt(LIMIT));
        }
        assertThat(heap, isHeap(int[].class));
    }

    @Test
    public void shitUp_addsNewElements() {
        for (int i = 0; i < length; i++) {
            sut.siftUp(i, random.nextInt(LIMIT));
        }
        assertThat(heap, isHeap(int[].class));
    }

    @Test
    public void heapify_reArrangesData() {
        for (int i = 0; i < length; i++) {
            heap[i] = random.nextInt(LIMIT);
        }
        sut.heapify();
        assertThat(heap, isHeap(int[].class));
    }

    @Test
    public void sort_sortsTheArray() {
        for (int i = 0; i < length; i++) {
            heap[i] = random.nextInt(LIMIT);
        }
        sut.sort();
        assertThat(heap, isSorted(int[].class));
    }

}
