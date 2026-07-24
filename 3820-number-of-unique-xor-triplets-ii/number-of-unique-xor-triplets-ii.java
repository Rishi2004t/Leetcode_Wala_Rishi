class Solution {

    public int uniqueXorTriplets(int[] nums) {

        boolean[] pair = new boolean[2048];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                pair[nums[i] ^ nums[j]] = true;
            }
        }
        boolean[] ans = new boolean[2048];
        for (int x = 0; x < 2048; x++) {
            if (!pair[x]) continue;
            for (int num : nums) {
                ans[x ^ num] = true;
            }
        }

        int count = 0;
        for (boolean x : ans) {
            if (x) count++;
        }

        return count;
    }
}