

class Solution {

    private final int MAX = 1000001;

    public String smallestPalindrome(String s, int k) {

        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        if (!isPalindromePossible(count))
            return "";

        int[] halfCount = new int[26];
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;

            if ((count[i] & 1) == 1)
                middle = (char) ('a' + i);
        }

        int totalPerm = countArrangements(halfCount);

        if (k > totalPerm)
            return "";

        StringBuilder left = generateLeftHalf(halfCount, k);

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (middle != 0)
            ans.append(middle);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private boolean isPalindromePossible(int[] count) {

        int odd = 0;

        for (int x : count) {
            if ((x & 1) == 1)
                odd++;
        }

        return odd <= 1;
    }

    private StringBuilder generateLeftHalf(int[] halfCount, int k) {

        int len = 0;

        for (int x : halfCount)
            len += x;

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {

            for (int i = 0; i < 26; i++) {

                if (halfCount[i] == 0)
                    continue;

                halfCount[i]--;

                int ways = countArrangements(halfCount);

                if (ways >= k) {
                    left.append((char) ('a' + i));
                    break;
                } else {
                    k -= ways;
                    halfCount[i]++;
                }
            }
        }

        return left;
    }

    private int countArrangements(int[] count) {

        int total = 0;

        for (int x : count)
            total += x;

        long ans = 1;

        for (int freq : count) {

            ans *= nCk(total, freq);

            if (ans >= MAX)
                return MAX;

            total -= freq;
        }

        return (int) ans;
    }

    private int nCk(int n, int k) {

        long ans = 1;

        k = Math.min(k, n - k);

        for (int i = 1; i <= k; i++) {

            ans = ans * (n - i + 1) / i;

            if (ans >= MAX)
                return MAX;
        }

        return (int) ans;
    }
}