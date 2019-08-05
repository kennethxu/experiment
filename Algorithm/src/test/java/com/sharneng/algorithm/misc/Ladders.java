package com.sharneng.algorithm.misc;
import org.junit.*;

public class Ladders {

    public int maxTower(int x, int y) {
        assert (x > y);
        if (y == 1) return 0;
        if (lcf(x, y) > 1) return -1;

        int result = y - 1;
        for (int i = y; i <= result + y; i++) {
            if (!canReach(i, x, y)) result = i;
        }
        return result;
    }

    private int lcf(int x, int y) {
        int r = x % y;
        return r == 0 ? y : lcf(y, r);
    }

    private boolean canReach(int t, int x, int y) {
        for (int i = t / x; i >= 0; i--) {
            if ((t - i * x) % y == 0) return true;
        }
        return false;
        // return t == x || t == y || (t > x && canReach(t - x, x, y)) || (t > y && canReach(t - y, x, y));
    }

    @Test
    public void canReachTrueCase1() {
        Assert.assertTrue(canReach(3, 5, 3));
    }

    @Test
    public void canReachTrueCase2() {
        Assert.assertTrue(canReach(5, 5, 3));
    }

    @Test
    public void canReachTrueCase3() {
        Assert.assertTrue(canReach(6, 5, 3));
    }

    @Test
    public void canReachTrueCase4() {
        Assert.assertTrue(canReach(9, 5, 3));
    }

    @Test
    public void canReachTrueCase5() {
        Assert.assertTrue(canReach(10, 5, 3));
    }

    @Test
    public void canReachTrueCase6() {
        Assert.assertTrue(canReach(11, 5, 3));
    }

    @Test
    public void canReachFalseCase1() {
        Assert.assertFalse(canReach(2, 5, 3));
    }

    @Test
    public void canReachFalseCase2() {
        Assert.assertFalse(canReach(4, 5, 3));
    }

    @Test
    public void canReachFalseCase3() {
        Assert.assertFalse(canReach(7, 5, 3));
    }

    @Test
    public void maxTowerInfiniteCase1() {
        Assert.assertEquals(-1, maxTower(15, 21));
    }

    @Test
    public void maxTowerInfiniteCase2() {
        Assert.assertEquals(-1, maxTower(12, 10));
    }

    @Test
    public void maxTowerZeroCase1() {
        Assert.assertEquals(0, maxTower(5, 1));
    }

    @Test
    public void maxTowerCase1() {
        Assert.assertEquals(7, maxTower(5, 3));
    }

    @Test
    public void maxTowerCase2() {
        Assert.assertEquals(17 * 13 - 17 - 13, maxTower(17, 13));
    }

    @Test
    public void maxTowerCase3() {
        Assert.assertEquals(2 * 3 - 2 - 3, maxTower(3, 2));
    }

    @Test
    public void maxTowerCase4() {
        Assert.assertEquals(35 * 33 - 35 - 33, maxTower(35, 33));
    }

}
