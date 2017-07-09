import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Chn11EditorialComparison {
    private static int PRIME = 1_000_000_007;
    private static int[] fact = new int[100_001];

    static {
        fact[1] = 1;
        for (int a = 2; a < fact.length; ++a) {
            fact[a] = mul(a, fact[a - 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        while(true){
        //try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
        String randomInput = generateRandomInput();
//        String randomInput = "3\n1 2\n1 2 2 1\n1 1 2\n";
        //System.out.println(randomInput);
        try (BufferedReader br = new BufferedReader(new StringReader(randomInput))) {
            int T = parseInts(br.readLine())[0];
            while (T-- > 0) {
                int[] arr = parseInts(br.readLine());
                int editorialAns = solveEditorial(arr);
                int optimizedAns = solveOptimized(arr);
                if (optimizedAns != editorialAns) {
                    System.out.println(Arrays.toString(arr));
                    System.out.printf("editorial: %d%n", editorialAns);
                    System.out.printf("optimized: %d%n", optimizedAns);
                    throw new RuntimeException();
                }
                System.out.println(optimizedAns);
            }
          }
        }
    }

    private static int solveEditorial(int[] arr){
      Map<Integer, Integer> fc = new HashMap<>();
      for(int a : arr){
        fc.merge(a, 1, Integer::sum);
      }
      int[] weight = new int[arr.length];
      Map<Integer, Integer> ocurrence = new HashMap<>();
      for(int a = 0; a < arr.length; ++a){
        ocurrence.merge(arr[a], 1, Integer::sum);
        weight[a] = 2*ocurrence.get(arr[a])-fc.get(arr[a])-1;
      }
      Map<Integer, Integer> weightCnt = new HashMap<>();
      for(int a : weight){
        weightCnt.merge(a, 1, Integer::sum);
      }
      int res = 1;
      for(int a : weightCnt.values()){
        res = mul(res, fact[a]);
      }
      return res;
    }
    private static int solveOptimized(int[] arr) {
        Map<Integer, Integer> tribeSizes = new HashMap<>();
        for (int tribe : arr) {
            tribeSizes.merge(tribe, 1, Integer::sum);
        }

        NavigableMap<Integer, Integer> odds = new TreeMap<>();
        int oddsHeight = 0;
        NavigableMap<Integer, Integer> evens = new TreeMap<>();
        int evensHeight = 0;
        for (int size : tribeSizes.values()) {
            if (size % 2 == 1) {
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

    private static int calc(NavigableMap<Integer, Integer> map, int height) {
        int res = 1;
        int widthSum = 0;
        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> entry = map.pollFirstEntry();
            int width = entry.getKey() - widthSum;
            res = mul(res, pow(fact[height], width));
            height -= entry.getValue();
            widthSum += entry.getKey();
        }
        return res;
    }

    private static int pow(int a, int pow) {
        int res = 1;
        int base = a;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res = mul(res, base);
            }
            pow >>= 1;
            base = mul(base, base);
        }
        return res;
    }

    private static int mul(int a, int b) {
        long res = ((((long) a) % PRIME) * (((long) b) % PRIME)) % PRIME;
        return (int) res;
    }

    private static int[] parseInts(String s) {
        return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
    }

    private static List<Integer> asList(int[] arr) {
        return IntStream.of(arr).boxed().collect(toList());
    }

    private static String generateRandomInput() {
        int maxLen = 10;
        int maxVal = 10;
	int testCases = 10;
        StringBuilder ans = new StringBuilder(""+testCases);
        for (int a = 0; a < testCases; ++a) {
            ans.append('\n');
            int len = 1 + random().nextInt(maxLen);
            for (int b = 0; b + 1 < len; ++b) {
                ans.append(random().nextInt(maxVal));
                ans.append(' ');
            }
            ans.append(random().nextInt(maxVal));
        }
        return ans.toString();
    }

    private static ThreadLocalRandom random() {
        return ThreadLocalRandom.current();
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}

