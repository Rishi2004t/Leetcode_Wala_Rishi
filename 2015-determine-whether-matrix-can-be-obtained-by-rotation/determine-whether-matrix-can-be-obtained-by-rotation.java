class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        for(int k = 0; k < 4; k++){
            boolean same = true;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(mat[i][j] != target[i][j]){
                        same = false;
                    }
                }
            }
        
        if(same) return true;

        int[][] newMat = new int[n][n];
        for(int i = 0; i <  n; i++){
            for(int j = 0; j < n; j++){
                newMat[j][n - i - 1] = mat[i][j];

            }
        }
        mat = newMat;
        }
        return false;
    }
}