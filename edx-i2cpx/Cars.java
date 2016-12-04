import java.io.*;
import java.util.*;
public class Cars {
    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new FileReader("cars.in"));
                FileWriter fw = new FileWriter("cars.out");
        ) {
            int[] aux = toArray(br.readLine());
            int N = aux[0];
            long[] arr = new long[N];
            int hi = 0, lo = 0;
            String s =  null;
            long sum = 0;
            long cnt = 0;
            TreeMap<Long, Integer> counts = new TreeMap<>();
            while(true){
                s = br.readLine();
                if (s == null) break;
                if (s.startsWith("+")){
                    long next = Long.parseLong(s.split(" ")[1]);

                    sum += next;
                    ++cnt;
                    arr[hi] = next;
                    ++hi;
                    if (!counts.containsKey(next)){
                        counts.put(next,0);
                    }
                    counts.put(next, counts.get(next)+1);
                }else if (s.startsWith("-")){
                    sum -= arr[lo];
                    counts.put(arr[lo], counts.get(arr[lo])-1);
                    --cnt;
                    ++lo;
                } else {
                    if (cnt == 0 || sum%cnt != 0 || !counts.containsKey(sum/cnt))  { fw.write("0\n"); continue;}
                    fw.write(""+counts.get(sum/cnt)+"\n");
                }
            }
        }
    }
    static int[] toArray (String s){
        return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
    }
}
