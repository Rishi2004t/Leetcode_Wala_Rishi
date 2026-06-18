class Solution {
    public double angleClock(int hour, int minute) {
        double minAngle = minute  * 6;
        double hourAngle = (hour % 12)  * 30 + minute * 0.5;
        double diff = Math.abs (hourAngle - minAngle);
        return Math.min(diff,360 - diff);
    }
}