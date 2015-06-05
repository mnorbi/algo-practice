import java.util.*;
public class Question18_7{
	public static void main(String[] args){
		solve(new String[]{ "cat", "banana", "dog", "nana", "walk", "walker", "dogwalker", "longerthandogwalker" });
		solve(new String[]{});
		solve(new String[]{""});
		solve(new String[]{"a","b","c"});
                solve(new String[]{"a", "b", "aa", "ab", "aabb", "aabbc"});
	}
	private static void solve(String[] strings){
                LongestWordFromOthers solver = new LongestWordFromOthers(strings);
                char[] solution = solver.find();
                if (solution != null){
                        System.out.println(solution);
                } else {
                        System.out.println("No solution");
                }
	}
}
class LongestWordFromOthers{
	char[][] charsArr;
	Node trie;
	Boolean[][] dp;

	LongestWordFromOthers(String[] strings){
		this(toCharArray(strings));
	}
	
	private static char[][] toCharArray(String[] strings){
		char[][] charsArr = new char[strings.length][];
		for(int i = 0; i < charsArr.length; i++){
			charsArr[i] = strings[i].toCharArray();
		}
		return charsArr;
	}

	LongestWordFromOthers(char[][] charsArr){
		this.charsArr = charsArr;
		this.trie = buildTrie();
		this.dp = initDpTable();
	}

	private Boolean[][] initDpTable(){
		Boolean[][] ret = new Boolean[charsArr.length][];
		for(int i = 0; i < ret.length; ++i){
			ret[i] = new Boolean[charsArr[i].length];
		}
		return ret;
	}

	private Node buildTrie(){
		Node trie = new Node();
                for(char[] chars : charsArr){
                        trie.addWord(chars);
                }
		return trie;
	}

	public char[] find(){
		int maxIdx = -1;
		for(int i = 0; i < charsArr.length; i++){
			if (isLonger(i, maxIdx)){
				if (findTiles(i, 0)){
					maxIdx = i;
				}
			}
		}
		if (maxIdx > -1){
			return charsArr[maxIdx];
		}
		return null;
	}

	private boolean isLonger(int idxA, int idxB){
		if(idxA < 0) return false;
		if(idxB < 0) return true;
		char[] a = charsArr[idxA];
		char[] b = charsArr[idxB];
		if (a==b) return false;
		if (a == null || a.length == 0) return false;
		if (b == null) return true;
		return a.length > b.length;
	}

	//use memoization to speed up search
	private Boolean findTiles(int charsIdx, int from){
		if (from >= charsArr[charsIdx].length) return Boolean.TRUE;
                if (dp[charsIdx][from] != null) return dp[charsIdx][from];
		char[] chars = charsArr[charsIdx];
                for(int i = from; i < chars.length; ++i){
                	for(int j = i+1; j <= chars.length; j++){
		                if (trie.contains(chars, 0, i) && trie.contains(chars, i, j) && findTiles(charsIdx, j)){
					dp[charsIdx][from] = Boolean.TRUE;
					return Boolean.TRUE;
				}
	                }
                }
		dp[charsIdx][from] = Boolean.FALSE;		
		return Boolean.FALSE;
	}
}
class Node{
	char[] storedChars;
	Map<Character, Node> nodes = new HashMap<>();

	Node(){}

	void addWord(char[] chars){
		addWord(chars, 0);
	}

	private void addWord(char[] chars, int from){
		if (from == chars.length){
			if (storedChars == null){ 
				storedChars = chars;
			}
			return;
		}
		char aChar = chars[from];
		if (!nodes.containsKey(aChar)){
			nodes.put(aChar, new Node());
		}
		nodes.get(aChar).addWord(chars, ++from);
	}
	boolean contains(char[] chars, int from, int to){
		if (from == to) {
			boolean ret = storedChars != null && storedChars != chars;
			return ret;
		}
		if (from >= chars.length) return false;
		char aChar = chars[from];
		if (nodes.containsKey(aChar)){
			return nodes.get(aChar).contains(chars, ++from, to);
		}
		return false;
	}
}
