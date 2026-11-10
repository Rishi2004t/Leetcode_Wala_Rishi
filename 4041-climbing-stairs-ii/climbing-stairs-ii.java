
class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[n + 1];

        // Starting step has zero cost.
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            dp[i] = Integer.MAX_VALUE;

            // Try jumping 1, 2, or 3 steps.
            for (int jump = 1; jump <= 3; jump++) {

                int previous = i - jump;

                if (previous >= 0) {

                    int currentCost =
                        dp[previous] + costs[i - 1] + jump * jump;

                    dp[i] = Math.min(dp[i], currentCost);
                }
            }
        }

        return dp[n];
    }
}
