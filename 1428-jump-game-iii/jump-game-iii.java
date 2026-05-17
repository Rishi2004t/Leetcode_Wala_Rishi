class Solution {
    public boolean canReach(int[] arr, int start) {
        return dfs(arr, start, new boolean[arr.length]);
    }
    private boolean dfs(int[] arr,int i, boolean[] vis){
        if(i  < 0 || i >= arr.length || vis[i])return false;
        if(arr[i] == 0)return true;
        vis[i] = true;
        return dfs(arr, i + arr[i], vis) || dfs(arr, i - arr[i], vis);
    }
}