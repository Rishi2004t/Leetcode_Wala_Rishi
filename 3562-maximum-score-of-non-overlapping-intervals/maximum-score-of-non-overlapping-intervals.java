class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        // stable sort by end point ascending
        Arrays.sort(order, (a, b) -> Integer.compare(intervals.get(a).get(1), intervals.get(b).get(1)));

        long[] R = new long[n];
        for (int i = 0; i < n; i++) R[i] = intervals.get(order[i]).get(1);

        long[][] score = new long[n + 1][5];
        int[][][] picks = new int[n + 1][5][];
        for (int k = 0; k <= 4; k++) picks[0][k] = new int[0];

        for (int i = 1; i <= n; i++) {
            int orig = order[i - 1];
            long l = intervals.get(orig).get(0);
            long w = intervals.get(orig).get(2);

            int p = countLess(R, i - 1, l);

            for (int k = 0; k <= 4; k++) {
                long bestScore = score[i - 1][k];
                int[] bestPick = picks[i - 1][k];

                if (k >= 1) {
                    long candScore = score[p][k - 1] + w;
                    int[] candPick = insertSorted(picks[p][k - 1], orig);
                    if (candScore > bestScore ||
                        (candScore == bestScore && lexSmaller(candPick, bestPick))) {
                        bestScore = candScore;
                        bestPick = candPick;
                    }
                }
                score[i][k] = bestScore;
                picks[i][k] = bestPick;
            }
        }
        return picks[n][4];
    }

    private int countLess(long[] R, int len, long l) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (R[mid] < l) lo = mid + 1; else hi = mid;
        }
        return lo;
    }

    private int[] insertSorted(int[] arr, int val) {
        int[] res = new int[arr.length + 1];
        int i = 0;
        while (i < arr.length && arr[i] < val) { res[i] = arr[i]; i++; }
        res[i] = val;
        for (int j = i; j < arr.length; j++) res[j + 1] = arr[j];
        return res;
    }

    private boolean lexSmaller(int[] a, int[] b) {
        int len = Math.min(a.length, a.length);
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) return a[i] < b[i];
        }
        return a.length < b.length;
    }
}