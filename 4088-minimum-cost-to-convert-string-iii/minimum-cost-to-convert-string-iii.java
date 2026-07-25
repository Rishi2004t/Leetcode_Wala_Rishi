class Solution {
    public int minCost(String source, String target, List<List<String>> rules, int[] costs) {
        int n =  source.length();
        long[] dp =  new long[ n + 1];
        Arrays.fill(dp,  Long.MAX_VALUE / 2);
        dp[0] = 0;
        for(int i = 0;  i <  n; i++){
            if (dp[i] >=  Long.MAX_VALUE / 2) continue;
            if(source.charAt(i)  == target.charAt(i))  dp[ i + 1] = Math.min(dp[i + 1],  dp[i]);
            for(int r = 0; r <  rules.size(); r++){
                String p = rules.get(r).get(0),  q =  rules.get(r).get(1);
                int len = p.length();
                if(i  + len >  n)  continue;
                boolean match = true;
                int wildcards = 0;
                for(int j= 0; j <  len; j++){
                    if(p.charAt(j)  == '*')wildcards++;
                    else if(p.charAt(j) !=  source.charAt(i + j)){ match  =  false;  break;}
                    if(q.charAt(j) != target.charAt(i + j)) {match =  false; break;}
                }
                if(match)  dp[i +  len] = Math.min(dp[i + len], dp[i]+  costs[r] +  wildcards);
            }
        }
        return dp[n] >= Long.MAX_VALUE / 2 ?  -1  :(int) dp[n];
    }
}