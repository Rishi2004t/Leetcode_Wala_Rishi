class Solution {
    public long maxRatings(int[][] units) {
        long  sum = 0;
            int globalMin  = Integer.MAX_VALUE;
        int minSecond = Integer.MAX_VALUE;
        for(int[] row :  units){
            int first = Integer.MAX_VALUE;
            int second =Integer.MAX_VALUE;
            for( int  x :  row){
                if(x<  first){
                second  = first;
                first = x;
                
            }else if(x <  second){
                second = x;
            }
        }
            if(row.length == 1){
                second = first;
        }
        globalMin= Math.min(globalMin, first);
        minSecond = Math.min(minSecond,  second);
        sum += second;
    }
        return sum -  minSecond + globalMin;
    }
}