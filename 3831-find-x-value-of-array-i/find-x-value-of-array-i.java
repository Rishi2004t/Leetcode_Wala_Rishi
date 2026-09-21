class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans =  new long[k];
        long[] dp  =  new long[k];
        for(int num :  nums){
            int val  = num % k;
            long[]  ndp = new long[k];
             ndp[val]++;
             for(int r = 0; r < k ; r++){
                int nr = (r * val) % k;
                ndp[nr] +=  dp[r];

             }
             for(int r = 0; r < k; r++){
                ans[r] +=  ndp[r];
             }
             dp =  ndp;

        }
        return ans;
    }
}