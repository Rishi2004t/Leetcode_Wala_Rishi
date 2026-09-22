class Solution {
    static class Node {
        int prod;
        int[] remain;
        
        Node(int k) {
            this.prod = 1;
            this.remain = new int[k];
        }
    }

    static class SegmentTree {
        int n, k;
        Node[] tree;
        int[] nums;

        SegmentTree(int[] nums, int k) {
            this.nums = nums;
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];
            build(1, 0, n - 1);
        }

        private Node merge(Node left, Node right) {
            Node res = new Node(k);
            res.prod = (left.prod * right.prod) % k;
            for (int i = 0; i < k; i++) {
                res.remain[i] = left.remain[i];
            }
            for (int i = 0; i < k; i++) {
                int nxt = (left.prod * i) % k;
                res.remain[nxt] += right.remain[i];
            }
            return res;
        }

        private void build(int node, int l, int r) {
            tree[node] = new Node(k);
            if (l == r) {
                tree[node].prod = nums[l] % k;
                tree[node].remain[nums[l] % k] = 1;
                return;
            }
            int mid = (l + r) / 2;
            build(2 * node, l, mid);
            build(2 * node + 1, mid + 1, r);
            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }

        public void update(int idx, int val, int node, int l, int r) {
            if (l == r) {
                nums[idx] = val % k;
                tree[node] = new Node(k);
                tree[node].prod = nums[idx];
                tree[node].remain[nums[idx]] = 1;
                return;
            }
            int mid = (l + r) / 2;
            if (idx <= mid) {
                update(idx, val, 2 * node, l, mid);
            } else {
                update(idx, val, 2 * node + 1, mid + 1, r);
            }
            tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
        }

        public Node query(int ql, int qr, int node, int l, int r) {
            if (ql <= l && r <= qr) {
                return tree[node];
            }
            int mid = (l + r) / 2;
            if (qr <= mid) {
                return query(ql, qr, 2 * node, l, mid);
            }
            if (ql > mid) {
                return query(ql, qr, 2 * node + 1, mid + 1, r);
            }
            return merge(query(ql, qr, 2 * node, l, mid), query(ql, qr, 2 * node + 1, mid + 1, r));
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int[] modNums = new int[n];
        for (int i = 0; i < n; i++) {
            modNums[i] = nums[i] % k;
        }

        SegmentTree tree = new SegmentTree(modNums, k);
        int m = queries.length;
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            int idx = queries[i][0];
            int val = queries[i][1] % k;
            int start = queries[i][2];
            int x = queries[i][3];

            tree.update(idx, val, 1, 0, n - 1);
            Node res = tree.query(start, n - 1, 1, 0, n - 1);
            ans[i] = res.remain[x];
        }

        return ans;
    }
}
