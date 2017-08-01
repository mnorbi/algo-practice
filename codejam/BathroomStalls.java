import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Arrays.stream;

public class BathroomStalls{
    public static void main(String[]args) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int T = Integer.valueOf(br.readLine());
            for(int a = 1; a <= T; ++a){
                long[] arr = stream(br.readLine().split(" ")).mapToLong(Long::valueOf).toArray();
                long N = arr[0];
                long K = arr[1];
                TreeMap<Long, Long> cnts = new TreeMap<>();
                cnts.put(N, 1L);
                while(K > 0){
                    TreeMap<Long, Long> nextCnts = new TreeMap<>();
                    for(Map.Entry<Long, Long> e : cnts.descendingMap().entrySet()){
                        if (K > 0){
                            long cnt = Math.min(e.getValue(), K);
                            K -= cnt;
                            nextCnts.merge(e.getKey()/2,cnt,Long::sum);
                            nextCnts.merge(e.getKey()-e.getKey()/2-1,cnt,Long::sum);
                            if (cnt < e.getValue()){
                                nextCnts.merge(e.getKey(),e.getValue()-cnt,Long::sum);
                            }
                        } else {
                            nextCnts.merge(e.getKey(),e.getValue(),Long::sum);
                        }
                    }
                    cnts = nextCnts;
                }
                System.out.printf("Case #%d: %d %d%n", a, cnts.lastKey(), cnts.firstKey());
            }
        }
    }
}

