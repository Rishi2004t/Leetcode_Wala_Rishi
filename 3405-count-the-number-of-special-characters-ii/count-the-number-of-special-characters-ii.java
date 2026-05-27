class Solution {
    public int numberOfSpecialChars(String word) {
        int n =  word.length();
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];
        for(int i = 0; i < 26; i++){
            lastLower[i] = -1;
            firstUpper[i] = n;

        }
        for(int i = 0; i  < n ; i++){
            char ch = word.charAt(i);
            if(Character.isLowerCase(ch)){
                lastLower[ch - 'a'] = i;
            }else{
                firstUpper[ch - 'A'] = Math.min(firstUpper[ch - 'A'], i);
            }
        }
        int count = 0;
        for(int i = 0; i < 26; i++){
        if(lastLower[i] != -1 && firstUpper[i] != n && lastLower[i] <  firstUpper[i]){
            count++;
        }
        }
        return count;
    }
}