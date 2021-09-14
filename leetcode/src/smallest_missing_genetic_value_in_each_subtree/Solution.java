package smallest_missing_genetic_value_in_each_subtree;

class Solution {
    public int[] smallestMissingValueSubtree(final int[] parents, final int[] nums) {
        int len = parents.length, p1 = -1, max = 1;
        int[] result = new int[len], temp = new int[len];
        // initialized
        for (var i = 0; i < len; i++) {
            result[i] = 1;  // initialized the result to all 1
            if (nums[i] == 1) p1 = i; // find the position of node 1
            else if (nums[i] > max) max = nums[i]; // >len can never be the smallest missing
        }
        if (p1 < 0) return result; // is there is no node 1, all missing is 1

        // build the tree
        int[] counts = temp, empty = new int[0];
        for (int parent : parents) if (parent>=0) counts[parent]++;
        int[][] tree = new int[len][];
        for (int parent = 0; parent < len; parent++) {
            tree[parent] = counts[parent] == 0 ? empty : new int[counts[parent]];
        }
        for (int child = 0; child < len; child++) {
            int parent = parents[child];
            if (parent >= 0) tree[parent][--counts[parent]] = child;
        }

        var visited = new boolean[max+1];
        int[] queue = temp;
        // going from node 1 to root, only those nodes have smallest missing > 1
        for (int missing = 2, node = p1; node >= 0 ; node = parents[node]) {
            // BFS - compare to DFS, it avoids recursion.
            int head = 0, tail = 0;
            queue[tail++] = node;
            while(tail != head) {
                var n = queue[head++];
                visited[nums[n]] = true; // track visited also serve as value set.
                for (var child : tree[n]) {
                    if(!visited[nums[child]]) queue[tail++] = child;
                }
            }
            // find next missing number that is not in the value set.
            while(missing <= len && visited[missing]) missing++;
            result[node] = missing;
        }

        return result;
    }
}