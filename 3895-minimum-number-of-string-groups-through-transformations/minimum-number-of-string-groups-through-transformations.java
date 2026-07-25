class Solution {
    public int minimumGroups(String[] words) {
        HashSet<String> set = new HashSet<>();
        for (String w : words) {
            int n = w.length();
            int lenE = (n + 1) / 2;
            int lenO = n / 2;
            char[] E = new char[lenE];
            char[] O = new char[lenO];
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    E[i / 2] = w.charAt(i);
                    
                }
                else {
                    O[i / 2] = w.charAt(i);
                }
            }
            String rE = getMinRotation(E);
            String rO = getMinRotation(O);
            set.add(rE + "#" + rO);
        }
        return set.size();
    }

    private String getMinRotation(char[] s) {
        if (s.length == 0) return "";
        int n = s.length;
        int i = 0, j = 1, k = 0;
        while (i < n && j < n && k < n) {
            char ci = s[(i + k) % n];
            char cj = s[(j + k) % n];
            if (ci == cj) {
                k++;
            }
            else {
                if (ci > cj) {
                    i += k + 1;
                }
                else {
                    j += k + 1;
                }
                if (i == j) {
                    j++;
                }
                k = 0;
            }
        }
        int minIdx = Math.min(i, j);
        StringBuilder sb = new StringBuilder(n);
        for (int step = 0; step < n; step++) {
            sb.append(s[(minIdx + step) % n]);
            
        }
        return sb.toString();
    }
}