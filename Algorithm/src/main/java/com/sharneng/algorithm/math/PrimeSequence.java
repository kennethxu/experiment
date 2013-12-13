package com.sharneng.algorithm.math;

import com.sharneng.algorithm.ArrayUtils;

import java.util.Arrays;
import java.util.Iterator;

public class PrimeSequence implements Iterable<Long> {
    public static final PrimeSequence INSTANCE = new PrimeSequence();
    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    private long[] primes;
    private int index = 0;

    PrimeSequence() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    PrimeSequence(int initialCapacity) {
        if (initialCapacity < DEFAULT_INITIAL_CAPACITY) initialCapacity = DEFAULT_INITIAL_CAPACITY;
        primes = new long[initialCapacity];
        primes[index++] = 2;
        primes[index++] = 3;
    }

    private boolean isPrime(final long x) {
        long half = (long) Math.sqrt(x);
        long p;
        for (int i = 0; i < index && (p = primes[i]) <= half; i++) {
            if (x % p == 0) return false;
        }
        return true;
    }

    private long get(int i) {
        if (i >= primes.length) grow(i + 1);
        for (long next = primes[index - 1] + 2; i >= index; next += 2) {
            if (isPrime(next)) primes[index++] = next;
        }
        return primes[i];
    }

    private void grow(int minCapacity) {
        primes = Arrays.copyOf(primes, ArrayUtils.newCapacity(primes.length, minCapacity));
    }

    @Override
    public Iterator<Long> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Long> {
        private int current;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Long next() {
            return get(current++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
