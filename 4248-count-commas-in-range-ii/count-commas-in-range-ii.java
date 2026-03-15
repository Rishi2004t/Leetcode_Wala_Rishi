class Solution {
    public long countCommas(long n) {
        long comma = 0;
        long level = 1000;
        while (level <= n){
            comma += (n - level + 1);
            level *= 1000;
        }
        return comma;
    }
}