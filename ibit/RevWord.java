public class Solution {
	public String reverseWords(String a) {
	    char[] ret = new char[a.length()];
	    int to = 0;
	    for(int j = a.length()-1, i = j; i >= 0;){
	        for(i = j; i >= 0 && !Character.isWhitespace(a.charAt(i)); --i);
	        to = copy(ret, to, a, i+1, j);
	        for(j = i; j >= 0 && Character.isWhitespace(a.charAt(j)); --j);
	        if (j >= 0 && to > 0) ret[to++] = ' ';
	    }
	    return new String(ret, 0, to);
	}
	int copy(char[] ret, int to, String a, int lo, int hi){
	    if (lo > hi) return to;
	    if (lo < 0) return to;
	    for(; lo <= hi && lo < a.length(); ++lo){
	        ret[to++] = a.charAt(lo);
	    }
	    return to;
	}
}
