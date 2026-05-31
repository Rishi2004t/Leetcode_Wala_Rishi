class Solution {
    public int[] scoreValidator(String[] events) {
        int score  = 0;
        int counter  = 0 ;
        for(int i = 0 ; i < events.length; i++){
            String e = events[i];
            if(e.equals("W")){
                counter++;
                if(counter  == 10) break;
            }
            else if(e.equals("WD") || e.equals("NB")){
                score += 1;
            }else{
                score += Integer.parseInt(e);
            }
        }
        return new int[]{score, counter};
    }
}