class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        int n = s.length();
        int ones = 0;
        int left = 0;
        int bestLen = Integer.MAX_VALUE;
        for(int right = 0; right < n; right++){
            if(s.charAt(right) == '1'){
                ones++;
            }
            while(ones > k){
                if(s.charAt(left) == '1'){
                    ones--;
                }
                left++;
            }
            while(ones == k){
                String curr = s.substring(left, right + 1);
                int len = curr.length();
                if(len <  bestLen){
                    bestLen = len;
                    ans = curr;

                }else if(len == bestLen && (ans.equals("") || curr.compareTo(ans) < 0)){
                    ans = curr;
                }
                if(s.charAt(left) == '1'){
                    ones--;
                }
                left++;
            }
        }
        return ans;
    }
}