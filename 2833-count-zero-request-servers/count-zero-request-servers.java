
import java.util.Arrays;

class Solution {

    public int[] countServers(
        int n,
        int[][] logs,
        int x,
        int[] queries
    ) {

        int m = queries.length;

        // Step 1: Sort logs by request time
        Arrays.sort(logs, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        // Step 2: Store queries with original indices
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Step 3: Sort queries by time
        Arrays.sort(sortedQueries, (a, b) ->
            Integer.compare(a[0], b[0])
        );

        // Step 4: Frequency of each server
        int[] freq = new int[n + 1];

        int activeServers = 0;

        int left = 0;
        int right = 0;

        int[] answer = new int[m];

        // Step 5: Process queries
        for (int i = 0; i < m; i++) {

            int query = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];

            int start = query - x;

            // Add logs within the right boundary
            while (right < logs.length &&
                   logs[right][1] <= query) {

                int server = logs[right][0];

                if (freq[server] == 0) {
                    activeServers++;
                }

                freq[server]++;
                right++;
            }

            // Remove logs outside the left boundary
            while (left < right &&
                   logs[left][1] < start) {

                int server = logs[left][0];

                freq[server]--;

                if (freq[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            // Servers without any requests
            answer[originalIndex] = n - activeServers;
        }

        return answer;
    }
}
