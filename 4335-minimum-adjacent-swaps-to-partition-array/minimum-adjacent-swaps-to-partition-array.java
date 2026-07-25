class Solution {
    public int minAdjacentSwaps(int[] nums, int a, int b) {
        long swaps = 0;
        long count1 = 0;
        long  count2 = 0;
        long count3 = 0;
        int MOD = 1_000_000_007;
        for(int x :  nums){
            if(x <  a){
                swaps = (swaps +  count2 + count3) % MOD;
                count1++;
            }else if (x >= a && x <= b){
                swaps = (swaps + count3) % MOD;
                count2++;
            }else{
                count3++;
            }
                
        }
        return (int) swaps;
    }
}