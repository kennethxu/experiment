package gcd_sort_of_an_array;

import java.util.Arrays;

class Solution2 {
    public boolean gcdSort(final int[] nums) {
        final var sorted = nums.clone();
        Arrays.sort(sorted);
        final int len = nums.length, max = sorted[len - 1];
        // grouping tree child(index)->parent(value), index==value is root
        final int[] map = new int[max + 1];
        for (int i = 0; i < len; i++) map[nums[i]] = -1;
        // value: <=0 not sieved, <0 leaf node, 0 or 1 not in nums, >1 grouped
        for (int p = 2; p <= max / 2; p++) {
            if (map[p] > 0) continue; // sieved so not a prime number.
            map[p] = p; // p is now a prime number, set self as root.
            for (int group = p, num = p + p; num <= max; num += p) {
                var existing = map[num];
                if (existing < 0) map[num] = group;  // 1st hit, set group
                else if (existing <= 1) map[num] = 1; // value doens't exist in nums
                else if ((existing = root(map, existing)) < group) {
                    map[group] = existing;
                    group = existing;
                } else map[existing] = group;
            }
        }

        for (int i = 0; i < len; i++) if (root(map, nums[i]) != root(map, (sorted[i]))) return false;
        return true;
    }

    private static int root(final int[] map, int num) {
        for (int group; (group = map[num]) > 0 && group != num; num = group);
        return num;
    }
}

