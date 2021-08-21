package sum_of_even_numbers_after_queries;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SumEvenAfterQueries {
    private final int[] nums;
    private final int[][] queries;
    private final int[] expected;

    public SumEvenAfterQueries(String name, int[] nums, int[][] queries, int[] expected) {
        this.nums = nums;
        this.queries = queries;
        this.expected = expected;
    }

    @Parameters(name="{0}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][] {
                {"Test1", new int[]{1,2,3,4}, new int[][]{{1,0},{-3,1},{-4,0},{2,3}}, new int[]{8,6,2,4}},
        });
    }
    @Test
    public void sumEvenAfterQueries() {
        int[] result = new Solution().sumEvenAfterQueries(nums, queries);
        Assert.assertArrayEquals(expected, result);
    }
}
