package smallest_missing_genetic_value_in_each_subtree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

@RunWith(Parameterized.class)
public class SmallestMissingValueSubtree {
    private final int[] nums;
    private final int[] parents;
    private final int[] expected;

    public SmallestMissingValueSubtree(String name, int[] parents, int[] nums, int[] expected) {
        this.nums = nums;
        this.parents = parents;
        this.expected = expected;
    }

    @Parameters(name="{0}")
    public static Collection<Object[]> testCases() throws Exception {
        return Arrays.asList(new Object[][] {
                {"Test1", new int[]{-1,0,0,2}, new int[]{1,2,3,4}, new int[]{5,1,1,1}},
                {"Test2", new int[]{-1,0,1,0,3,3}, new int[]{5,4,6,2,1,3}, new int[]{7,1,1,4,2,1}},
                {"Test3", new int[]{-1,2,3,0,2,4,1}, new int[]{2,3,4,5,6,7,8}, new int[]{1,1,1,1,1,1,1}},
                {"Test4", new int[]{-1,0,0,2}, new int[]{5,3,2,1}, new int[]{4,1,3,2}},
                {"Test5", new int[]{-1,2,3,0,2,4,1}, new int[]{5,8,3,4,9,2,1}, new int[]{6,2,4,5,1,1,2}},
                loadTestData("TestData100000.txt"),
        });
    }
    private static Object[] loadTestData(String file) throws Exception {
        try(Scanner scanner = new Scanner(SmallestMissingValueSubtree.class.getResourceAsStream(file))) {
            scanner.useDelimiter("[,\n]");
            var list = new ArrayList<Integer>();
            while(scanner.hasNext()) list.add(scanner.nextInt());
            var len = list.size() / 3;
            int[] parents = new int[len], nums = new int[len], expected = new int[len];
            for (int i = 0; i < len; i++) {
                parents[i] = list.get(i);
                nums[i] = list.get(i + len);
                expected[i] = list.get(i + len + len);
            }
            return new Object[]{file, parents, nums, expected};
        }
    }

    @Test
    public void smallestMissingValueSubtree() {
        Solution solution = new Solution();
        int[] result = solution.smallestMissingValueSubtree(parents, nums);
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void smallestMissingValueSubtree2() {
        var solution = new Solution2();
        long time = System.nanoTime();
        int[] result = solution.smallestMissingValueSubtree(parents, nums);
        time = System.nanoTime() - time;
        System.out.println("Length: " + nums.length + ", Complexity: " + solution.complexity + ", Time: " + time / 1000.0);
        Assert.assertArrayEquals(expected, result);
    }
}
