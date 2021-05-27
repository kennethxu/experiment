package maximum_subarray;

class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0, maxSum = 0, max = nums[0];
        for (int n : nums) {
            sum += n;
            if (sum < 0) sum = 0;
            if (sum > maxSum) maxSum = sum;
            if (n > max) max = n;
        }
        return maxSum > 0 ? maxSum : max;
    }
}
