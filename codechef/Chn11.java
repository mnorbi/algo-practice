import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Chn11{
    private static int PRIME = 1_000_000_007;
    private static int[] fact = new int[100_001];
    static {
        fact[1] = 1;
        for(int a = 2; a < fact.length; ++a){
            fact[a] = mul(a,fact[a-1]);
        }
    }

    public static void main(String[]args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            int T = parseInts(br.readLine())[0];
            while(T-->0){
                parseInts(br.readLine());
                int[] arr = parseInts(br.readLine());
                int optimizedAns = solveOptimized(arr);
                System.out.println(optimizedAns);
            }
        }
    }
    private static int solveOptimized(int[] arr){
      Map<Integer, Integer> tribeSizes = new HashMap<>();
      for(int tribe : arr){
          tribeSizes.merge(tribe, 1, Integer::sum);
      }

      NavigableMap<Integer, Integer> odds = new TreeMap<>();
      int oddsHeight = 0;
      NavigableMap<Integer, Integer> evens = new TreeMap<>();
      int evensHeight = 0;
      for(int size : tribeSizes.values()){
          if (size%2==1){
              odds.merge(size, 1, Integer::sum);
              oddsHeight++;
          } else {
              evens.merge(size, 1, Integer::sum);
              evensHeight++;
          }
      }

      int res = 1;
      res = mul(res, calc(odds, oddsHeight));
      res = mul(res, calc(evens, evensHeight));
      return res;
    }
    private static int calc(NavigableMap<Integer, Integer> map, int height){
        int res = 1;
        int widthSum = 0;
        while(!map.isEmpty()){
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            int width = entry.getKey()-widthSum;
            res = mul(res,pow(fact[height],width));
            height -= entry.getValue();
            widthSum += entry.getKey();
        }
        return res;
    }
    private static int pow(int a, int pow){
        int res = 1;
        int base = a;
        while(pow > 0){
            if ((pow&1)==1){
                res = mul(res,base);
            }
            pow >>= 1;
            base = mul(base, base);
        }
        return res;
    }
    private static int mul(int a, int b){
        long res = ((((long)a)%PRIME)*(((long)b)%PRIME))%PRIME;
        return (int)res;
    }
    private static int[] parseInts(String s){
        return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
    }
}
