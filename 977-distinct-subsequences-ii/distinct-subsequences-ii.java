class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1_000_000_007L;
        long dp = 1;
        long[] last =  new long[26];
        for(char ch : s.toCharArray()){
            long newDp = (2 * dp % MOD - last[ch - 'a'] + MOD) % MOD;
            last[ch -'a'] =  dp;
            dp =  newDp;
        }
        return(int)((dp - 1 + MOD) % MOD);
    }
}