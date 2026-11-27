class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] bonus = new int[n];

        // Calculate free items for each item
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j &&
                    items[j][0] % items[i][0] == 0) {
                    bonus[i]++;
                }
            }
        }

        // Knapsack DP
        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int firstValue = 1 + bonus[i];

            int[] next = dp.clone();

            for (int b = price; b <= budget; b++) {

                // First copy gives bonus free items
                next[b] = Math.max(
                    next[b],
                    dp[b - price] + firstValue
                );

                // Additional copies give only 1 item
                next[b] = Math.max(
                    next[b],
                    next[b - price] + 1
                );
            }

            dp = next;
        }

        return dp[budget];
    }
}