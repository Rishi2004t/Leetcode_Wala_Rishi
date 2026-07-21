class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";
        int n = t.length();
        int ones  = 0;
         for(char ch :  s.toCharArray()){
            if(ch == '1'){
                ones++;

            }
         }
         int answer = ones;
         for(int i = 1; i < n -1; i++){
            if(t.charAt(i) == '1' && t.charAt(i - 1) == '0'){
            int j = i;
            while(j < n && t.charAt(j) == '1'){
                j++;
            }
            if(j <  n && t.charAt(j) == '0'){
                int oneLength = j - i;
                int left  = i - 1;
                while(left >= 0 && t.charAt(left) == '0'){
                    left--;
                }
                int right = j;
                while(right < n && t.charAt(right) == '0'){
                    right++;
                }
                int zeroLength = right - left - 1;
                answer = Math.max(answer, ones  - oneLength + zeroLength); 
            }
            i = j - 1;
         }
    }
    return answer;
    }
}