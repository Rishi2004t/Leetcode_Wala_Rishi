class Solution {
    public int countGroups(int[] position, int[] speed, int distance) {
        int n =  position.length;
        int count =1;
        int repIdx = n -1;
        for(int i  =  n - 2; i >=  0;i--){
            boolean immediateMerge =  position[ i + 1] -  position[i] <=  distance;
            boolean catchesUp =  speed[i]> speed[repIdx];
            int gap =  position[repIdx]- position[i];
            if(immediateMerge ||  catchesUp){
                
            }else{
                count++;
                repIdx = i;
            }
        }
        return count;
    }
}