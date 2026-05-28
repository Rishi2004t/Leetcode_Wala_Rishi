class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];

        int index = -1;
        int len = Integer.MAX_VALUE;
    }

    TrieNode root = new TrieNode();

    
    void insert(String word, int idx) {

        TrieNode node = root;


        if (word.length() < node.len) {
            node.len = word.length();
            node.index = idx;
        }

        for (int i = word.length() - 1; i >= 0; i--) {

            char ch = word.charAt(i);
            int c = ch - 'a';

            if (node.child[c] == null) {
                node.child[c] = new TrieNode();
            }

            node = node.child[c];

        
            if (word.length() < node.len) {
                node.len = word.length();
                node.index = idx;
            }
        }
    }

    
    int search(String word) {

        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {

            char ch = word.charAt(i);
            int c = ch - 'a';

            if (node.child[c] == null) break;

            node = node.child[c];
        }

        return node.index;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }
}