class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long Mass = mass;
        for(int asteroid :  asteroids){
            if(Mass <  asteroid){
                return  false;

            }
            Mass += asteroid;
        }
        return true;
    }
}