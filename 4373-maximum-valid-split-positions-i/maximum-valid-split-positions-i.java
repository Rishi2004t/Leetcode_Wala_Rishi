class Solution {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;
        int best = score(nums);
        for (int k = 0; k < n; k++) {
            int[] arr = new int[n - 1];
            int idx = 0;
            for (int j = 0; j < n; j++) {
                if (j != k) arr[idx++] = nums[j];
            }
            best = Math.max(best, score(arr));
        }
        return best;
    }

    private int score(int[] arr) {
        int m = arr.length;
        if (m < 2) return 0;
        int[] prefix = new int[m];
        int[] suffix = new int[m];
        prefix[0] = arr[0];
        for (int i = 1; i < m; i++) prefix[i] = gcd(prefix[i - 1], arr[i]);
        suffix[m - 1] = arr[m - 1];
        for (int i = m - 2; i >= 0; i--) suffix[i] = gcd(suffix[i + 1], arr[i]);
        int count = 0;
        for (int i = 0; i < m - 1; i++) {
            if (prefix[i] == suffix[i + 1]) count++;
        }
        return count;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}