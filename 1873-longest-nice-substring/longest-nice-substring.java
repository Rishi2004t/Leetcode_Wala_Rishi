class Solution {
    public String longestNiceSubstring(String s) {
     String ans  = "";
     for(int i = 0 ;i <s.length(); i++){
        for(int j =  i + 1; j <= s.length(); j++){
            String  sub = s.substring(i , j);
            if(isNice(sub) && sub.length() > ans.length()){
                ans = sub;
            }
        }
     }   
     return ans;
    }
    private boolean isNice(String s){
        HashSet<Character> set = new HashSet<>();
        for(char c:  s.toCharArray()){
            set.add(c);

        }
        for(char c :  set){
            if(Character .isLowerCase(c)){
                if(!set.contains(Character.toUpperCase(c))){
                    return false;
                }
            }else{
                if(!set.contains(Character.toLowerCase(c))){
                    return false;
                }
            }
        }
        return true;
    }
}