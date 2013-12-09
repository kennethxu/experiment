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
        final int limit = 1000;
        System.out.println(sumOfMultiplesOf3and5(limit));
    }

    static int sumOfMultiplesOf3and5(final int limit) {
        final int three = 3;
        final int five = 5;
        return sumArithmeticSeries(three, limit, three) + sumArithmeticSeries(five, limit, five)
                - sumArithmeticSeries(three * five, limit, three * five);
    }

    static int sumArithmeticSeries(final int start, final int end, final int inc) {
        final int count = (end - start + inc - 1) / inc;
        return (start + start + (count - 1) * inc) * count / 2;
    }
}
