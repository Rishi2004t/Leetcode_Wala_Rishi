class Solution {

    List<Integer>[] graph;
    boolean[] visited;

    int nodes;
    int edges;

    public int countCompleteComponents(int n, int[][] edgesArr) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edgesArr) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        visited = new boolean[n];

        int ans = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                nodes = 0;
                edges = 0;

                dfs(i);

                edges /= 2;

                if (edges == nodes * (nodes - 1) / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int node) {

        visited[node] = true;
        nodes++;

        edges += graph[node].size();

        for (int next : graph[node]) {

            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}