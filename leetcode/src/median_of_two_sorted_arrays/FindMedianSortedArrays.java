package median_of_two_sorted_arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FindMedianSortedArrays {
    private final int[] nums1;
    private final int[] nums2;
    private final double expected;

    public FindMedianSortedArrays(String name, int[] nums1, int[] nums2, double expected) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.expected = expected;
    }

    @Parameters(name="{0}")
    public static Collection<Object[]> testCases() {
        return Arrays.asList(new Object[][] {
                {"1,3|2", new int[]{1,3}, new int[]{2}, 2.0},
                {"1,2|3,4", new int[]{1,2}, new int[]{3,4}, 2.5},
                {"0,0|0,0", new int[]{0,0}, new int[]{0,0}, 0.0, },
                {"|1", new int[]{}, new int[]{1}, 1.0},
                {"2|", new int[]{2}, new int[]{}, 2.0},
                {"2|1,3,4", new int[]{2}, new int[]{1,3,4}, 2.5},
        });
    }
    @Test
    public void findMedianSortedArrays() {
        double result = new Solution().findMedianSortedArrays(nums1, nums2);
        Assert.assertEquals(expected, result, 0.1);
    }
}
