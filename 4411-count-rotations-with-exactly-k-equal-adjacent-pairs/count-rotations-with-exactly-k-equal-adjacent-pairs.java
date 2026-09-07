class Solution {
    public int countRotations(String s, int k) {
        int n = s.length();
        int ans = 0;
         for (int r = 0; r <  n; r++){
             String rotated =  s.substring(r) +  s.substring(0, r);
             int score  =  0;
             for(int  i = 0; i < n  - 1; i++){
                 if(rotated.charAt(i) == rotated.charAt(i + 1)){
                     score++;
                 }
             }
             if(score == k){
                 ans++;
             }
         }
         return ans;
    }
}