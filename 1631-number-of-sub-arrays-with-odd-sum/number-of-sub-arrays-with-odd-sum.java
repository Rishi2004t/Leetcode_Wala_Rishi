
class Solution {
    public int numOfSubarrays(int[] arr) {

        int MOD = 1_000_000_007;

        long odd = 0;
        long even = 1;
        long ans = 0;

        int prefixSum = 0;

        for (int num : arr) {

            prefixSum += num;

            if (prefixSum % 2 == 1) {
                ans += even;
                odd++;
            } else {
                ans += odd;
                even++;
            }

            ans %= MOD;
        }

        return (int) ans;
    }
}
