class Solution {

    public int maxPalindromes(String s, int k) {

        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {

                int j = i + len - 1;

                if (len == 1) {
                    pal[i][j] = true;
                } else if (len == 2) {
                    pal[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    pal[i][j] =
                        s.charAt(i) == s.charAt(j) &&
                        pal[i + 1][j - 1];
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {

            dp[i] = dp[i + 1];

            for (int j = i + k - 1; j < n; j++) {

                if (pal[i][j]) {
                    dp[i] = Math.max(dp[i],
                                     1 + dp[j + 1]);
                    break;
                }
            }
        }

        return dp[0];
    }
}