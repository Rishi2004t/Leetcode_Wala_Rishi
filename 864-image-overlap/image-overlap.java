class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        List<int[]> A =  new ArrayList<>();
        List<int[]> B =  new ArrayList<>();
        int n =  img1.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(img1[i][j] == 1)A.add(new int[]{i, j});
                if(img2[i][j] == 1)B.add(new int[]{i , j});

            }
        }
        HashMap<String, Integer> map =  new HashMap<>();
        int ans = 0;
        for(int[] p1 :  A){
            for(int[] p2 : B){
                int dx =  p1[0] - p2[0];
                int dy =  p1[1] -  p2[1];
                String key = dx + "#" + dy;
                int cnt =  map.getOrDefault(key, 0) + 1;
                map.put(key,cnt);
                ans =  Math.max(ans, cnt);
            }
        }  
        return ans;

    }
}