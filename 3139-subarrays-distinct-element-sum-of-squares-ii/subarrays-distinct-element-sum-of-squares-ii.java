
import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        SegmentTree st = new SegmentTree(n);
        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            int left = last[x] + 1;
            st.update(left, i, 1);

            ans = (ans + st.querySquareSum()) % MOD;
            last[x] = i;
        }

        return (int) ans;
    }

    static class SegmentTree {
        int n;
        long[] sum, squareSum, lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            squareSum = new long[4 * n];
            lazy = new long[4 * n];
        }

        void update(int l, int r, int val) {
            update(1, 0, n - 1, l, r, val);
        }

        void update(int node, int start, int end,
                    int l, int r, int val) {
            if (r < start || end < l) return;

            if (l <= start && end <= r) {
                apply(node, start, end, val);
                return;
            }

            push(node, start, end);
            int mid = (start + end) / 2;

            update(node * 2, start, mid, l, r, val);
            update(node * 2 + 1, mid + 1, end, l, r, val);

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            squareSum[node] =
                (squareSum[node * 2] + squareSum[node * 2 + 1]) % MOD;
        }

        void apply(int node, int start, int end, long val) {
            long len = end - start + 1L;

            squareSum[node] =
                (squareSum[node] + 2L * val % MOD * sum[node] % MOD
                 + len % MOD * val % MOD * val % MOD) % MOD;

            sum[node] = (sum[node] + len * val) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        void push(int node, int start, int end) {
            if (lazy[node] == 0 || start == end) return;

            int mid = (start + end) / 2;
            apply(node * 2, start, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, end, lazy[node]);

            lazy[node] = 0;
        }

        long querySquareSum() {
            return squareSum[1];
        }
    }
}
