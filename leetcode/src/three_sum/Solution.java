package three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;

        Arrays.sort(nums);
        var counters = new Counter(nums);
        final int n = counters.size;

        for (int i = 0; i < n && nums[i] < 0; i++) {
            final int twoSum = -nums[i], half = twoSum / 2;
            final int start = Arrays.binarySearch(nums, i+1, n, twoSum-nums[n-1]);
            for (int j = start < 0 ? -start-1 : start; j < n && nums[j] <= half; j++) {
                final int k = twoSum - nums[j];
                if (k != nums[j] && counters.get(k)>0)  result.add(Arrays.asList(nums[i], nums[j], k));
            }
        }

        for (int i = 0; i < n; i++) {
            int count = counters.get(nums[i]);
            if (nums[i] == 0) {
                if (count >= 3) result.add(Arrays.asList(new Integer[]{0, 0, 0}));
            } else if (count >= 2 && counters.get(-2*nums[i]) > 0)
                result.add(Arrays.asList(nums[i], nums[i], -2*nums[i]));
        }
        return result;
    }

    private static class Counter {
        private final int offset;
        private final int[] counters;
        public final int size;

        Counter(int[] nums) {
            offset = -nums[0];
            counters = new int[nums[nums.length-1] - nums[0] + 1];
            int i = 0;
            for (int v : nums) {
                int j = v + offset;
                int count = counters[j];
                if (count == 0) {
                    nums[i++] = v;
                    counters[j] = 1;
                } else {
                    counters[j] = count + 1;
                }
            }
            size = i;
        }

        int get(int v) {
            int i = v + offset;
            return i < 0 || i >= counters.length ? 0 : counters[i];
        }
    }
}
