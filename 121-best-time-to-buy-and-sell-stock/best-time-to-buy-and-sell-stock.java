class Solution {
    public int maxProfit(int[] price) {
        int minPrice = price[0];
        int maxProfit = 0;
        for(int i = 0; i <  price.length; i++){
            if(price[i] <  minPrice){
                minPrice = price[i];
            }else{
                maxProfit  = Math.max(maxProfit , price[i] - minPrice);
            }
        }
        return maxProfit;
    }
}