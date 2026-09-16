class Solution {
    static final  long MOD =  1000000007L;
    public int numberOfSets(int n, int k) {

        int N =  n + k -1;
        long[][] C =  new long[N + 1][N + 1];
        for(int  i = 0; i <= N; i++){
            C[i][0] = C[i][i] = 1;
            for(int j = 1; j < i; j++){
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;  
            }
        }
         return (int) C[N][2 * k];
    }
}