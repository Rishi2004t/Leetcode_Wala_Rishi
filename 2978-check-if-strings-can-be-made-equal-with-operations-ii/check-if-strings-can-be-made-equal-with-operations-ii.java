class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        int freqEven[] = new int[26];
        int freqOdd[] = new int[26];
        for(int i  =0 ;i < n; i++){
            if(i % 2 == 0){
                freqEven [s1.charAt(i)-'a']++;
                freqEven [s2.charAt(i)-'a']--;
            }else{
                freqOdd [s1.charAt(i)- 'a']++;
                freqOdd [s2.charAt(i) - 'a']--;
            }
        }
        for(int i = 0; i < 26; i++){
            if(freqEven[i] != 0 || freqOdd[i] != 0){
                return false;
            }
        }
        return true;
    }
}