package com.sharneng.experiment;

public class RunnerAbsOverride extends Runner {
    private static final int LOOP_COUNT = 2000000;

    private static class FixtureBase extends Fixture {

        @Override
        void run() {
            int a[] = source;
            int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    int x = a[j];
                    b[j] = compute(x);
                }
            }
        }

        int compute(int x) {
            return x;
        }
    }

    private static final class Baseline extends FixtureBase {

        @Override
        final int compute(int x) {
            return x;
        }
    }

    private static final class MathAbs extends FixtureBase {
        @Override
        final int compute(int x) {
            return Math.abs(x);
        }
    }

    private static final class Ternary extends FixtureBase {
        @Override
        final int compute(int x) {
            return x >= 0 ? x : -x;
        }
    }

    private static final class IfElse extends FixtureBase {
        @Override
        final int compute(int x) {
            if (x >= 0) return x;
            else return -x;
        }
    }

    public static void main(String[] args) {
        Fixture[] fixtures = new Fixture[] { new Baseline(), new MathAbs(), new Ternary(), new IfElse() };
        System.out.println("Min Override:");
        runAndReport(fixtures, LOOP_COUNT);
    }
}
