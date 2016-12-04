import java.util.*;
import java.io.*;
public class WinTheCompetition{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader in = new BufferedReader(new FileReader("win.in"));
      BufferedWriter out = new BufferedWriter(new FileWriter("win.out"));
    ){
      int prCnt = Integer.valueOf(in.readLine());
      int[] durs = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
      int[] dp = new int[300*60+1];
      for(int pr = 0; pr < prCnt; ++pr){
        for(int dur = dp.length-1; dur >= durs[pr]; --dur){
          dp[dur] = Math.max(dp[dur], dp[dur-durs[pr]]+1);
        }
      }
      out.write(""+dp[dp.length-1]);
    }
  }
}
