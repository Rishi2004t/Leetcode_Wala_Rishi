class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Base optimizations: 
        // 1. Total path length (m + n - 1) must be even to balance parentheses.
        // 2. Start must be '(' and end must be ')'.
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        // Maximum possible open parentheses balance is (m + n) / 2
        int maxK = (m + n) / 2 + 1;
        memo = new Boolean[m][n][maxK];

        return dfs(0, 0, 0, grid);
    }

    private boolean dfs(int r, int c, int k, char[][] grid) {
        // Update balance: '(' adds 1, ')' subtracts 1
        k += (grid[r][c] == '(') ? 1 : -1;

        // If balance goes negative, it's an invalid path prefix
        // If balance exceeds remaining possible steps, it can never reach 0
        if (k < 0 || k > (m - r + n - c - 1)) {
            return false;
        }

        // Destination reached: check if all parentheses are balanced
        if (r == m - 1 && c == n - 1) {
            return k == 0;
        }

        // Return cached result if already calculated
        if (memo[r][c][k] != null) {
            return memo[r][c][k];
        }

        // Try moving down
        if (r + 1 < m && dfs(r + 1, c, k, grid)) {
            return memo[r][c][k] = true;
        }

        // Try moving right
        if (c + 1 < n && dfs(r, c + 1, k, grid)) {
            return memo[r][c][k] = true;
        }

        return memo[r][c][k] = false;
    }
}
