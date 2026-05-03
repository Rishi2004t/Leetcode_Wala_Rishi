class Solution {
    public int[] countOppositeParity(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int totalEven = 0, totalOdd = 0;
        for(int num :  nums){
            if(num % 2 == 0)totalEven++;
            else totalOdd++;
        }
        for(int i = 0; i < n; i++){
            if(nums[i] % 2 == 0)totalEven--;
            else totalOdd--;
            if(nums[i] % 2 == 0){
                ans[i] = totalOdd;
            }else{
                ans[i] = totalEven;
            }
        }
        return ans;
    }
}