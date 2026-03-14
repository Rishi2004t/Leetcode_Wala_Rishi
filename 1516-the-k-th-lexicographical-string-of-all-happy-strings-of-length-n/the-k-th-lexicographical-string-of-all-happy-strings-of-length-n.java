class Solution {
        int count = 0;
        String ans = "";
        public String getHappyString(int n, int k){
            backtrack("", n, k);
                return ans;
            }
            void backtrack(String curr, int n, int k){
                if(curr.length() == n){
                    count++;
                    if(count == k){
                        ans = curr;
                    }
                return;
            }
            char[] letters = {'a','b','c'};
            for(char c : letters){
                if(curr.length() > 0 && curr.charAt(curr.length() -1) == c)
                continue;
                backtrack(curr + c, n, k);
            
        }   
    }
}