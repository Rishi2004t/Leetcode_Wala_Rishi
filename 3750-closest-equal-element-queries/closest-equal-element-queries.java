class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i  = 0; i < n; i++){
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        int [] dist =  new int[n];
        Arrays.fill(dist, -1);
        for(List<Integer> list :  map.values()){
            int size = list.size();
            if(size == 1) continue;
            List<Integer> extended = new ArrayList<>(list);
            for(int x :  list){
                extended.add(x + n);
            }
            for(int i = 0; i < size; i++){
                int curr = extended.get(i);
                int next  = extended.get(i + 1);
                int prev = extended.get(i + size - 1);
                
                int d1 = next - curr;
                int d2 = curr - prev + n;
                int best = Math.min(d1, d2);
                dist[curr % n] = best;
            }

        }
        List<Integer> ans  = new ArrayList();
        for(int i = 0; i < queries.length; i++){
            ans.add (dist[queries[i]]);
        }
        return ans;
    }
}