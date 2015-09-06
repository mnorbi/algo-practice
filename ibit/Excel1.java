/**
Given a column title as appears in an Excel sheet, return its corresponding column number.

Example:

    A -> 1
    
    B -> 2
    
    C -> 3
    
    ...
    
    Z -> 26
    
    AA -> 27
    
    AB -> 28 
**/
class Excel1 {
        public static void main(String args[]){
          System.out.println(titleToNumber("BAQTZ"));
        }
	static int titleToNumber(String a) {
	    int ret = 0;
	    int base = 26;
	    for(int i = 0; i < a.length(); ++i){
	        ret = ret*base + (a.charAt(i)-'A'+1);
	    }
	    return ret;
	}
}
