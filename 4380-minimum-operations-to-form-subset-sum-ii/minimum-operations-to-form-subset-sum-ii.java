class Solution {
    public int minOperations(int[] nums, int sum) {
        int INF = Integer.MAX_VALUE / 2;
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int num : nums) {
            Map<Integer, Integer> options = new HashMap<>();
            for (int k = 0; ; k++) {
                int base = num >> k;
                if (base == 0) break;
                for (int m = 0; ; m++) {
                    long value = (long) base << m;
                    if (value > sum) break;
                    int v = (int) value;
                    int cost = k + m;
                    Integer cur = options.get(v);
                    if (cur == null || cost < cur) options.put(v, cost);
                }
            }
            int[] prev = dp.clone();
            for (Map.Entry<Integer, Integer> e : options.entrySet()) {
                int v = e.getKey();
                int c = e.getValue();
                for (int s = sum; s >= v; s--) {
                    int cand = prev[s - v] + c;
                    if (cand < dp[s]) dp[s] = cand;
                }
            }
        }
        return dp[sum] >= INF ? -1 : dp[sum];
    }
}