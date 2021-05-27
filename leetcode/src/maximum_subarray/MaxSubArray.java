package maximum_subarray;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MaxSubArray {
    private final int[] input;
    private final int expected;

    public MaxSubArray(int[] input, int expected, String name) {
        this.input = input;
        this.expected = expected;
    }

    @Parameters(name="{2}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][] {
                {new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6, "-2,1,-3,4,-1,2,1,-5,4"},
                {new int[]{1}, 1, "1"},
                {new int[]{5,4,-1,7,8}, 23, "5,4,-1,7,8"},
        });
    }
    @Test
    public void maxSubArray() {
        int result = new Solution().maxSubArray(input);
        Assert.assertEquals(expected, result);
    }
}
