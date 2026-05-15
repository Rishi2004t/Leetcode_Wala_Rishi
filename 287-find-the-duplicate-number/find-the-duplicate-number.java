class Solution {
    public int findDuplicate(int[] nums) {
        int low  = 0 , high =  nums.length - 1;
        while(low < high){
            int mid= (low  +  high) / 2;
            int count = 0;
            for(int x :  nums){
                if(x <=  mid) count++;
            }           
            if(count > mid){
                high = mid;
            }else{
                low  =  mid + 1;
            }
        }
        return low;
    }
}