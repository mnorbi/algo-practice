import java.util.*;

public class Question9_6{
	public static void main(String[]args){
		List<String> parens = genParens(4);
		print(parens);
	}
	private static void print(List<String> strings){
		for(String s: strings){
			System.out.println(s);
		}
	}

	private static List<String> genParens(int n){
		List<String>[] cache = new List[n+1];
		cache[0] = Collections.singletonList("");	
		return genParens(n, cache);
	}

	private static List<String> genParens(int n, List<String>[] cache){
		if (cache[n] != null) return cache[n];
		List<String> ret = new ArrayList<String>();
		for(int i = 0; i < n; ++i){
			List<String> parens = genParens(i);
			for(String s : parens){
				ret.add(genPrefix(n-1-i)+"()"+s+genSuffix(n-i-1));
			}
		}
		cache[n] = ret;
		return ret;
	}
	private static String genPrefix(int n){
		return repChar(n, '(');
	}
	private static String genSuffix(int n){
		return repChar(n, ')');
	}
	private static String repChar(int n, char c){
		char[] ret = new char[n];
		for(int i = 0; i < n; ++i){
			ret[i] = c;
		}
		return new String(ret);
	}
}
