class Solution {
    public long maximumSum(int[]  a, int m, int l, int r) {
     int n = a.length;
        long[] p = new long[n + 1];
        for(int i = 0; i < n; i++)
            p[i + 1] =  p[i] + a[i];
        long neg = Long.MIN_VALUE / 4;
        long[][] dp =  new long [m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            Arrays.fill(dp[i], neg);
            Arrays.fill(dp[0], 0);
            long ans = neg;
            for(int k = 1; k <= m ;k++){
                ArrayDeque<Integer> q = new ArrayDeque<>();
                for(int idx = 1; idx <=n ;idx++){
                    dp[k][idx] = dp[k][idx - 1];
                    int j = idx - l;
                    if(j >= 0){
                        while(!q.isEmpty() && dp[k - 1][q.peekLast()] - p[q.peekLast()] <= dp[k - 1][j]- p[j]){
                            q.pollLast();
                            
                        }
                        q.offerLast(j);
                    }
                    while(!q.isEmpty() && q.peekFirst() < idx - r)q.pollFirst();
                    if(!q.isEmpty()){
                        j = q.peekFirst();
                        dp[k][idx] = Math.max(dp[k][idx], p[idx] + dp[k - 1][j] - p[j]);
                    }
                    ans = Math.max(ans, dp[k][idx]);
                }
            }
            return ans;
        
        }
    }
