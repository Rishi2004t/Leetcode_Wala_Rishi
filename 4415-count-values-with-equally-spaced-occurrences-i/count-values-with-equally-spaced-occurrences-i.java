class Solution {
    public int countSpecialIntegers(int[] nums) {
    Map<Integer, List <Integer>> map  =  new HashMap<>();
        for(int i = 0 ; i< nums.length; i++){
            map.computeIfAbsent(nums[i],  k -> new ArrayList <>()).add(i);
            
        }
        int ans = 0;
        for(List<Integer> pos: map.values()){
            if(pos.size()!= 3) continue;
            if(pos.get(1)- pos.get(0) == pos.get(2)- pos.get(1)){
                ans++;
            }
        }
        return ans;
        
    }
}