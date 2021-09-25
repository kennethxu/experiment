package the_number_of_good_subsets;

public class Solution2 {
    private static final int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
    private static final int[] intersect = new int[]{6, 10, 14, 15, 21, 22, 26, 30};

    // pure math, unfinished.
    public int numberOfGoodSubsets(int[] nums) {
        int[] count = new int[31];
        for (int n : nums) {
            count[n]++;
        }
        int[] ans = new int[31];
        ans[0] = 1;
        for (int prime : new int[]{2, 3, 5, 7, 11, 13}) {
            int sum = count[prime]+1;
            for (int i : intersect) {
                if (i % prime == 0) sum += count[i];
            }
            for (int i : intersect) if (i % prime != 0) ans[i] *= sum;
            for (int i : intersect) {
                if (count[i] > 0 && i % prime == 0) {
                    ans[i] = count[i] * ans[0];
                    count[i] = 0;
                }
            }
            ans[0] *= count[prime]+1;
        }

        int result = 0;
        for (int i : ans) result +=i;
        for (int prime : new int[]{17, 19, 23, 29}) result *= count[prime] + 1;
        return (result - 1) * (1<<count[1]);
    }
}
