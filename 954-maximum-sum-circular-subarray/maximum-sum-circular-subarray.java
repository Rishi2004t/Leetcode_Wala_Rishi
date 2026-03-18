class Solution {
    public int maxSubarraySumCircular(int[] nums) {
     int total = 0;
     int max = nums[0];
     int currMax = 0;
     int min =  nums[0];
     int currMin = 0;
     for(int num :  nums){
        currMax = Math.max(num, currMax +  num);
        max = Math.max(max,  currMax);

        currMin = Math.min( num, currMin +  num);
        min = Math.min(min,  currMin);
        total +=  num;
     }   
     if(max < 0){
        return max;
     }
     return Math.max(max , total - min);
    }
}