package biological_hazards;

public class Solution {
    int bioHazard(int n, int[] allergic, int[] poisonous) {
        int[] counter = new int[n+1];
        for (int i = allergic.length - 1; i >= 0; i--) {
            int index = Math.max(allergic[i], poisonous[i]);
            int value = Math.abs(allergic[i] - poisonous[i]);
            counter[index] = Math.min(counter[index], value);
        }
        int total = 0;
        for (int i = 1, count = 1; i <= n; i++, count++) {
            total += (counter[i] == 0) ? count : (count = counter[i]);
        }
        return total;
    }
}
