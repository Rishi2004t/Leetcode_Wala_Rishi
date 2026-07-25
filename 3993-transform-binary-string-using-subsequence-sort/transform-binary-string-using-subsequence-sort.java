class Solution {
    public boolean[] transformStr(String s, String[] str) {
        
    int n = s.length();
    boolean[] result  =  new boolean[str.length];
    int sZeros = 0;
    for(char c :  s.toCharArray()) if  (c == '0')sZeros++;
for(int i = 0; i <  str.length; i++){
    String target =  str[i];
    int qCount = 0;
    int tZeros = 0;
    for(char c :  target.toCharArray()){
        if(c == '0') tZeros++;
        else if (c == '?') qCount++;
    }
    if(tZeros > sZeros){
        result[i] =  false;
        continue;
    }
    int neededZeros =  sZeros - tZeros;
    result[i] =  canTransform(s, target, neededZeros);
}
return result;
}
private boolean canTransform(String s ,  String t, int  neededZeros){
    int sZeros = 0,  tZeros = 0;
    for(int i = 0; i <  s.length(); i++){
        if(s.charAt(i) == '0') sZeros++;
        if(t.charAt(i) == '0') tZeros++;
        else if(t.charAt(i) == '?' && neededZeros >  0){
            tZeros++;
            neededZeros--;
        }
        if(tZeros <  sZeros)  return false;
    }
    return true;
}
}