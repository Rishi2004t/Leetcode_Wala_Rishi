class Solution {

    class Pair {
        long cnt;
        long wave;

        Pair(long c, long w) {
            cnt = c;
            wave = w;
        }
    }

    String s;
    Pair[][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        s = String.valueOf(n);

        memo = new Pair[s.length()][11][4][2];

        Pair ans = dfs(0, 10, 3, 0, true);

        return ans.wave;
    }

    private Pair dfs(int pos,
                     int lastDigit,
                     int prevDir,
                     int started,
                     boolean tight) {

        if (pos == s.length()) {
            return new Pair(1, 0);
        }

        if (!tight &&
            memo[pos][lastDigit][prevDir][started] != null) {
            return memo[pos][lastDigit][prevDir][started];
        }

        int limit = tight ? s.charAt(pos) - '0' : 9;

        long totalCnt = 0;
        long totalWave = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nt = tight && (d == limit);

            if (started == 0 && d == 0) {

                Pair nxt = dfs(
                    pos + 1,
                    10,
                    3,
                    0,
                    nt
                );

                totalCnt += nxt.cnt;
                totalWave += nxt.wave;
            }
            else {

                if (started == 0) {

                    Pair nxt = dfs(
                        pos + 1,
                        d,
                        3,
                        1,
                        nt
                    );

                    totalCnt += nxt.cnt;
                    totalWave += nxt.wave;
                }
                else {

                    int dir;

                    if (d > lastDigit)
                        dir = 0;      
                    else if (d < lastDigit)
                        dir = 1;     
                    else
                        dir = 2;      

                    Pair nxt = dfs(
                        pos + 1,
                        d,
                        dir,
                        1,
                        nt
                    );

                    long add = 0;

                    if (prevDir != 3 &&
                        dir != 2 &&
                        prevDir != 2 &&
                        dir != prevDir) {

                        add = nxt.cnt;
                    }

                    totalCnt += nxt.cnt;
                    totalWave += nxt.wave + add;
                }
            }
        }

        Pair res = new Pair(totalCnt, totalWave);

        if (!tight) {
            memo[pos][lastDigit][prevDir][started] = res;
        }

        return res;
    }
}