import java.util.*;

class Solution {
    List<Integer>[] tree;
    int[] depth, parent, heavy, head, pos;
    int curPos = 0;
    SegmentTree segTree;

    class SegmentTree {
        int n;
        int[] st;

        SegmentTree(int n) {
            this.n = n;
            st = new int[4 * n];
        }

        void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                st[node] = val;
                return;
            }
            int mid = (l + r) / 2;
            if (idx <= mid) update(2 * node, l, mid, idx, val);
            else update(2 * node + 1, mid + 1, r, idx, val);
            st[node] = st[2 * node] ^ st[2 * node + 1];
        }

        int query(int node, int l, int r, int ql, int qr) {
            if (qr < l || ql > r) return 0;
            if (ql <= l && r <= qr) return st[node];
            int mid = (l + r) / 2;
            return query(2 * node, l, mid, ql, qr) ^ query(2 * node + 1, mid + 1, r, ql, qr);
        }
    }

    int dfs1(int u, int p, int d) {
        depth[u] = d;
        parent[u] = p;
        int size = 1, maxSubSize = 0, heavyChild = -1;
        for (int v : tree[u]) {
            if (v != p) {
                int subSize = dfs1(v, u, d + 1);
                size += subSize;
                if (subSize > maxSubSize) {
                    maxSubSize = subSize;
                    heavyChild = v;
                }
            }
        }
        heavy[u] = heavyChild;
        return size;
    }

    void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = curPos++;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : tree[u]) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= segTree.query(1, 0, tree.length - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= segTree.query(1, 0, tree.length - 1, pos[u], pos[v]);
        return res;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }

        depth = new int[n];
        parent = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];

        dfs1(0, -1, 0);
        dfs2(0, 0);

        segTree = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            int mask = 1 << (s.charAt(i) - 'a');
            segTree.update(1, 0, n - 1, pos[i], mask);
        }

        List<Boolean> answer = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int mask = 1 << (c - 'a');
                segTree.update(1, 0, n - 1, pos[u], mask);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int mask = queryPath(u, v);
                // A path can form a palindrome if at most one bit is set (mask == 0 or a power of 2)
                boolean isPalindrome = (mask == 0) || ((mask & (mask - 1)) == 0);
                answer.add(isPalindrome);
            }
        }
        return answer;
    }
}