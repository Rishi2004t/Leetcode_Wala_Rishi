class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char current = 'a';

        for(int i = 0; i < n; i++){
            if(word[i] == 0){
                if(current >'z')    return ""; 
                word[i] = current;
                for(int j = i + 1; j < n; j++){
                    if(lcp[i][j] > 0){
                        word[j] = word[i];
                    }
                }
                current++;
            }
        }
        for(int i = n - 1; i >= 0; i--){
            for(int j = n -1; j>= 0; j--){
                if(word[i] == word[j]){
                    int val = 1;
                    if(i + 1 < n && j + 1 < n){
                        val += lcp[i + 1][j + 1];
                    }
                
                if(lcp[i][j] != val) return "";
            }else{
                if(lcp[i][j] != 0)return "";
            }
        }
        }
        return new String(word);
    }
}