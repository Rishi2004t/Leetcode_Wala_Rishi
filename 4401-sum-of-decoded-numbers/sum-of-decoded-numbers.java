class Solution {
    public int sumDecoded(long[] nums) {
        long MOD  = 1_000_000_007;
        long totalSum = 0;
        for(long num :  nums){
            long width =  num % 10;
            long d =  num /  10;
            String s =  String.valueOf(d);
            long x1 =  Long.parseLong(s.substring(0,  (int)width));
            long y1 =  Long.parseLong(s.substring((int)width));
            long decodeVal =  power(x1, y1, MOD);
            totalSum=  (totalSum +  decodeVal ) % MOD;
        }
        return (int ) totalSum;
    }
    private long power(long base, long exp, long mod){
        long res = 1;
         base %=  mod;
        while (exp > 0) {
            if((exp & 1) == 1){
                res = (res * base) % mod;
            }
            base = (base *  base) % mod;
                exp >>=  1;
            }
            return res;
        
    }
}