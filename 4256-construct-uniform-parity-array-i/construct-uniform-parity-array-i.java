class Solution {
    public boolean uniformArray(int[] nums1) {
        int odd = 0;
        int even = 0;

        for (int x : nums1) {
            if ((x & 1) == 0) {
                even++;
            } else {
                odd++;
            }
        }

        if (nums1.length == 1) {
            return true;
        }

        return true;
    }
}