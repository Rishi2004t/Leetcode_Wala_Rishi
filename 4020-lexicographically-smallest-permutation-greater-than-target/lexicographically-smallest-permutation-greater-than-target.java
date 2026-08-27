class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        int pos = -1;
        int bigger = -1;
        for (int i = 0; i < n; i++) {
            int x = target.charAt(i) - 'a';
            for (int c = x + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    pos = i;
                    bigger = c;
                    break;
                }
            }
            if (freq[x] > 0) {
                freq[x]--;
            } else {
                break;
            }
        }
        if (pos == -1) {
            return "";
        }
        freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        for (int i = 0; i < pos; i++) {
            freq[target.charAt(i) - 'a']--;
        }
        freq[bigger]--;
        StringBuilder ans = new StringBuilder();
        ans.append(target.substring(0, pos));
        ans.append((char) ('a' + bigger));
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                ans.append((char) ('a' + c));
                freq[c]--;
            }
        }

        return ans.toString();
    }
}