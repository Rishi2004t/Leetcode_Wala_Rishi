class Solution {
    public int minOperations(int[] nums, int sum) {
        int[] dp =  new int [sum + 1];
        Arrays.fill(dp, 1000000);
        dp[0] =  0;
         for(int x : nums){
             Map<Integer, Integer>  map =  new HashMap<>();
             for(int a = 0; a <=  15; a++){
                 long curr = (long) x << a;
                 for(int b =  0; curr > 0 && b <=  20; b++){
                     int val = (int)  curr;
                     int ops = a + b;
                     map.put(val, Math.min(map.getOrDefault(val, 1000000), ops));
                     curr /= 2;
                 }
             }
             int[]  nextDp =  dp.clone();
             for(int j = 0; j <=  sum; j++) {
                 for(var entry :  map.entrySet()){
                     int val =  entry.getKey(), ops =  entry.getValue();
                     if(j +  val <= sum ){
                          nextDp[j +  val] = Math.min(nextDp[j +  val], dp[j] +  ops);
                     }
                 }
             }
             dp =  nextDp;
         }
        return dp[sum] >= 1000000 ? -1 :  dp[sum];
    }
}