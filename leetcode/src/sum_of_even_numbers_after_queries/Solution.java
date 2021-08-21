package sum_of_even_numbers_after_queries;

class Solution {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int sum = 0;
        int[] result = new int[nums.length];
        for(int num : nums) if(num % 2 == 0) sum += num;
        for(int i=0; i < queries.length; i++) {
            int index = queries[i][1];
            if(nums[index] % 2 == 0) sum -= nums[index];
            nums[index] += queries[i][0];
            if(nums[index] % 2 == 0) sum += nums[index];
            result[i] = sum;
        }
        return result;
    }
}
