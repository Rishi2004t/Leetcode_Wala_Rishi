class Solution {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        List<List<Integer>> ans =  new ArrayList<>();
        long prev =  (long) lower - 1;
        for(int num :  nums){
            if(num < lower)
                continue;
            if(num >  upper) break;
            if(num <=  prev)  continue;
            if(num -  prev > 1){
                ans.add(Arrays.asList((int)(prev +  1), num - 1));
                
            }
            prev =  num;
            
        }
        if(upper -  prev >=  1){
            ans.add(Arrays.asList((int)(prev +  1), upper));
        }
        return ans;
    }
}