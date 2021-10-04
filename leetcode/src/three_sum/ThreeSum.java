package three_sum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class ThreeSum {
    private final int[] input;
    private final String expected;

    public ThreeSum(String name, int[] input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameters(name="{0}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][] {
                { "-1,0,1,2,-1,-4", new int[]{-1,0,1,2,-1,-4}, "[[-1,-1,2],[-1,0,1]]"},
                { "Empty", new int[0], "[]"},
                {"0", new int[]{0}, "[]"},
                {"0,1,1", new int[]{0,1,1}, "[]"},
                {"-1,0,1,0", new int[]{-1,0,1,0}, "[[-1,0,1]]"},
        });
    }
    @Test
    public void threeSum() {
        Solution solution = new Solution();
        var result = solution.threeSum(input);
        Assert.assertEquals(expected, toString(result));
    }
    @Test
    public void threeSum2() {
        var result = new Solution2().threeSum(input);
        Assert.assertEquals(expected, toString(result));
    }

    private String toString(List<List<Integer>> answers) {
        if (answers.isEmpty()) return "[]";
        for (List<Integer> answer : answers) {
            Collections.sort(answer);
        }
        Collections.sort(answers, (a, b) -> compare(a, b));
        var sb = new StringBuilder();
        sb.append('[');
        for (List<Integer> answer : answers) {
            sb.append('[');
            for (Integer a : answer) {
                sb.append(a).append(',');
            }
            sb.setCharAt(sb.length()-1, ']');
            sb.append(',');
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }


    private int compare(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            int v = a.get(i) - b.get(i);
            if (v != 0) return v;
        }
        return 0;
    }
}
