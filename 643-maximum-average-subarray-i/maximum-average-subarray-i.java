class Solution {
    public double findMaxAverage(int[] nums, int w) {
     int curr = 0;
     for(int i = 0; i < w; i++){
        curr  += nums[i];
     }
     int max = curr;
     for(int i = w; i <  nums.length ; i++){
        curr = curr - nums[i - w] + nums[i ];
            if(curr >  max){
                max = curr;
            }
             
        }
     return (double) max / w; 

    }
}