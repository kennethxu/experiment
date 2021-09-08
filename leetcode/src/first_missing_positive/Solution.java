package first_missing_positive;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            var v = nums[i] - 1;
            if (v < 0 || v >= nums.length) {
                nums[i] = 0;
            } else if (v != i) {
                nums[i] = nums[v] == nums[i] ? 0 : nums[v]; // avoid replacing with self
                nums[v] = v + 1;
                i--;
            }
        }
        int i = 0; while( i < nums.length && nums[i] > 0) i++;
        return i + 1;
    }
}
