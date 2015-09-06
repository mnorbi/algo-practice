public class Solution {
	public String countAndSay(int a) {
	    String s = "1";
	    for(int i = 1; i < a; ++i){
	        StringBuilder sb = new StringBuilder();
	        int j = 0;
	        for(;;){
	            int pj = j;
	            j = seqEnd(s, j);
	            sb.append(j-pj);
	            sb.append(s.charAt(pj));
	            if (j >= s.length()) break;
	        }
	        s = sb.toString();
	    }
	    return s;
	}
	int seqEnd(String s, int i){
	    char c = s.charAt(i);
	    for(i = i+1; i < s.length(); ++i){
	        if (s.charAt(i) != c) break;
	    }
	    return i;
	}
}
