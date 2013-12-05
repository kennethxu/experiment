package com.sharneng.algorithm.euler;

/**
 * <a href="https://projecteuler.net/problem=3">Project Euler Problem 3</a> <b>Largest prime factor</b>.
 * <p>
 * The prime factors of 13195 are 5, 7, 13 and 29. What is the largest prime factor of the number 600851475143?
 * 
 * @author Kenneth Xu
 * 
 */
// SUPPRESS CHECKSTYLE MagicNumber FOR 1000 LINES BECAUSE
// SUPPRESS CHECKSTYLE HideUtilityClassConstructor BECAUSE
class P3 {
    private static final long max = 600851475143L;
    private static long[] primes = new long[1000000];
    private static int index = 0;

    public static void main(String[] args) {
        System.out.println(findLpfOf(max));
        System.out.println(findLpfOf2(max));
    }

    private static long findLpfOf2(long number) {
        long divisor = 2;
        while (number > 1) {
            if (0 == (number % divisor)) {
                number /= divisor;
                divisor--;
            }
            divisor++;
        }
        return divisor;
    }

    private static long findLpfOf(long value) {
        primes[0] = 2;
        index = 1;
        long lpf = value % 2 == 0 ? value / 2 : value;
        for (long l = 3; l < lpf; l += 2) {
            if (isPrime(l)) {
                primes[index++] = l;
                if (lpf % l == 0) lpf = lpf / l;
            }
        }
        return lpf;
    }

    private static boolean isPrime(long l) {
        for (int i = 0; i < index; i++) {
            long p = primes[i];
            if (l % p == 0) return false;
            if (primes[i + 1] > l / p) return true;
        }
        return true;
    }
}
