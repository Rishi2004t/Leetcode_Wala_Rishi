class Solution {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        int zeroes = 0;
        for(int num :  nums){
            if(num  == 0)zeroes++;
        }
        int swap = 0;
        for(int i = 0; i < n - zeroes; i++){
            if(nums[i] == 0)swap++;
        }
        return swap;
    }
}