class Solution {
    public int minMirrorPairDistance(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for(int i =  nums.length - 1;i >= 0; i--){
            int rev =  reverse(nums[i]);
            if(map.containsKey(rev)){
                ans = Math.min(ans, map.get(rev) - i);
            }
            map.put(nums[i], i);
        }
        return ans == Integer.MAX_VALUE ? -1 :  ans;
    }
    private int reverse(int x){
        int rev = 0;
        while (x > 0){
            rev = rev * 10 + ( x % 10);
            x /= 10;
        }
        return rev;
    }
}