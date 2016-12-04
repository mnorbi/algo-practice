import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public class DryingAnalysis {
  public static void main(String[] args) throws IOException {
    while(true) {
      int[] arr = ThreadLocalRandom.current().ints(4, 10, 100).map(Math::abs).toArray();
      int K = ThreadLocalRandom.current().nextInt(1, 10);
//      int[] arr = new int[] {9,8,5};
//      int K = 4;
      int expectedSteps = expectedSteps(arr, K);
      int actualSteps = actualSteps(arr, K);
      if (expectedSteps != actualSteps) {
        System.out.println("Arr:" + Arrays.toString(arr));
        System.out.println("K:" + K);
      }
    }
  }

  private static int actualSteps(int[] arr, int k) {
    int lo = 1, hi = 1000000000, best = -1;
    while(lo <= hi){
      int expectedTime = lo+(hi-lo)/2;
      int actualTime = 0;
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
    return best;
  }

  private static int expectedSteps(int[] arr, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    for (int v : arr) {
      pq.add(v);
    }
    int expectedSteps = 0;
    while (true) {
      if (pq.isEmpty()) break;
      int next = pq.remove();
      if (next <= expectedSteps) break;
      pq.add(next - k + 1);
      ++expectedSteps;
    }
    return expectedSteps;
  }
}
