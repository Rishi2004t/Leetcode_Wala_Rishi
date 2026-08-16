class Solution {
    public int minOperations(String s) {
        int n =  s.length();
        int minOps = Integer.MAX_VALUE;
        for(int r = 0; r < n; r++){
            String rotated = s.substring(r) +  s.substring(0 , r);
            int currentOps = r;
            for(int i = 0; i < n / 2; i++){
                char leftChar =  rotated.charAt(i);
                char rightChar =  rotated.charAt( n  -1 - i);
                if(leftChar != rightChar){
                    int diff =  Math.abs(leftChar -  rightChar);
                    currentOps += Math.min(diff,  26 - diff);
                }
            }
            minOps = Math.min(minOps, currentOps);
        }
        return minOps;
    }
}