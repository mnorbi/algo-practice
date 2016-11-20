import java.util.*;
import java.io.*;
public class KthOrderStatistics{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("kth.in"));
      FileWriter fw = new FileWriter("kth.out");
    ){
      int[] arr = nextIntArr(br.readLine());
      int n = arr[0], k1 = arr[1]-1, k2 = arr[2]-1;
      arr = generateArray(n, nextIntArr(br.readLine()));
      quickselect(arr,0,n-1,k1);
      if (k2 > k1){
        quickselect(arr,k1,n-1,k2);
      }
      quicksort(arr,k1,k2);
      
      fw.write(""+arr[k1]); 
      for(int a = k1+1; a <= k2; ++a){
        fw.write(" "+arr[a]);
      }
    }
  }
  private static void quickselect(int[]arr,int lo, int hi, int kth){
    while(lo < hi){
      int a = lo;
      int b = hi;
      int key = arr[(a+b)/2];
      while(a <= b){
        while(arr[a] < key) { ++a; }
        while(arr[b] > key) { --b; }
        if (a <= b){
          swap(arr,a++,b--);
        }
      }
      if (kth <= b){
        hi = b;
      } else if (kth > b){
        lo = a;
      }
    }
  }
  private static void quicksort(int[]arr,int lo, int hi){
    int a = lo;
    int b = hi;
    int key = arr[(a+b)/2];
    while(a<=b){
      while(arr[a] < key){ ++a; }
      while(arr[b] > key){ --b; }
      if (a <= b){
        swap(arr, a++, b--);
      }
    }
    if (lo < b) {quicksort(arr,lo,b);}
    if (a < hi) {quicksort(arr,a,hi);}
  }
  private static void swap(int[]arr,int a, int b){
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }
  private static int[] generateArray(int n, int[] in){
    int[] arr = new int[n];
    arr[0] = in[3]; --n;
    arr[1] = in[4]; --n;
    for(int a = 2; n-- > 0; ++a){
      arr[a] = in[0]*arr[a-2]+in[1]*arr[a-1]+in[2];
    }
    return arr;
  }
  private static int[] nextIntArr(String line){
    return Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
