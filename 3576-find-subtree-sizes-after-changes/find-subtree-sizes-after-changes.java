
import java.util.*;

class Solution {

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        // Step 1: Build the original tree
        List<Integer>[] children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        // Step 2: Find the new parent of every node
        int[] newParent = parent.clone();

        // Last node with each character on current DFS path
        int[] last = new int[26];
        Arrays.fill(last, -1);

        // Previous matching node, used to restore DFS state
        int[] previousSame = new int[n];

        // Iterative DFS: ~node represents the exit event
        int[] stack = new int[2 * n];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {

            int value = stack[--top];

            // Exit event
            if (value < 0) {

                int node = ~value;
                int c = s.charAt(node) - 'a';

                last[c] = previousSame[node];

                continue;
            }

            // Enter event
            int node = value;
            int c = s.charAt(node) - 'a';

            // Save previous node with the same character
            previousSame[node] = last[c];

            // Update parent if matching ancestor exists
            if (last[c] != -1) {
                newParent[node] = last[c];
            }

            // Current node becomes latest matching node
            last[c] = node;

            // Exit must happen after all children
            stack[top++] = ~node;

            // Visit original children
            for (int child : children[node]) {
                stack[top++] = child;
            }
        }

        // Step 3: Build the final tree
        List<Integer>[] finalChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            finalChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            finalChildren[newParent[i]].add(i);
        }

        // Step 4: Get traversal order of final tree
        int[] order = new int[n];
        int[] dfs = new int[n];

        int size = 0;
        int top2 = 0;

        dfs[top2++] = 0;

        while (top2 > 0) {

            int node = dfs[--top2];

            order[size++] = node;

            for (int child : finalChildren[node]) {
                dfs[top2++] = child;
            }
        }

        // Step 5: Calculate subtree sizes bottom-up
        int[] answer = new int[n];
        Arrays.fill(answer, 1);

        for (int i = size - 1; i > 0; i--) {

            int node = order[i];

            answer[newParent[node]] += answer[node];
        }

        return answer;
    }
}
