class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long left = 1;
        long right = (long)1e18;
        long ans = right;
        while(left <= right){
        long mid = (left + right) / 2;
        if (canReduce(mid, mountainHeight, workerTimes)){
            ans = mid;
            right = mid - 1;
        }else{
            left = mid + 1;
        }
    }
    return ans;
}
boolean canReduce(long time, int height, int[] workers){
    long total = 0;
    for(int t  : workers){
        long l = 0;
         long r = (long)Math.sqrt(2 * time / t) + 2;
         while(l <= r){
            long m = (l +r ) / 2;
            long needed = t * m * ( m + 1) / 2;
            if(needed <= time){
                l = m + 1;
            }else{
                r = m - 1;
            }
         }
         total += r;
         if(total >= height) return true;
    }
    return false;
}
}