class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        int odds = 0, evens = 0;
        boolean hasOdd = false;

        for (int x : nums1) {
            if (x % 2 == 0) evens++;
            else { odds++; hasOdd = true; }
            minVal = Math.min(minVal, x);
        }

        if (evens == nums1.length) return true;

        if (odds == nums1.length) return true;

        if (minVal % 2 != 0) return true;

        return false;
    }
}