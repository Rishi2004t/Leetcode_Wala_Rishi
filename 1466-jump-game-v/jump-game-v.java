class Solution {
    int[] dp;
    int n, d;

    public int maxJumps(int[] arr, int d) {
        this.n = arr.length;
        this.d = d;
        dp = new int[n];

        int ans = 1;

        for(int i = 0; i < n; i++){
            ans = Math.max(ans, dfs(arr, i));
        }

        return ans;
    }

    private int dfs(int[] arr, int i){
        if(dp[i] != 0) return dp[i];

        int max = 1; 

        
        for(int j = i + 1; j <= Math.min(n - 1, i + d); j++){
            if(arr[j] >= arr[i]) break; 
            max = Math.max(max, 1 + dfs(arr, j));
        }

        
        for(int j = i - 1; j >= Math.max(0, i - d); j--){
            if(arr[j] >= arr[i]) break; 
            max = Math.max(max, 1 + dfs(arr, j));
        }

        return dp[i] = max;
    }
}