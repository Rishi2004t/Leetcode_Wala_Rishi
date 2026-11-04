
import java.util.*;

class Solution {

    private static final long INF = Long.MAX_VALUE / 4;

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate cost of every window of size x
        long[] cost = calculateCosts(nums, x);

        // Step 2: DP
        long[][] dp = new long[n + 1][k + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j <= k; j++) {

                // Choice 1: Skip current element
                dp[i][j] = dp[i - 1][j];

                // Choice 2: Select window ending at i-1
                if (j > 0 && i >= x &&
                    dp[i - x][j - 1] != INF) {

                    long take =
                        dp[i - x][j - 1] + cost[i - x];

                    dp[i][j] = Math.min(dp[i][j], take);
                }
            }
        }

        return dp[n][k];
    }

    // Calculate minimum equalization cost
    // for every window of size x.
    private long[] calculateCosts(int[] nums, int x) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        WindowMedian window = new WindowMedian();

        // First window
        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        // Sliding window
        for (int i = x; i < n; i++) {

            window.remove(nums[i - x]);
            window.add(nums[i]);

            cost[i - x + 1] = window.getCost();
        }

        return cost;
    }

    // Maintains the median and sum of absolute differences.
    static class WindowMedian {

        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        int lowSize = 0;
        int highSize = 0;

        long lowSum = 0;
        long highSum = 0;

        void add(int num) {

            if (low.isEmpty() || num <= low.lastKey()) {
                addLow(num);
            } else {
                addHigh(num);
            }

            rebalance();
        }

        void remove(int num) {

            if (low.containsKey(num)) {
                removeLow(num);
            } else {
                removeHigh(num);
            }

            rebalance();
        }

        void rebalance() {

            // LOW must contain ceil(totalSize / 2) elements.
            int target = (lowSize + highSize + 1) / 2;

            while (lowSize > target) {

                int num = low.lastKey();

                removeLow(num);
                addHigh(num);
            }

            while (lowSize < target) {

                int num = high.firstKey();

                removeHigh(num);
                addLow(num);
            }
        }

        void addLow(int num) {

            low.put(num, low.getOrDefault(num, 0) + 1);

            lowSize++;
            lowSum += num;
        }

        void addHigh(int num) {

            high.put(num, high.getOrDefault(num, 0) + 1);

            highSize++;
            highSum += num;
        }

        void removeLow(int num) {

            removeOne(low, num);

            lowSize--;
            lowSum -= num;
        }

        void removeHigh(int num) {

            removeOne(high, num);

            highSize--;
            highSum -= num;
        }

        void removeOne(TreeMap<Integer, Integer> map, int num) {

            int count = map.get(num);

            if (count == 1) {
                map.remove(num);
            } else {
                map.put(num, count - 1);
            }
        }

        long getCost() {

            long median = low.lastKey();

            long leftCost =
                median * lowSize - lowSum;

            long rightCost =
                highSum - median * highSize;

            return leftCost + rightCost;
        }
    }
}
