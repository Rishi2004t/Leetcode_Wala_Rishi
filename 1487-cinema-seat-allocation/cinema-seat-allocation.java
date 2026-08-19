class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << col));
            }
        }
        int answer = (n - map.size()) * 2;
        for (int mask : map.values()) {
            boolean left = true;
            boolean middle = true;
            boolean right = true;
            for (int seat = 2; seat <= 5; seat++) {
                if ((mask & (1 << seat)) != 0) {
                    left = false;
                }
            }
            for (int seat = 4; seat <= 7; seat++) {
                if ((mask & (1 << seat)) != 0) {
                    middle = false;
                }
            }
            for (int seat = 6; seat <= 9; seat++) {
                if ((mask & (1 << seat)) != 0) {
                    right = false;
                }
            }
            if (left && right) {
                answer += 2;
            } else if (left || middle || right) {
                answer += 1;
            }
        }
        return answer;
    }
}