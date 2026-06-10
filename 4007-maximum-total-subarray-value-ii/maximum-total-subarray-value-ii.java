
class Solution {

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        SegTree st = new SegTree(nums);

        PriorityQueue<Node> pq =
                new PriorityQueue<>((a, b) ->
                        Long.compare(b.val, a.val));

        pq.offer(new Node(
                0,
                n - 1,
                st.getValue(0, n - 1)
        ));

        HashSet<Long> vis = new HashSet<>();
        vis.add(encode(0, n - 1));

        long ans = 0;

        while (k > 0 && !pq.isEmpty()) {

            Node cur = pq.poll();

            ans += cur.val;
            k--;

            int l = cur.l;
            int r = cur.r;

            if (l + 1 <= r) {
                long key = encode(l + 1, r);

                if (!vis.contains(key)) {
                    vis.add(key);

                    pq.offer(new Node(
                            l + 1,
                            r,
                            st.getValue(l + 1, r)
                    ));
                }
            }

            if (l <= r - 1) {
                long key = encode(l, r - 1);

                if (!vis.contains(key)) {
                    vis.add(key);

                    pq.offer(new Node(
                            l,
                            r - 1,
                            st.getValue(l, r - 1)
                    ));
                }
            }
        }

        return ans;
    }

    long encode(int l, int r) {
        return (((long) l) << 32) | r;
    }

    static class Node {
        int l, r;
        long val;

        Node(int l, int r, long val) {
            this.l = l;
            this.r = r;
            this.val = val;
        }
    }

    static class SegTree {

        int[] mn;
        int[] mx;
        int n;

        SegTree(int[] nums) {
            n = nums.length;

            mn = new int[4 * n];
            mx = new int[4 * n];

            build(1, 0, n - 1, nums);
        }

        void build(int node, int l, int r, int[] a) {

            if (l == r) {
                mn[node] = mx[node] = a[l];
                return;
            }

            int mid = (l + r) / 2;

            build(node * 2, l, mid, a);
            build(node * 2 + 1, mid + 1, r, a);

            mn[node] = Math.min(mn[node * 2], mn[node * 2 + 1]);
            mx[node] = Math.max(mx[node * 2], mx[node * 2 + 1]);
        }

        long getValue(int l, int r) {

            int[] q = query(1, 0, n - 1, l, r);

            return (long) q[1] - q[0];
        }

        int[] query(int node,
                    int start,
                    int end,
                    int l,
                    int r) {

            if (r < start || end < l) {
                return new int[]{
                        Integer.MAX_VALUE,
                        Integer.MIN_VALUE
                };
            }

            if (l <= start && end <= r) {
                return new int[]{
                        mn[node],
                        mx[node]
                };
            }

            int mid = (start + end) / 2;

            int[] left =
                    query(node * 2, start, mid, l, r);

            int[] right =
                    query(node * 2 + 1, mid + 1, end, l, r);

            return new int[]{
                    Math.min(left[0], right[0]),
                    Math.max(left[1], right[1])
            };
        }
    }
}