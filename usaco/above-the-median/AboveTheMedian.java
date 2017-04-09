import java.util.*;
import java.io.*;

public class AboveTheMedian{
  public static void main(String[]args) throws Exception {
    try(
      BufferedReader br = new BufferedReader(new FileReader(args[0]));
      BufferedWriter bw = new BufferedWriter(new FileWriter(args[0]+".out"));
    ){
      int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf);
      int N = arr[0], median = arr[1];
      arr = new int[N];
      for(int a = 0; a < N; ++a){
        arr[a] = Integer.valueOf(br.readLine()) >= median ? 1 : -1;
      }
      bw.write(count(arr, 0, N));
    }
  }
  long count(int[] arr, int idx, int len){
    if (len <= 0){ return 0; }
    if (len == 1){ return arr[idx] >= 0 ? 1 : 0; }
    int N = (len+1)/2;
    long ret = count(arr, idx, N) + count(arr, idx+N, len-N);
    FT ft = new FT(2*N+1);
    for(int a = idx+N-1, sum = 0; a >= idx; --a){
      sum += arr[a];
      ft.add(N+sum, 1);
    }
    for(int a = idx+N, sum = 0; a < idx+len; ++a){
      sum += arr[a];
      ret += N - ft.query(N-sum);
    }
    return ret;
  }
}
class FT{
  final int[] arr;
  FT(int N){
    arr = new int[N];
  }
  int add(int idx, int v){
    while(idx < N){ arr[idx] += v; idx |= idx+1; }
  }
  int query(int len){
    int sum = 0;
    while(len > 0){ sum += arr[len-1]; len &= len-1; }
    return sum;
  }
}
