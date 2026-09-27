class Solution {
    public String reverseParentheses(String s) {

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {

                stack.push(sb.length());

            } else if (ch == ')') {

                int start = stack.pop();

                reverse(sb, start, sb.length() - 1);

            } else {

                sb.append(ch);
            }
        }

        return sb.toString();
    }

    private void reverse(StringBuilder sb, int l, int r) {

        while (l < r) {

            char temp = sb.charAt(l);

            sb.setCharAt(l, sb.charAt(r));
            sb.setCharAt(r, temp);

            l++;
            r--;
        }
    }
}