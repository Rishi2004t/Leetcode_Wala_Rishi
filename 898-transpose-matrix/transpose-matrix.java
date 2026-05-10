class Solution {
    public int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int col = matrix[0].length;
        int transpose[][] = new int[col][rows];
        for(int i = 0; i <  rows; i++){
            for(int j = 0; j < col ;j++){
                transpose[j][i] = matrix[i][j];

            }
        }
        return transpose;
    }
}