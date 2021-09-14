package smallest_missing_genetic_value_in_each_subtree;

class Solution2 {
    long complexity = 0;

    // Time: Average O(n*log(n)), worst case O(n^2)
    // Space: O(n)
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int len = parents.length;
        int[] result = new int[len], temp = new int[len];
        for (var i = 0; i < len; i++) {
            result[i] = 1;
            if (nums[i] <= len) temp[nums[i] - 1] = i + 1;
        }
        for (int i = 1; i <= len; i++) {
            var node = temp[i-1] - 1;
            if (node < 0) return result;
            do {
                if (result[node] == i) result[node] = i+1;
                complexity++;
                node = parents[node];
            } while (node >= 0);
        }
        return result;
    }
}