class Solution {
    public long maximumValue(int n, int s, int m) {
        if(n == 1){
            return s;
        }
            int upCount = n / 2;
            long ans1 = (long) s+ m;
            if(upCount >  1){
                ans1 += (long) (upCount - 1) * (m - 1);
                
            }
            int peakCount = (n - 1) / 2;
            long ans2 = s;
            if(peakCount > 0){
                ans2 = (long) s + m - 1;
                if(peakCount > 1) {
                    ans2 += (long) (peakCount - 1) * (m  -1);
                }
            }
            return Math.max(ans1, ans2);
    }
}