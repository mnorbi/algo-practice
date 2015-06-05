/**
i18n (where 18 stands for the number of letters between the first i and the last n in the word “internationalization,”) Wiki it. 

Generate all such possible i18n strings for any given string. for eg. "careercup"=>"c7p","ca6p","c6up","car5p","ca5up","care4p","car4up","caree3p","care3up"..till the count is 0 which means its the complete string again.
**/
class I18N_Careercup_5733696185303040{
	public static void main(String[]args){
		gen("careercup");
	}
	static void gen(String s){
	  if (s == null) return;
	  if (s.length() < 3) {
	    System.out.println(s);
	    return;
	  }
	
	  for(int len = s.length()-2; len > 0; --len){
	    for(int i = 1, j = i+len; j < s.length(); ++i, ++j){
	      System.out.print(s.substring(0,i));
	      System.out.print(len);
	      System.out.println(s.substring(j));
	    }
	  }
	
	  System.out.println(s);
	}
	
}