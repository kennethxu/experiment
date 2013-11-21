package com.sharneng.experiment.misc;
import org.junit.Assert;
import org.junit.Test;

public class AT {

    boolean check(String s) {
        int count = 0;
        boolean letterInBracket = false;
        for (char c : s.toCharArray()) {
            switch (c) {
            case 'A':
            case 'T':
                if (letterInBracket) return false; // [AT or [[A]B
                if (count > 0) letterInBracket = true;
                break;
            case '[':
                if (letterInBracket) return false; // [A[ or [[A][
                count++; // [[
                break;
            case ']':
                if (count == 0 || !letterInBracket) return false; // [[A]]], ] or []
                count--;
                if (count == 0) letterInBracket = false;
                break;
            default:
                return false;
            }
        }
        return (count == 0);
    }

    @Test
    public void case1() {
        Assert.assertTrue(check("A"));
    }

    @Test
    public void case2() {
        Assert.assertTrue(check("[[A]]T"));
    }

    @Test
    public void case3() {
        Assert.assertTrue(check("[A][[[T]]]"));
    }

    @Test
    public void case4() {
        Assert.assertTrue(check("ATATAT"));
    }

    @Test
    public void case5() {
        Assert.assertTrue(check("AAAA"));
    }

    @Test
    public void case6() {
        Assert.assertTrue(check("A[[T]]A"));
    }

    @Test
    public void falseCase1() {
        Assert.assertFalse(check("A[T"));
    }

    @Test
    public void falseCase2() {
        Assert.assertFalse(check("]AT"));
    }

    @Test
    public void falseCase3() {
        Assert.assertFalse(check("A]T"));
    }

    @Test
    public void falseCase4() {
        Assert.assertFalse(check("[A[T]]"));
    }

    @Test
    public void falseCase5() {
        Assert.assertFalse(check("A[[T]A]"));
    }

    @Test
    public void falseCase6() {
        Assert.assertFalse(check("ABT"));
    }

    @Test
    public void falseCase7() {
        Assert.assertFalse(check("A[B]T"));
    }

    @Test
    public void falseCase8() {
        Assert.assertFalse(check("[[A]"));
    }

    @Test
    public void falseCase9() {
        Assert.assertFalse(check("[T]]"));
    }

    @Test
    public void falseCase10() {
        Assert.assertFalse(check("A[]"));
    }

}
