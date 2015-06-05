import java.util.*;
public class Question179{
	public static void main(String[] args){
		String text = "some example test text containing some test words that should be searched for the pattern 'test'";
		String pattern = "test";
		Integer[] occurrences = findOccurrencesKmp(text, pattern);
		print(occurrences);
	}

	private static final Integer[] EMPTY = new Integer[0];

	private static Integer[] findOccurrences(String text, String pattern){
		if (text == null || pattern == null || "".equals(pattern)) return EMPTY;
		List<Integer> ret = new ArrayList<Integer>();
		int n = text.length();
		int m = pattern.length();
		for(int i = 0; i < n - m + 1; i++){
			int j = 0;
			for(;j < m && pattern.charAt(j) == text.charAt(i+j); j++);
			if (j == m) ret.add(i);
		}
		return ret.toArray(EMPTY);
	}

	private static void print(Integer[] occurrences){
		for(Integer i : occurrences){
			System.out.println(i);
		}
		System.out.println();
	}

	private static Integer[] findOccurrencesKmp(String text, String pattern){
		int i = 0, j = 0;
		int[] mbw = maxBorderWidth(pattern);
		List<Integer> matchIdxs = new ArrayList<Integer>();
		//while(text.length() - i > pattern.length() - j){
		while( i < text.length()){
			for(; j >= 0 && text.charAt(i) != pattern.charAt(j); j = mbw[j]);
			i++; j++;
			if (j == pattern.length()){
				matchIdxs.add(i-j);
				j = mbw[j];
			}
		}
		return matchIdxs.toArray(EMPTY);
	}

	private static int[] maxBorderWidth(String string){
		int[] mbw = new int[string.length()+1];
	
		int j = -1;
		int i = 0;	
		mbw[i] = j;
		while(i < string.length()){
			for(; j >= 0 && string.charAt(i) != string.charAt(j); j = mbw[j]);
			i++; j++;
			mbw[i] = j;
		}
		return mbw;
	}
}
