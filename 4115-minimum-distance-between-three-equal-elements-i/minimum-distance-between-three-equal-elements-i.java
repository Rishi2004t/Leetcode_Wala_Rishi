class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);

        }
        int ans = Integer.MAX_VALUE;
        for(List<Integer> list :  map.values()){
            int size = list.size();
            if(size >= 3){
                for(int i = 0; i <size; i ++){
                    for(int j  = i + 1; j < size; j++){
                        for(int k = j + 1; k < size; k ++){
                            int a = list.get(i);
                            int b = list.get(j);
                            int c = list.get(k);

                            int dist = Math.abs(a - b) + Math.abs(b - c) + Math.abs(c -a);
                            ans = Math.min(ans, dist);
                        }
                    }
                }
            }
        }
            return ans == Integer.MAX_VALUE ? -1 :  ans;
        
    }
}