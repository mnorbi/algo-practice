class Excel2 {
	public static void main(String[]args){
           System.out.println(convertToTitle(943566));
        }
 	static String convertToTitle(int a) {
	    StringBuilder sb = new StringBuilder();
	    int base = 26;
	    while(a != 0){
	        sb.append((char)((a-1)%base + 'A'));
	        a = (a-1) / base;
	    }
	    return sb.reverse().toString();
	}
}
