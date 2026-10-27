class Solution {
    public String[] createGrid(int m, int n) {
        char[][] g = new char [m][n];
        for(int i = 0; i < m; i++){
            Arrays.fill(g[i], '#');
                
            }
            for(int j = 0; j <n; j++){
                g[0][j] = '.';
            }
            for(int i = 0; i < m; i++){
            g[i][n  -1] = '.';    
            }
            List<String> ans  = new ArrayList<>();
            for(int i = 0; i < m; i++){
                ans.add(new String(g[i]));
            }
            return ans.toArray(new String[0]);
        
    }
}