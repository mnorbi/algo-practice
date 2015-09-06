class FindSubstring{
    public static void main(String[] args) {
        System.out.println(new Solution().findSubstring("abbaccaaabcabbbccbabbccabbacabcacbbaabbbbbaaabaccaacbccabcbababbbabccabacbbcabbaacaccccbaabcabaabaaaabcaabcacabaa"
                , Arrays.asList("cac", "aaa", "aba", "aab", "abc")));
        System.out.println(new Solution().findSubstring("cbbc"
                , Arrays.asList("b", "b", "b", "a", "c")));
        System.out.println(new Solution().findSubstring("barfoothefoobarman"
                , Arrays.asList("foo", "bar")));
    }
    public ArrayList<Integer> findSubstring(String S, final List<String> L) {
        int N = L.get(0).length();
        Trie trie = new Trie();
        for(int i = 0; i < L.size(); ++i){
            String word = L.get(i);
            trie.add(word, trie.uniqueWordCount);
        }
        int wordSize = L.get(0).length();
        int len = L.size()*N;
        ArrayList<Integer> ret = new ArrayList<>();
        int[] wordCount = new int[trie.uniqueWordCount];
        for(int i = 0; i < wordSize; ++i){
            Arrays.fill(wordCount, 0);
            int count = 0;
            for(int j = i; j+wordSize <= S.length(); j += wordSize){
                SearchResult searchResult = trie.search(S, j, j+wordSize);
                if (searchResult == null){
                    Arrays.fill(wordCount, 0);
                    count = 0;
                } else {
                    ++wordCount[searchResult.idx];
                    ++count;
                    while(wordCount[searchResult.idx] > searchResult.count){
                        int start = j+wordSize-count*wordSize;
                        SearchResult searchResult2  = trie.search(S, start, start+wordSize);
                        --wordCount[searchResult2.idx];
                        --count;
                    }
                    if (count == L.size()){
                        int start = j+wordSize-len;
                        ret.add(start);
                        searchResult = trie.search(S, start, start+wordSize);
                        --wordCount[searchResult.idx];
                        --count;
                    }
                }
            }
        }

        return ret;
    }
}
class Trie {
    TrieNode head = new TrieNode();
    int uniqueWordCount = 0;

    SearchResult search(String s, int lo, int hi) {
        if (lo < 0) return null;
        TrieNode node = head;
        for (int i = lo; i < hi && i < s.length(); ++i) {
            Character c = s.charAt(i);
            if (!node.map.containsKey(c)) {
                return null;
            }
            node = node.map.get(c);
        }
        return new SearchResult(node.count, node.idx);
    }

    void add(String word, int idx) {
        TrieNode node = head;
        for (int i = 0; i < word.length(); ++i) {
            Character c = word.charAt(i);
            if (!node.map.containsKey(c)) {
                node.map.put(c, new TrieNode());
            }
            node = node.map.get(c);
        }
        if (node.idx < 0) {
            node.idx = idx;
            ++uniqueWordCount;
        }
        node.count++;
    }
}
class SearchResult {
    int count = 0;
    int idx = -1;

    SearchResult(int c, int i) {
        this.count = c;
        this.idx = i;
    }
}
class TrieNode {
    int count = 0;
    int idx = -1;
    final Map<Character, TrieNode> map = new HashMap<>();
}
