package com.sharneng.algorithm.euler;

import java.util.Iterator;

/**
 * <a href="https://projecteuler.net/problem=4">Project Euler Problem 4</a> <b>Largest palindrome product</b>.
 * <p>
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is
 * 9009 = 91 99.
 * <p>
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * 
 * @author Kenneth Xu
 * 
 */
// SUPPRESS CHECKSTYLE MagicNumber FOR 1000 LINES BECAUSE
// SUPPRESS CHECKSTYLE HideUtilityClassConstructor BECAUSE
class P4 {

    private static class PalindromicGenerator implements Iterator<Integer> {
        private int i = 999;

        @Override
        public boolean hasNext() {
            return i >= 100;
        }

        @Override
        public Integer next() {
            Integer x = i * 1000 + i % 10 * 100 + i % 100 / 10 * 10 + i / 100;
            i--;
            return x;
        }

        @Override
        public void remove() {
        }
    }

    public static void main(String[] args) {
        PalindromicGenerator g = new PalindromicGenerator();
        while (g.hasNext()) {
            int next = g.next();
            if (verify(next)) {
                System.out.println(next);
                break;
            }
        }
    }

    private static boolean verify(int next) {
        for (int x = (int) Math.sqrt(next); x > 100; x--) {
            int y = next / x;
            if (y > 999) return false;
            if (y * x == next) return true;
        }
        return false;
    }
}
