
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        // Available servers: [weight, serverIndex]
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: [freeTime, serverIndex]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Long.compare(a[0], b[0]);
                }
                return Long.compare(a[1], b[1]);
            }
        );

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Initially, all servers are free.
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int i = 0; i < m; i++) {

            // Task i arrives at second i.
            time = Math.max(time, i);

            // Move all servers that have finished to free queue.
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();

                int index = (int) server[1];

                free.offer(new int[]{servers[index], index});
            }

            // If no server is free, wait for the earliest one.
            if (free.isEmpty()) {
                time = busy.peek()[0];

                // Release every server that becomes free at this time.
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();

                    int index = (int) server[1];

                    free.offer(new int[]{servers[index], index});
                }
            }

            // Select the best available server.
            int[] server = free.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            // This server becomes free after processing the task.
            long finishTime = time + tasks[i];

            busy.offer(new long[]{finishTime, index});
        }

        return ans;
    }
}
