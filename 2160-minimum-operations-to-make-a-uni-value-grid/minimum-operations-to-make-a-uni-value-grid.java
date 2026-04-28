class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int[] arr = new int[ m * n];
        int idx = 0;
        int base = grid[0][0];
        for( int[] row : grid){
            for(int  val :  row){
                if((val - base) % x != 0) return -1;
                arr[idx++] = val;

            }
        }
        java.util.Arrays.sort(arr);
        int median = arr[arr.length / 2];

        int ops = 0;
        for(int val :  arr){
            ops += Math.abs(val - median) / x;
        }
        return ops;
    }
}