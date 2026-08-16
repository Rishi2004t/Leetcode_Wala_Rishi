class Solution {
    public double minPrice(int[] prices, int[] discount) {
        Arrays.sort(prices);
        Arrays.sort(discount);
        int i = prices.length - 1;
        int j = discount.length - 1;
        double total = 0;
        while( i >= 0){
            if(j >= 0){
                total += (double)prices[i] * (100 - discount[j]) / 100;
                j--;
            }else{ total += prices[i];
                }
            i--;
        }
        return total;
    }
}