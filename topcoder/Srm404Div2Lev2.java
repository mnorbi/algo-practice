/**
	Solution diary
	--------------
		Messed up only the complement method calculation, mixed up the substraction order:
			expected: (b-'0')-(a-'0')
			wrong was:(a-'0')-(b-'0')
**/

public class Srm404Div2Lev2{
	public static void main(String... args){
		RevealTriangle solver = new RevealTriangle();
		print(solver.calcTriangle(new String[]{
			"4??",
			"?2",
			"1"
		}));
		print(solver.calcTriangle(new String[]{"1"}));
                print(solver.calcTriangle(new String[]{"???2", "??2", "?2", "2"}));
                print(solver.calcTriangle(new String[]{"??5?", "??9", "?4", "6"}));
	}
	public static void print(String[] stringArr){
		for(int i = 0; i < stringArr.length; i++){
			System.out.println(stringArr[i]);
		}
		System.out.println();
	}
}
class RevealTriangle{
	public String[] calcTriangle(String[] questionMarkTriangle){
		int N = questionMarkTriangle.length;
		if (N <= 1) return questionMarkTriangle;

		char[][] ret = new char[N][];
		ret[N-1] = new char[] { questionMarkTriangle[N-1].charAt(0) };
		for(int i = N-2; i >= 0; --i){
			ret[i] = new char[N-i];
			int j = nonQuestionMarkItemIdx(questionMarkTriangle[i]);
			ret[i][j] = questionMarkTriangle[i].charAt(j);
			for(int k = j-1; k >= 0; --k){
				ret[i][k] = complement(ret[i][k+1],ret[i+1][k]);
			}
			for(int k = j+1; k < ret[i].length; ++k){
				ret[i][k] = complement(ret[i][k-1],ret[i+1][k-1]);
			} 
		}
		return toStringArr(ret);
	}
	private int nonQuestionMarkItemIdx(String s){
		for(int i = 0; i < s.length(); i++){
			if (s.charAt(i) != '?') return i;
		}
		throw new IllegalArgumentException();
	}
	private char complement(char a, char b){
		char ret = (char)((((b-'0')-(a-'0')+ 10) % 10) + '0');
                //System.out.println(String.format("a[%s],b[%s]=c[%s]",a,b,ret));
		return ret;
	}
	private String[] toStringArr(char[][] charArr){
		String[] ret = new String[charArr.length];
		for(int i = 0; i < ret.length; i++){
			ret[i] = new String(charArr[i]);
		}
		return ret;
	}
}
