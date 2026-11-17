
class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1_000_000_000;

        
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {

                int best = INF;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;
                    int groupLength = len / d;

                    // Check every group
                    for (int offset = 0; offset < d; offset++) {

                        // Compare mirrored characters
                        for (int x = 0; x < groupLength / 2; x++) {

                            int left = i + offset + x * d;

                            int right = i + offset
                                    + (groupLength - 1 - x) * d;

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[i][i + len - 1] = best;
            }
        }

        // Step 2: DP
        int[][] dp = new int[k + 1][n + 1];

        for (int p = 0; p <= k; p++) {
            java.util.Arrays.fill(dp[p], INF);
        }

        dp[0][0] = 0;

        // Number of partitions
        for (int p = 1; p <= k; p++) {

            // Number of characters considered
            for (int i = 2 * p; i <= n; i++) {

                // Starting index of last substring
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[p - 1][j] == INF) {
                        continue;
                    }

                    int currentCost =
                            dp[p - 1][j] + cost[j][i - 1];

                    dp[p][i] = Math.min(
                            dp[p][i],
                            currentCost
                    );
                }
            }
        }

        return dp[k][n];
    }
}
