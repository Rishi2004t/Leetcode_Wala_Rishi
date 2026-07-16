class Solution {
    public long gcdSum(int[] nums) {
        int n =nums.length;
        int[] arr =new int[n];
        int max = 0;
        for(int i = 0; i <n; i++){
            max = Math.max(max,  nums[i]);
            arr[i] = gcd (nums[i], max);
        }
        Arrays.sort(arr);
        long ans = 0;
        int left = 0;
        int right = n -1;
        while(left <  right){
            ans += gcd(arr[left], arr[right]);
            left++;
            right--;
        }
        return ans;
    }
    private int gcd(int a, int b){
        while(b != 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}