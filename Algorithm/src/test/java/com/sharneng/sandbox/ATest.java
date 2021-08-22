package com.sharneng.sandbox;

import org.junit.Test;

import java.util.*;

import org.junit.Assert;

public class ATest {
    @Test
    public void test() {
        List<List<Integer>> x = new ArrayList<>();
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);
        for(List<Integer> l : x)
            for(Integer i : l)
                if (q.size() < 3 ) q.offer(i);
                else if (q.peek() > i) {
                    q.poll(); q.offer(i);
                }
                new ArrayList<Integer>(q);
    }

}
