package com.sharneng.algorithm.euler;

/**
 * <a href="https://projecteuler.net/problem=1">Project Euler Problem 1</a> <b>Multiples of 3 and 5</b>.
 * <p>
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these
 * multiples is 23.
 * <p>
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * 
 * @author Kenneth Xu
 * 
 */
// SUPPRESS CHECKSTYLE HideUtilityClassConstructor BECAUSE
final class P1 {

    public static void main(String[] args) {
        // SUPPRESS CHECKSTYLE MagicNumber BECAUSE
        System.out.println(sumArithmeticSeries(3, 1000) + sumArithmeticSeries(5, 1000) - sumArithmeticSeries(15, 1000));
    }

    static int sumArithmeticSeries(int inc, final int limit) {
        int count = (limit - 1) / inc;
        return inc * (1 + count) * count / 2;
    }
}
