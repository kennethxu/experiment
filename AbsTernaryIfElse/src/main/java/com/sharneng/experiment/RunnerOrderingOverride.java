package com.sharneng.experiment;

public class RunnerOrderingOverride extends Runner {
    private static final int LOOP_COUNT = 1000000;

    private static abstract class FixtureBase extends Fixture {

        @Override
        void run() {
            int a[] = source;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    compareSet(a[j], a[j + 1], j);
                }
            }
        }

        abstract void compareSet(int x, int y, int index);
    }

    private static final class Baseline extends FixtureBase {

        @Override
        final void compareSet(int x, int y, int index) {
            int b[] = target;
            b[index++] = x;
            b[index] = y;
        }
    }

    private static final class MathAbs extends FixtureBase {
        @Override
        final void compareSet(int x, int y, int index) {
            int b[] = target;
            int diff = Math.abs(x - y);
            int sum = x + y;
            b[index++] = sum - diff;
            b[index] = sum + diff;
        }
    }

    private static final class Ternary extends FixtureBase {
        @Override
        final void compareSet(int x, int y, int index) {
            int b[] = target;
            int diff = x >= y ? x - y : y - x;
            int sum = x + y;
            b[index++] = sum - diff;
            b[index] = sum + diff;
        }
    }

    private static final class IfElse extends FixtureBase {
        @Override
        final void compareSet(int x, int y, int index) {
            int b[] = target;
            if (x >= y) {
                b[index++] = y;
                b[index] = x;
            } else {
                b[index++] = x;
                b[index] = y;
            }
        }
    }

    public static void main(String[] args) {
        Fixture[] fixtures = new Fixture[] { new Baseline(), new MathAbs(), new Ternary(), new IfElse() };
        System.out.println("Swap Override:");
        runAndReport(fixtures, LOOP_COUNT);
    }
}
