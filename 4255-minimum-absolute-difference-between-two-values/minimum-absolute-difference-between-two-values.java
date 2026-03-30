class Solution {
    public int minAbsoluteDifference(int[] nums) {
        int one = -1, two = -1, ans = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
        if(nums[i] == 1)one = i;
        if(nums[i] == 2)two = i;
        if(one != -1 && two != -1)
        ans = Math.min(ans, Math.abs(one - two));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}