class Solution {
    public long maxTotal(int[] a, String s) {
        
        long neg  =  -(long)1e18;
        long []dp = {neg , neg}, ndp = new long[2];
        dp[s.charAt(0) -'0'] = 0;
        for(int i = 1; i < a.length; i++){
            ndp[0] = ndp[1] = neg;
            for(int  p = 0; p < 2; p++){
                if(dp[p] == neg)continue;
                for(int c  = 0; c <= (s.charAt(i) == '1' ? 1 : 0);c++){
                    boolean cover = (s.charAt(i - 1) == '1' && p == 1) || 
                        (s.charAt(i) == '1'  &&  c == 0);
                    ndp[c] = Math.max(ndp[c], dp[p] + (cover ? a[i - 1] : 0));
                    
                }
            }
            long[] t = dp;
            dp = ndp;
            ndp = t;
            
        }
        return Math.max(dp[0],dp[1] + (s.charAt(a.length - 1) == '1' ? a[a.length - 1] : 0)
                       );
        }
    }
