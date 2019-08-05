package com.sharneng.algorithm.median;

import java.util.Arrays;

/**
 * A class can be used to obtain the median value of all integers inserted into it.
 * 
 * @author Kenneth Xu
 * 
 */
public class MedianFinder2 {

    private int count = 0;
    private int capacity = 1024;
    private int[] data = new int[capacity];
    private int median;

    /**
     * Gets the median value of all inserted elements.
     * 
     * @return the median value of all inserted elements.
     */
    public int getMedian() {
        if (count <= 0) throw new IllegalStateException("No element");
        return (count & 0x1) == 1 ?
                select(0, count-1, count/2):
                (select(0, count-1, count/2)+ select(0, count-1, count/2-1)) / 2;
    }

    private int select(int left, int right, int k) {
        if (left == right) return data[left];
        int p = k;
        p = partition(left, right, p);
        if (k == p) return data[k];
        else if (k < p) {
            return select(left, p-1, k);
        } else {
            return select(p+1, right, k);
        }
    }

    private int partition(int left, int right, int p) {
        int v = data[p];
        swap(right, p);
        int iStore = left;
        for (int i = left; i < right; i++) {
            if (data[i] < v) {
                swap(i, iStore++);
            }
        }
        swap(right, iStore);
        return iStore;
    }

    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    /**
     * Add a new element to the collection that can later be used to determine the median.
     * 
     * @param i
     *            the element to be added
     */
    public void insert(final int i) {
        if (count >= capacity) {
            int[] temp = Arrays.copyOf(data, capacity * 2);
            data = temp;
            capacity *= 2;
        }
        data[count++] = i;
    }
}
