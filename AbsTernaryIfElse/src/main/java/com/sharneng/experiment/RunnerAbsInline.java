package com.sharneng.experiment;

public class RunnerAbsInline extends Runner {
    private static final int LOOP_COUNT = 10000000;

    private static final class Baseline extends Fixture {
        @Override
        final void run() {
            final int a[] = source;
            final int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    final int x = a[j];
                    b[j] = x;
                }
            }
        }
    }

    private static final class MathAbs extends Fixture {
        @Override
        final void run() {
            final int a[] = source;
            final int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    final int x = a[j];
                    b[j] = Math.abs(x);
                }
            }
        }
    }

    private static final class Ternary extends Fixture {
        @Override
        final void run() {
            final int a[] = source;
            final int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    final int x = a[j];
                    b[j] = x >= 0 ? x : -x;
                }
            }
        }
    }

    private static final class IfElse extends Fixture {
        @Override
        final void run() {
            final int a[] = source;
            final int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    final int x = a[j];
                    if (x >= 0) b[j] = x;
                    else b[j] = -x;
                }
            }
        }
    }

    private static final class IfElse2 extends Fixture {
        @Override
        final void run() {
            final int a[] = source;
            final int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    int x = a[j];
                    if (x < 0) x = -x;
                    b[j] = x;
                }
            }
        }
    }

    public static void main(String[] args) {
        Fixture[] fixtures = new Fixture[] { new Baseline(), new MathAbs(), new Ternary(), new IfElse(), new IfElse2() };
        System.out.println("Min Inline:");
        runAndReport(fixtures, LOOP_COUNT);
    }
}
