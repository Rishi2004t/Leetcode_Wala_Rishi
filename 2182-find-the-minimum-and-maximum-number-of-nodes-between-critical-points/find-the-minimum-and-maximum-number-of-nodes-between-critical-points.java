class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int last = -1;
        int prevCritical = -1;

        int minDist = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;

        int pos = 2; // curr ki position

        while (curr != null && curr.next != null) {

            boolean isMax =
                curr.val > prev.val &&
                curr.val > curr.next.val;

            boolean isMin =
                curr.val < prev.val &&
                curr.val < curr.next.val;

            if (isMax || isMin) {

                if (first == -1) {
                    first = pos;
                } else {
                    minDist = Math.min(
                        minDist,
                        pos - prevCritical
                    );
                }

                prevCritical = pos;
                last = pos;
            }

            prev = curr;
            curr = curr.next;
            pos++;
        }

        if (first == -1 || first == last) {
            return new int[]{-1, -1};
        }

        return new int[]{
            minDist,
            last - first
        };
    }
}