class Solution {
    private static final int MOD = 1_000_000_007;

    private int m, n;
    private int[][] grid;
    private int[][][] memo;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        // -2 means not calculated yet.
        // -1 means invalid path.
        memo = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = -2;
                memo[i][j][1] = -2;
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 || dp[i][j] == 0) {
                    continue;
                }

                // Attempt to move RIGHT.
                int rightDestination = resolve(i, j + 1, 0);

                if (rightDestination != -1) {
                    int r = rightDestination / n;
                    int c = rightDestination % n;

                    dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                }

                // Attempt to move DOWN.
                int downDestination = resolve(i + 1, j, 1);

                if (downDestination != -1) {
                    int r = downDestination / n;
                    int c = downDestination % n;

                    dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    // dir = 0: moving RIGHT
    // dir = 1: moving DOWN
    private int resolve(int r, int c, int dir) {
        // Robot moved outside the grid.
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return -1;
        }

        if (memo[r][c][dir] != -2) {
            return memo[r][c][dir];
        }

        int result;

        if (grid[r][c] == 0) {
            // Empty cell: robot can stop reflecting here.
            result = r * n + c;
        } else {
            // Mirror: change direction and continue.
            if (dir == 0) {
                // Entered mirror while moving RIGHT.
                // Reflect DOWN into the cell below it.
                result = resolve(r + 1, c, 1);
            } else {
                // Entered mirror while moving DOWN.
                // Reflect RIGHT into the cell beside it.
                result = resolve(r, c + 1, 0);
            }
        }

        memo[r][c][dir] = result;
        return result;
    }
}