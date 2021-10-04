package three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;

        Arrays.sort(nums);
        final int min = nums[0], max = nums[nums.length - 1];
        final int[] counters = new int[max - min + 1];

        int n = 0;
        for (int v : nums) {
            int j = v - min;
            int count = counters[j];
            if (count == 0) {
                nums[n++] = v;
                counters[j] = 1;
            } else {
                counters[j] = count + 1;
            }
        }

        for (int i = 0; i < n && nums[i] < 0; i++) {
            final int twoSum = -nums[i], half = twoSum / 2;
            final int start = Arrays.binarySearch(nums, i+1, n, twoSum-nums[n-1]);
            for (int j = start < 0 ? -start-1 : start; j < n && nums[j] <= half; j++) {
                final int k = twoSum - nums[j];
                if (k != nums[j] && k >= min && k <= max && counters[k -min] > 0)  {
                    result.add(Arrays.asList(nums[i], nums[j], k));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int count = counters[nums[i] - min], k = -2*nums[i];
            if (count >= 2 && k >= min && k <= max && counters[k - min] > 0 && (nums[i] != 0 || count >= 3)) {
                result.add(Arrays.asList(nums[i], nums[i], k));
            }
        }
        return result;
    }

}
