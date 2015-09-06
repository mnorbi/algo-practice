public class Solution {
	public String addBinary(String a, String b) {
	    StringBuilder sb = new StringBuilder();
	    for(int i = a.length()-1, j = b.length()-1, cry = 0; i >= 0 || j >= 0 || cry > 0;--i,--j){
	        int dA = (i >= 0) ? (a.charAt(i) - '0') : 0;
	        int dB = (j >= 0) ? (b.charAt(j) - '0') : 0;
	        int sum = dA+dB+cry;
	        sb.append(sum%2);
	        cry = sum/2;
	    }
	    return sb.reverse().toString();
    }
}
