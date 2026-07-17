class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxNum = 0;
        for (int x : nums) {
            if (x > maxNum) maxNum = x;
        }

        int[] count = new int[maxNum + 1];
        for (int x : nums) {
            count[x]++;
        }

        long[] gcdCount = new long[maxNum + 1];
        for (int i = maxNum; i >= 1; i--) {
            long multiplesCount = 0;
            for (int j = i; j <= maxNum; j += i) {
                multiplesCount += count[j];
            }

            gcdCount[i] = multiplesCount * (multiplesCount - 1) / 2;

            for (int j = 2 * i; j <= maxNum; j += i) {
                gcdCount[i] -= gcdCount[j];
            }
        }

        long[] prefixSum = new long[maxNum + 1];
        for (int i = 1; i <= maxNum; i++) {
            prefixSum[i] = prefixSum[i - 1] + gcdCount[i];
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = lowerBound(prefixSum, queries[i] + 1);
        }
        return result;
    }

    private int lowerBound(long[] arr, long target) {
        int low = 1, high = arr.length - 1;
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}