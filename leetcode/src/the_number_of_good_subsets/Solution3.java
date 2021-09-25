package the_number_of_good_subsets;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    private static final int MOD = 1000000007;

    public int numberOfGoodSubsets(int[] nums) {
        int[] count = new int[31];
        for (int n : nums) {
            count[n]++;
        }
        int ans = 0;
        outer: for (var c : combinations) {
            long p = 1;
            for (int i : c) {
                if (i != 0) {
                    if (count[i] == 0) continue outer;
                    else p = (p * count[i]) % MOD;
                }
            }
            ans = (ans + (int)p) % MOD;
        }
        ans--;
        for (int one = count[1]; one > 0; one--) ans = (ans * 2) % MOD;
        return ans;
    }

    private static final int[][] v2p= new int[][]{
            {}, {1}, {2}, {3}, {}, {5}, {2, 3}, {7}, {}, {}, {2, 5},
            {11}, {}, {13}, {2, 7}, {3, 5}, {}, {17}, {}, {19}, {},
            {3, 7}, {2, 11}, {23}, {}, {}, {2, 13}, {}, {}, {29}, {2, 3, 5},
    };
    private static final int[] candidates = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 6, 10, 14, 15, 21, 22, 26, 30};
    static List<int[]> combinations = new ArrayList<>();

    static {
        dfs(new int[candidates.length], 0, new boolean[30]);
    }

    private static void dfs(int[] nums, int count, boolean[] used) {
        if (count == candidates.length) {
            combinations.add(nums.clone());
            return;
        }
        int candidate = candidates[count];
        boolean skip = false;
        for (int n : v2p[candidate]) {
            if (used[n]) {
                skip = true;
                break;
            }
        }
        if (!skip) {
            for (int n : v2p[candidate]) used[n] = true;
            nums[count] = candidate;
            dfs(nums, count + 1, used);

            nums[count] = 0;
            for (int n : v2p[candidate]) used[n] = false;
        }
        dfs(nums, count + 1, used);
    }
}
