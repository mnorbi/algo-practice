import java.util.*;
class PascalTriangleKthRow{
        public static void main(String[]args){
           System.out.println(getRow(3));
        }
	static ArrayList<Integer> getRow(int a) {
	    int[][] dp = new int[2][a+2];
	    dp[0][0]=1;
	    dp[1][0]=1;
	    dp[1][1]=1;
	    for(int i = 2; i <= a; ++i){
	        dp[i%2][0] = 1;
	        dp[i%2][i] = 1;
	        for(int j = 1; j < i; ++j){
	            dp[i%2][j] = dp[(i-1)%2][j-1]+dp[(i-1)%2][j];
	        }
	    }
	    ArrayList<Integer> ret = new ArrayList<>();
	    for(int i = 0; i < a+1; ++i){
	        ret.add(dp[a%2][i]);
	    }
	    return ret;
	}
}
