package com.sharneng.experiment;

public class RunnerOrderingInline extends Runner {
    private static final int LOOP_COUNT = 5000000;

    private static final class Baseline extends Fixture {
        @Override
        final void run() {
            int a[] = source;
            int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    final int x = a[j];
                    final int y = a[j + 1];
                    b[j] = x;
                    b[j + 1] = y;
                }
            }
        }
    }

    private static final class MathAbs extends Fixture {
        @Override
        final void run() {
            int a[] = source;
            int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    final int x = a[j];
                    final int y = a[j + 1];
                    int diff = Math.abs(x - y);
                    final int sum = x + y;
                    b[j] = sum - diff;
                    b[j + 1] = sum + diff;
                }
            }
        }
    }

    private static final class Ternary extends Fixture {
        @Override
        final void run() {
            int a[] = source;
            int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    final int x = a[j];
                    final int y = a[j + 1];
                    final int diff = x >= y ? x - y : y - x;
                    final int sum = x + y;
                    b[j] = sum - diff;
                    b[j + 1] = sum + diff;
                }
            }
        }
    }

    private static final class IfElse extends Fixture {
        @Override
        final void run() {
            int a[] = source;
            int b[] = target;
            for (int i = LOOP_COUNT; i > 0; i--) {
                for (int j = ELEMENT_END; j >= 0; j--) {
                    final int x = a[j];
                    final int y = a[j + 1];
                    if (x >= y) {
                        b[j] = x;
                        b[j + 1] = y;
                    } else {
                        b[j] = y;
                        b[j + 1] = x;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Fixture[] fixtures = new Fixture[] { new Baseline(), new MathAbs(), new Ternary(), new IfElse() };
        System.out.println("Ordering Inline:");
        runAndReport(fixtures, LOOP_COUNT);
    }
}
