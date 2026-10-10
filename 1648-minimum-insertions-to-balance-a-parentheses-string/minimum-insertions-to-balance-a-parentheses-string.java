
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int ans = 0;
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else {
                // Check whether we have a pair of ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i += 2;
                } else {
                    // Insert the missing ')'
                    ans++;
                    i++;
                }

                // Match this pair with an opening '('
                if (open > 0) {
                    open--;
                } else {
                    // Insert a missing '('
                    ans++;
                }
            }
        }

        // Every unmatched '(' needs two ')'
        ans += open * 2;

        return ans;
    }
}
