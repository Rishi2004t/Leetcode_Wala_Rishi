class Solution {
    public int countSpecialIntegers(int[] nums) {
        int[] first =  new int[101];
        Arrays.fill(first, -1);
        int[] last =  new int[101];
        int[] freq =  new int[101];
        for(int i = 0; i <  nums.length;i++){
            int x =  nums[i];
            if(first[x] == -1){
                first[x] = i;
                
            }
            last[x] = i;
            freq[x]++;
        }
        int ans = 0;
        for(int x = 1; x <= 100; x++){
            
                if(freq[x] > 0 && last[x] - first[x] + 1 ==  freq[x]){
                    ans++;
                }
            
        }
        return ans;
    }
}