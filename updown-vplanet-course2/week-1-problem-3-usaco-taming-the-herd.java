import java.util.*;
import java.io.*;
class Solution{
    public static void main(String[]args)throws Exception{
	try(BufferedWriter bw = new BufferedWriter(new FileWriter("taming.out"))){
        try(BufferedReader br = new BufferedReader(new FileReader("taming.in"))){
            int N = Integer.valueOf(br.readLine());
            int[] log = Arrays.stream(br.readLine().split("\\s+")).map(Integer::valueOf).mapToInt(Integer::intValue).toArray();
            int[][] oneBr = new int[N][N];
            int[] dp  = new int[N];
            for(int s = 0; s < N; ++s){
                int cost = 0;
                for(int d = 0; s+d < N; ++d){
                    cost += log[s+d] == d ? 0 : 1;
                    oneBr[s][s+d] = cost;
                    if (s == 0) dp[s+d] = cost;
                }
            }
            bw.write(""+dp[N-1]+"\n");
            for(int brk = 1; brk < N; ++brk){
                for(int end = N-1; end >= brk; --end){
                    dp[end] = 1_234_567_890;
                    for(int lastBr = end; lastBr >= brk; --lastBr){
                        dp[end] = Math.min(dp[end],oneBr[lastBr][end]+dp[lastBr-1]);
                    }
                }
                bw.write(""+dp[N-1]+"\n");
            }
        }
        }
    }
}
