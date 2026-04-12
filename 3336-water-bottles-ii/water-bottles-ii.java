class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int full =  numBottles;
        int empty = 0;
        int total = 0;
        while(true){
            total += full;
            empty +=  full;
            full = 0;
            if(empty >= numExchange){
                empty -= numExchange;
                full = 1;
                numExchange++;

            }else{
                break;
            }
        }
        return total;
    }
}