class Solution {
    public int minOperations(String s) {
        boolean isSorted = true;
        char[] cs = s.toCharArray();
        int n = cs.length;
        char mn = cs[0], mx = cs[0];
        
        for (int i = 1; i < n; ++i) {
            mn = (char) Math.min(mn, cs[i]);
            mx = (char) Math.max(mx, cs[i]);
            if (cs[i] < cs[i - 1]) {
                isSorted = false;
            }
        }
        
        // If already sorted
        if (isSorted) {
            return 0;
        }
        
        // Length 2 cannot be sorted with proper substrings if out of order
        if (n == 2) {
            return -1;
        }
        
        // Can be sorted in 1 operation if the min is at the start or max is at the end
        if (cs[0] == mn || cs[n - 1] == mx) {
            return 1;
        }
        
        // Can be sorted in 2 operations if min or max is in the interior
        for (int i = 1; i < n - 1; ++i) {
            if (cs[i] == mn || cs[i] == mx) {
                return 2;
            }
        }
        
        // Otherwise, 3 operations are sufficient
        return 3;
    }
}