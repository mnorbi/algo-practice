import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ScarecrowSort{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("scarecrow.in"));
      FileWriter fw = new FileWriter("scarecrow.out");
    ){
      int[] arr = nextArray(br.readLine());
      int N = arr[0], K = arr[1];
      arr = nextArray(br.readLine());
//      while(true) {
//        int[] arr = ThreadLocalRandom.current().ints(10, 0, 100).toArray();
//        int K = ThreadLocalRandom.current().nextInt(1, 10);
//        int[] arr= new int[]{23, 12, 4, 98, 44, 72, 62, 19, 29, 52};
//        int K = 2;
//        System.out.println(Arrays.toString(arr));
//        System.out.println(K);
        for (int a = 0; a < Math.min(K,arr.length); ++a) {
          sort(arr, a, arr.length - 1, K);
        }

        String ans = "YES";
        for (int a = 1; a < arr.length; ++a) {
          if (arr[a - 1] > arr[a]) {
            ans = "NO";
            break;
          }
        }
//      }
      fw.write(ans);
    }
  }
  private static void sort(int[] arr, int lo, int hi, int jump){
    int cnt = (hi-lo)/jump+1;
    int a = lo, b = lo+(cnt-1)*jump;
    int key = arr[a+jump*((cnt-1)/2)];//arr[a+jump*ThreadLocalRandom.current().nextInt(0,cnt)];
    while(a <= b){
      while(arr[a] < key) { a+=jump; }
      while(arr[b] > key) { b-=jump; }
      if(a <= b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
        a+=jump;
        b-=jump;
      }
    }
    if (lo < b) { sort(arr,lo,b,jump); }
    if (hi > a) { sort(arr,a,hi,jump); }
  }
  private static int[] nextArray(String line){
    return Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
