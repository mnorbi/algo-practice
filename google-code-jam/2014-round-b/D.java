import java.util.*;
import java.io.*;	
/*
  dp[a][b] = sfx s.t. a<=b
*/
public class D {
	private static long LIMIT = 1_000_000_000_000_000_000L;
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
	    int nn = 100;
	    long[][] dp = new long[nn+1][nn+1];
	    Arrays.fill(dp[0], 1);
	    for(int a = 1; a <= nn; ++a){
	    	for(int b = a; b <= nn; ++b){
	    		for(int c = 1; c <= b-a+1; ++c){
	    			dp[a][b] += dp[a-1][b-(c-1)];
				if (dp[a][b] > LIMIT) break;
	    		}
	    	}
	    }
            int t = Integer.valueOf(br.readLine());
            for (int i = 1; i <= t; ++i) {
		long[] arr = toLongArr(br.readLine());
		int n = (int)arr[0];
		long k = arr[1];
		StringBuilder sb = new StringBuilder();
		int a = (int)n, b = (int)n;
		if (k <= dp[n][n]){
		   while(a > 0){
		   	if (k > dp[a-1][b]){
		   		k -= dp[a-1][b];
		   		sb.append(')');
		   		--b;
		   	} else {
		   		sb.append('(');
		   		--a;
		   	}
		   }
		   if (sb.length() != 0){
		     while(b-->0){
		  	sb.append(')');
		     }
		   }
		} else {
		   sb.append("Doesn't Exist!");
		}
                System.out.println("Case #" + i + ": "+sb.toString());
            }
        }
    }

    static long[] toLongArr(String s) {
        return Arrays.stream(s.split("\\s+")).mapToLong(Long::valueOf).toArray();
    }
    static int[] toIntArr(String s) {
        return Arrays.stream(s.split("\\s+")).mapToInt(Integer::valueOf).toArray();
    }
}

