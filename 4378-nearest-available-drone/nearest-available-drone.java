class Solution {
    public int nearestDrone(int[][] drones, int[] target) {
        int ans  = -1;
        int  minDist = Integer.MAX_VALUE;
        for(int i = 0; i<  drones.length; i++){
            int x = drones[i][0];
            int y =  drones[i][1];
            int range =  drones[i][2];
            int dist =  Math.abs(x - target[0]) + Math.abs(y -  target[1]);
            if(dist <=  range){
                if(dist <  minDist){
                    minDist =  dist;
                    ans =  i;
                }
            }
        }
        return ans;
    }
}