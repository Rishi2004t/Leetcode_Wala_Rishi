import java.util.*;

class Solution {

    public List<String> braceExpansionII(String expression) {

        Set<String> result = dfs(expression);

        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);

        return ans;
    }

    private Set<String> dfs(String exp) {

        Set<String> cur = new HashSet<>();

        cur.add("");

        Set<String> res = new HashSet<>();

        int i = 0;

        while (i < exp.length()) {

            if (Character.isLetter(exp.charAt(i))) {

                Set<String> next = new HashSet<>();

                String ch = String.valueOf(exp.charAt(i));

                for (String s : cur) {
                    next.add(s + ch);
                }

                cur = next;

                i++;
            }

            else if (exp.charAt(i) == '{') {

                int cnt = 0;
                int j = i;

                while (j < exp.length()) {

                    if (exp.charAt(j) == '{') cnt++;
                    if (exp.charAt(j) == '}') cnt--;

                    if (cnt == 0) break;

                    j++;
                }

                Set<String> inner =
                        dfs(exp.substring(i + 1, j));

                Set<String> next = new HashSet<>();

                for (String a : cur) {
                    for (String b : inner) {
                        next.add(a + b);
                    }
                }

                cur = next;

                i = j + 1;
            }

            else if (exp.charAt(i) == ',') {

                res.addAll(cur);

                cur = new HashSet<>();

                cur.add("");

                i++;
            }
        }

        res.addAll(cur);

        return res;
    }
}