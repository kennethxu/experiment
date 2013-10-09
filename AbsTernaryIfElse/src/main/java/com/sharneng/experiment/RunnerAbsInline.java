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
                    b[j] = a[j];
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
                    b[j] = Math.abs(a[j]);
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
                    b[j] = a[j] >= 0 ? a[j] : -a[j];
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
                    if (a[j] >= 0) b[j] = a[j];
                    else b[j] = -a[j];
                }
            }
        }
    }

    private static final class IfOnly extends Fixture {
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
        Fixture[] fixtures = new Fixture[] { new Baseline(), new MathAbs(), new Ternary(), new IfElse(), new IfOnly() };
        System.out.println("Abs Inline:");
        runAndReport(fixtures, LOOP_COUNT);
    }
}
