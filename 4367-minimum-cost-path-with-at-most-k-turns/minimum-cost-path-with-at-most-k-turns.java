class Solution {


  public int minCost(int[][] grid, int k)
  {
    int m = grid.length;
    int n = grid[0].length;
    if (m == 1 && n == 1)
    {
      return grid[0][0];
    }
    int[][] di = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int inf = 0x3f3f3f3f;
    int[][][][] d = new int[m][n][4][k + 1];
    for (int i = 0; i < m; ++i)
    {
      for (int j = 0; j < n; ++j)
      {
        for (int x = 0; x < 4; ++x)
        {
          Arrays.fill(d[i][j][x], inf);
        }
      }
    }
    PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    for (int x = 0; x < 4; ++x)
    {
      int i = di[x][0];
      int j = di[x][1];
      if (i >= 0 && i < m && j >= 0 && j < n)
      {
        int c = grid[0][0] + grid[i][j];
        d[i][j][x][0] = c;
        q.add(new int[] {c, i, j, x, 0});
      }
    }
    while (!q.isEmpty())
    {
      int[] u = q.poll();
      int c = u[0];
      int i = u[1];
      int j = u[2];
      int dr = u[3];
      int t = u[4];
      if (c != d[i][j][dr][t])
      {
        continue;
      }
      for (int nd = 0; nd < 4; ++nd)
      {
        int ni = i + di[nd][0];
        int nj = j + di[nd][1];
        if (ni < 0 || ni >= m || nj < 0 || nj >= n)
        {
          continue;
        }
        int nt = t + (nd != dr ? 1 : 0);
        if (nt > k)
        {
          continue;
        }
        int nc = c + grid[ni][nj];
        if (nc < d[ni][nj][nd][nt])
        {
          d[ni][nj][nd][nt] = nc;
          q.add(new int[] {nc, ni, nj, nd, nt});
        }
      }
    }
    int an = inf;
    for (int x = 0; x < 4; ++x)
    {
      for (int t = 0; t <= k; ++t)
      {
        an = Math.min(an, d[m - 1][n - 1][x][t]);
      }
    }
    return an == inf ? -1 : an;
  }
}