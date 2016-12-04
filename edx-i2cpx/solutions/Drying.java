import java.util.*;
import java.io.*;
public class Drying{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("drying.in"));
      FileWriter fw = new FileWriter("drying.out");
    ){
      int N = Integer.valueOf(br.readLine());
      int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
      int k = Integer.valueOf(br.readLine());
      int lo = 1, hi = 1000000000, best = -1;
      while(lo <= hi){
        int expectedTime = lo+(hi-lo)/2;
        long actualTime = 0;
        for(int a : arr){
          if (a > expectedTime){
            if (k > 1){
              int surplus = a-expectedTime;
              int rounds = (surplus/(k-1)) + ((surplus%(k-1)>0)?1:0);
              actualTime += rounds;
            } else {
              actualTime = Integer.MAX_VALUE;
              break;
            }
          }
        }
        if (actualTime > expectedTime){
          lo = expectedTime+1;
        } else {
          best = expectedTime;
          hi = expectedTime-1;
        }
      }
      fw.write(""+best); 
    }
  }
}
