package smallest_missing_genetic_value_in_each_subtree;

class Solution {
    long complexity = 0;

    public int[] smallestMissingValueSubtree(final int[] parents, final int[] nums) {
        int len = parents.length, p1 = -1;
        int[] result = new int[len];
        for (var i = 0; i < len; i++) {
            result[i] = 1;
            if (nums[i] == 1) p1 = i;
            else if (nums[i] > len) nums[i] = 0;
        }
        if (p1 < 0) return result;

        int[][] tree = buildTree(parents);
        var visited = new boolean[len+1];
        visited[0] = true;

        for (int missing = 2, node = p1; node >= 0 ; node = parents[node]) {
            dfs(tree, node, visited, nums);
            while(missing <= len && visited[missing]) missing++;
            result[node] = missing;
        }

        return result;
    }

    private void dfs(int[][] tree, int node, boolean[] visited, int[] nums) {
        visited[nums[node]] = true;
        for (var child : tree[node]) {
            if(!visited[nums[child]]) dfs(tree, child, visited, nums);
        }
    }

    private int[][] buildTree(final int[] parents) {
        int len = parents.length;
        int[] counts = new int[len], empty = new int[0];
        for (int parent : parents) if (parent>=0) counts[parent]++;
        int[][] tree = new int[len][];
        for (int parent = 0; parent < len; parent++) {
            tree[parent] = counts[parent] == 0 ? empty : new int[counts[parent]];
        }
        for (int child = 0; child < len; child++) {
            int parent = parents[child];
            if (parent >= 0) tree[parent][--counts[parent]] = child;
        }
        return tree;
    }
}