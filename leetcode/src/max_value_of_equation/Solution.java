package max_value_of_equation;

class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int j = 1, i = 0, max = Integer.MIN_VALUE, result = Integer.MIN_VALUE;
        for(;i<points.length;) {
            while (points[j][0] - points[i][0] <= k) {
                int current = points[j][0] - points[i][0] + points[j][1] + points[i][1];
                if (current > max) {
                    max = current;
                    if (max > result) result = max;
                }
                j++;
            }
            max += points[i][0] - points[i][1];
            i++;
            max -= points[i][0] - points[i][1];
            if (max > result) result = max;
        }
        return result;
    }
}
