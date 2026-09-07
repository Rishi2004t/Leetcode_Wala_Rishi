class Solution {
    public int countGoodRotations(int[] nums) {
        int n =  nums.length;
        int m =  n / 2;
        long total = 0;
         for(int  x :  nums) total +=  x;
        long window  = 0;
         for(int  i = 0; i <  m ; i++){
             window  +=   nums[i];
         }
        int ans = 0;
        for(int start = 0; start <  n; start++){
            if(2 * window >  total)ans++;
            window -=   nums[start];
            window += nums[(start +  m) % n];  
        }
        return ans;
    }
}