class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diff = new long[n];

        long operations = (long) k1 + k2;
        long max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }

        // No operations needed
        if (operations == 0) {
            return calculateSum(diff);
        }

        // Maximum possible reduction
        long totalDiff = 0;
        for (long d : diff) {
            totalDiff += d;
        }

        if (operations >= totalDiff) {
            return 0;
        }

        // Count frequencies of differences
        long[] count = new long[(int) max + 1];

        for (long d : diff) {
            count[(int) d]++;
        }

        // Reduce largest differences first
        for (int d = (int) max; d > 0 && operations > 0; d--) {
            long use = Math.min(operations, count[d]);

            count[d] -= use;
            count[d - 1] += use;
            operations -= use;
        }

        long ans = 0;

        for (int d = 0; d < count.length; d++) {
            ans += count[d] * d * d;
        }

        return ans;
    }

    private long calculateSum(long[] diff) {
        long ans = 0;

        for (long d : diff) {
            ans += d * d;
        }

        return ans;
    }
}