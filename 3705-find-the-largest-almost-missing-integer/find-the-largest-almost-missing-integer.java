class Solution {
    public int largestInteger(int[] nums, int k) {
        int n =  nums.length;
        int answer = -1;
        for(int x = 0; x <=  50; x++){
            int count = 0;
            for(int start =  0; start <= n - k; start++){
                boolean found =  false;
                for(int j = start; j <  start + k; j++){
                    if(nums[j] == x){
                        found =  true;
                        break;
                    }
                }
                if(found){
                    count++;
                }
            }
            if(count == 1){
                answer = Math.max(answer, x);
            }
        }
        return answer;
    }
}