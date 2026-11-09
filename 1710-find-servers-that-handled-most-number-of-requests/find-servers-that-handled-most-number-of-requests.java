
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        // Stores available server IDs in sorted order.
        TreeSet<Integer> available = new TreeSet<>();

        // Stores busy servers as [finishTime, serverIndex].
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int[] count = new int[k];

        // Initially, every server is available.
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            long currentTime = arrival[i];

            // Release all servers whose requests have finished.
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= currentTime) {

                long[] finished = busy.poll();

                int serverId = (int) finished[1];

                available.add(serverId);
            }

            // If all servers are busy, drop this request.
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server for this request.
            int preferred = i % k;

            // Find the next available server at or after preferred.
            Integer server = available.ceiling(preferred);

            // If none exists, wrap around to the smallest ID.
            if (server == null) {
                server = available.first();
            }

            // Assign the request to this server.
            available.remove(server);

            count[server]++;

            // Calculate when this server becomes free.
            long finishTime = currentTime + load[i];

            busy.offer(new long[]{finishTime, server});
        }

        // Find the maximum number of handled requests.
        int maxRequests = 0;

        for (int c : count) {
            maxRequests = Math.max(maxRequests, c);
        }

        // Collect all servers having the maximum count.
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}
