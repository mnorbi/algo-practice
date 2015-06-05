import java.util.*;

public class CollegiateSemifinalRound3Div1Lev1{
	public static void main(String...args){
		ZigZag zigZag = new ZigZag();
		assert zigZag.longestZigZag(new int[] { 1, 7, 4, 9, 2, 5 }) == 6;
                assert zigZag.longestZigZag(new int[] { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 }) == 7;
                assert zigZag.longestZigZag(new int[] { 44 }) == 1;
                assert zigZag.longestZigZag(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }) == 2;
                assert zigZag.longestZigZag(new int[] { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }) == 8;
                assert zigZag.longestZigZag(new int[] { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
							600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
							67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
							477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
							249, 22, 176, 279, 23, 22, 617, 462, 459, 244 }) == 36;
	}
}
class ZigZag{
	public int longestZigZag(int[] sequence){
		if (sequence == null) return 0;
		int N = sequence.length;
		if (sequence.length < 3) return N;

		int[] ups = new int[N+1];
		int[] downs = new int[N+1];
		
		ups[0] = 1; downs[0] = 1;

		int max = 1;
		for(int i = 1; i < N; ++i){
			for(int k = 0; k < i; ++k){
				ups[i] = sequence[k] < sequence[i] ? Math.max(downs[k]+1, ups[i]) : ups[i];
				downs[i] = sequence[k] > sequence[i] ? Math.max(ups[k]+1, downs[i]) : downs[i];
			}
			max = Math.max(Math.max(max, downs[i]), ups[i]);
		}
		return max;
	}
}
