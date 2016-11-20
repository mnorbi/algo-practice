import java.util.*;
import java.io.*;
import java.util.stream.IntStream;
public class KthBest{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("k.in"));
      FileWriter fw = new FileWriter("k.out");
    ){
      int[] arr = nextArray(br.readLine());
      int N = arr[0], K = arr[1];
      double[][] vw = new double[N][2];
      int[] idx = IntStream.range(0, N).toArray();
      double lo = Double.MAX_VALUE, hi = Double.MIN_VALUE;
      for(int a = 0; a < N; ++a){
        double[] aux = Arrays.stream(nextArray(br.readLine())).mapToDouble(Double::valueOf).toArray();
        vw[a][0] = aux[0]; vw[a][1] = aux[1];
        idx[a] = a;
        lo = Math.min(lo, vw[a][0]/vw[a][1]);
        hi = Math.max(hi, vw[a][0]/vw[a][1]);
      }
      for(int cnt = 0; cnt < 200; ++cnt){
        double mid = lo+(hi-lo)/2;

        double[] f = new double[N];
        for(int a = 0; a < vw.length; ++a){
          f[a] = vw[a][0] - mid*vw[a][1];
        }

        quickselect(f, idx, K);

        double sum = 0.0;
        for(int a = 0; a < K; ++a){
          sum += f[idx[a]];
        }

        if (sum > 0.0){
          lo = mid;
        } else {
          hi = mid;
        }
      }
      fw.write(""+Math.round(idx[0]+1));
      for(int a = 1; a < K; ++a){
        fw.write(" "+Math.round(idx[a]+1));
      }
    }
  }
  static void quickselect(double[] cost, int[] idx, int K){
    if (idx.length <= K) return;
    int lo = 0, hi = idx.length-1;
    while(true){
      int a = lo, b = hi;
      double mid = cost[idx[a + (b-a)/2]];
      while(a<=b){
        while(cost[idx[a]] > mid) { ++a; }
        while(cost[idx[b]] < mid) { --b; }
        if (a <= b){
          int tmp = idx[a];
          idx[a] = idx[b];
          idx[b] = tmp;
          ++a; --b;
        }
      }
      if (a < K){
        lo = a;
      } else if (a > K){
        hi = b;
      } else {
        return;
      }
    }
  }
  static int[] nextArray(String s){
    return  Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
