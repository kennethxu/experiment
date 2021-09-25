package three_sum;

import java.util.*;

class Solution2 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;

        Arrays.sort(nums);
        var counters = count(nums);
        final int n = counters.size();

        for (int i = 0; i < n && nums[i] < 0; i++) {
            final int twoSum = -nums[i], half = twoSum / 2;
            for (int j = i + 1; j < n && nums[j] <= half; j++) {
                final int k = twoSum - nums[j];
                if (k != nums[j] && counters.containsKey(k))  result.add(Arrays.asList(new Integer[]{nums[i], nums[j], k}));
            }
        }

        for (int i = 0; i < n; i++) {
            int count = counters.getOrDefault(nums[i], 0);
            if (nums[i] == 0) {
                if (count >= 3) result.add(Arrays.asList(new Integer[]{0, 0, 0}));
            } else if (count >= 2 && counters.containsKey(-2*nums[i]))
                result.add(Arrays.asList(new Integer[]{nums[i], nums[i], -2*nums[i]}));
        }
        return result;
    }

    private static Map<Integer, Integer> count(int[] nums) {
        Map<Integer, Integer> counters = new HashMap<>();
        int i = 0;
        for (int v : nums) {
            var count = counters.get(v);
            if (count == null) {
                nums[i++] = v;
                counters.put(v, 1);
            } else {
                counters.put(v, count+1);
            }
        }
        return counters;
    }
}
