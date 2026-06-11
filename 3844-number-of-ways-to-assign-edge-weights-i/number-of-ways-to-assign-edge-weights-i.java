class Solution {
    long MOD = 1000000007;
    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adj = new ArrayList[n +1];
        for(int i = 0; i <=n; i++){
            adj[i] = new ArrayList<>();
        }
        for(int[] e: edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int depth = dfs(1,0,adj);
        long ans = 1;
        for(int i = 1;i < depth; i++){
            ans  = (ans * 2) % MOD;
        }
        return (int)ans;
    }
    private int dfs(int node, int parent, List<Integer>[] adj){
        int maxDepth = 0;
        for(int nei :  adj[node]){
            if(nei != parent){
                maxDepth = Math.max(maxDepth, 1 + dfs(nei, node, adj));
            }
        }
        return maxDepth;
    }
}