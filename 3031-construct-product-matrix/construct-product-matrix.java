class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int mod  = 12345;
        int n = grid.length, m = grid[0].length;
        int size = n * m;
        int[] arr = new int[size];

        int idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                arr[idx++] = grid[i][j];
            }
        }
        int[] prefix = new int[size];
        prefix[0] = 1;
        for(int i = 1; i < size; i++){
            prefix[i] = (int)((long)prefix[i - 1] * arr[ i -1] % mod);
        }
        int[] suffix = new int[size];
        suffix[size - 1] = 1;
        for(int i = size-2; i >= 0; i--){
            suffix[i] = (int)((long)suffix[i + 1] * arr[i + 1] % mod);
        }
        int[][] ans = new int[n][m];
        idx = 0;
        for(int i = 0; i  < n ; i++){
            for(int j = 0; j < m; j++){
                ans[i][j] = (int)((long)prefix[idx] * suffix[idx] % mod);
                idx++;
            }
        }
        return ans;
    }
}