public class Solution {
	public String addBinary(String a, String b) {
	    if (a == null || b == null) return null;
	    if (a.length() == 0) return b;
	    if (b.length() == 0) return a;
	    if (a.length() < b.length()){
	        String tmp = a;
	        a = b;
	        b = tmp;
	    }
	    StringBuilder sb = new StringBuilder();
	    int i = b.length();
	    int cry = 0;
	    while(i-->0){
	        char cA = a.charAt(i+a.length()-b.length());
	        char cB = b.charAt(i);
	        Character c = null;
	        if (cA == '1' && cB == '1'){
	            if (cry == 1){
	                c = '1';
	            } else {
	                c = '0';
	                cry = 1;
	            }
	        } else if (cA == '1' || cB == '1'){
                if (cry == 1){
                    c = '0';
                } else {
                    c = '1';
                }
            } else {
                if (cry == 1){
                    c = '1';
                    cry = 0;
                } else {
                    c = '0';
                }
            }
	        sb.append(c);
	    }
	    i = a.length()-b.length();
	    while(i-->0){
	        char c = a.charAt(i);
	        if (c == '1' && cry == 1){
	            c = '0';
	        } else if (cry == 1){
	            c = '1';
	            cry = 0;
	        }
	        sb.append(c);
	    }
	    if (cry == 1){
	        sb.append('1');
	    }
	    return sb.reverse().toString();
	}
}

