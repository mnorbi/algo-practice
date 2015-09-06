public class Solution {
	public String intToRoman(int a) {
	    String[] rom = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
	    int[] num = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
	    
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < rom.length;){
	        if (a >= num[i]){
	            sb.append(rom[i]);
	            a -= num[i];
	        } else {
	            ++i;
	        }
	    }
	    return sb.toString();
	}
}
