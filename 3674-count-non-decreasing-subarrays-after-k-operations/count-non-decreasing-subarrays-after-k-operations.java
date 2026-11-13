
class Solution {

    // Calculates the minimum operations needed for nums[l...r].
    private long getCost(
        int l,
        int r,
        int[] nums,
        long[] prefix,
        int[][] jump,
        long[][] blockSum,
        int log
    ) {

        int cur = l;
        long sumMax = 0;

        // Include all complete blocks using binary lifting.
        for (int p = log - 1; p >= 0; p--) {

            int next = jump[p][cur];

            if (next <= r + 1) {
                sumMax += blockSum[p][cur];
                cur = next;
            }
        }

        // Include the final incomplete block.
        if (cur <= r) {
            sumMax += (long) nums[cur] * (r - cur + 1);
        }

        // Sum of original elements in nums[l...r].
        long originalSum = prefix[r + 1] - prefix[l];

        return sumMax - originalSum;
    }

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        // Number of binary lifting levels.
        int log = 1;
        while ((1 << log) <= n) {
            log++;
        }

        // Step 1: Build next strictly greater indices.
        int[] nextGreater = new int[n];

        int[] stack = new int[n];
        int top = 0;

        for (int i = n - 1; i >= 0; i--) {

            while (top > 0 &&
                   nums[stack[top - 1]] <= nums[i]) {
                top--;
            }

            nextGreater[i] =
                (top == 0) ? n : stack[top - 1];

            stack[top++] = i;
        }

        // Step 2: Build binary lifting tables.
        // n is the sentinel representing the end.
        int[][] jump = new int[log][n + 1];
        long[][] blockSum = new long[log][n + 1];

        for (int i = 0; i < n; i++) {

            jump[0][i] = nextGreater[i];

            blockSum[0][i] =
                (long) nums[i] * (nextGreater[i] - i);
        }

        // Sentinel always jumps to itself.
        for (int p = 0; p < log; p++) {
            jump[p][n] = n;
        }

        for (int p = 1; p < log; p++) {

            for (int i = 0; i < n; i++) {

                int mid = jump[p - 1][i];

                jump[p][i] = jump[p - 1][mid];

                blockSum[p][i] =
                    blockSum[p - 1][i] +
                    blockSum[p - 1][mid];
            }
        }

        // Step 3: Build prefix sums.
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Step 4: Sliding window.
        int left = 0;
        long answer = 0;

        for (int right = 0; right < n; right++) {

            while (left <= right &&
                   getCost(
                       left, right, nums,
                       prefix, jump, blockSum, log
                   ) > k) {

                left++;
            }

            // Count all valid subarrays ending at right.
            answer += right - left + 1;
        }

        return answer;
    }
}
