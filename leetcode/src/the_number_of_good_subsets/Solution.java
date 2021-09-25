package the_number_of_good_subsets;

class Solution {
    private static final int MOD = 1000000007;
    private static final int[] mask = new int[]{
         // 0   1   2   3  4  5   6   7  8   9
            0,  0,  1,  2, 0, 4,  3,  8, 0,  0,
            5, 16,  0, 32, 9, 6,  0, 64, 0,128,
            0, 10, 17,256, 0, 0, 33,  0, 0,512, 7,
    };
    private static final int[] candidates = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 6, 10, 14, 15, 21, 22, 26, 30};

    public int numberOfGoodSubsets(int[] nums) {
        int[] counts = new int[31];
        for (int n : nums) counts[n]++;
        dfs(counts, 0, 0, 1);
        int ans = counts[0] - 1;
        for (int one = counts[1]; one > 0; one--) ans = (ans * 2) % MOD;
        return ans;
    }

    private void dfs(int[] counts, int used, int pos, int count) {
        if (pos == candidates.length) {
            counts[0] = (counts[0]+count) % MOD;
            return;
        }
        int candidate = candidates[pos];
        long c = counts[candidate];
        if (c > 0 && (used & mask[candidate]) == 0) {
            used |= mask[candidate];
            dfs(counts, used, pos + 1, (int)((count * c) % MOD));
            used &= ~mask[candidate];
        }
        dfs(counts, used, pos + 1, count);
    }
}
