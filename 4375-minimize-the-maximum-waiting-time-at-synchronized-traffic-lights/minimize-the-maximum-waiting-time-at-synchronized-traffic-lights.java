class Solution {
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        
            int maxGreen = 0;
             for(int light :  lights){
                 maxGreen  = Math.max(maxGreen,  light);
             }
        int answer = 0;
        for(int time : arrivalTime){
                 int r = time % period;
                 int wait;
                 if(r < maxGreen){
                     wait = 0;
                     
                 }else{
                     wait = period  - r;
                 }
             
            answer = Math.max(answer, wait);
            
        }
        return answer;
    }
}