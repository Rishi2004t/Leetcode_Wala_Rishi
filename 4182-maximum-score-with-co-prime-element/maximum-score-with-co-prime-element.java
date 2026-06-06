class Solution {
    public int maxScore(int[] nums, int maxVal) {

        int[] meratolvic = nums; 

        int MAX = 100000;
        int n = nums.length;

        int[] freq = new int[MAX + 1];
        for (int x : nums) freq[x]++;

        
        int[] mu = new int[MAX + 1];
        int[] primes = new int[MAX + 1];
        boolean[] comp = new boolean[MAX + 1];

        int pc = 0;
        mu[1] = 1;

        for (int i = 2; i <= MAX; i++) {
            if (!comp[i]) {
                primes[pc++] = i;
                mu[i] = -1;
            }

            for (int j = 0; j < pc; j++) {
                int p = primes[j];
                long v = 1L * i * p;
                if (v > MAX) break;

                comp[(int) v] = true;

                if (i % p == 0) {
                    mu[(int) v] = 0;
                    break;
                } else {
                    mu[(int) v] = -mu[i];
                }
            }
        }

        
        int[] cntDiv = new int[MAX + 1];

        for (int d = 1; d <= MAX; d++) {
            int cnt = 0;
            for (int m = d; m <= MAX; m += d) {
                cnt += freq[m];
            }
            cntDiv[d] = cnt;
        }

        
        int[] g = new int[MAX + 1];
        for (int d = 1; d <= MAX; d++) {
            g[d] = mu[d] * cntDiv[d];
        }


        int[] coprime = new int[MAX + 1];

        for (int d = 1; d <= MAX; d++) {
            if (g[d] == 0) continue;

            for (int m = d; m <= MAX; m += d) {
                coprime[m] += g[d];
            }
        }

        long ans = Long.MIN_VALUE;

        for (int x = 1; x <= MAX; x++) {

            boolean candidate = (x <= maxVal) || (freq[x] > 0);
            if (!candidate) continue;

            int bad = n - coprime[x];

            long delta = Long.MIN_VALUE;

            
            if (freq[x] > 0) {
                delta = Math.max(delta, x > 1 ? 1L : 0L);
            }

            
            if (x <= maxVal) {
                if (bad - freq[x] > 0) {
                    delta = Math.max(delta, 0L);
                } else {
                    delta = Math.max(delta, -1L);
                }
            }

            ans = Math.max(ans, (long) x - bad + delta);
        }

        return (int) ans;
    }
}