import java.util.*;
import java.io.*;
public class MergeSort{
    public static void main(String[]args) throws IOException{
        try(
                BufferedReader br = new BufferedReader(new FileReader("sort.in"));
                FileWriter fw = new FileWriter("sort.out");
        ){
            int n = Integer.valueOf(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            int[] tmp = new int[n];
            for(int L = 1, a = 0, b = L;b < n;L <<= 1, a += L, b += L){
                int c = a, e = a, d = b;
                for(;c < a+L && d < b+L && e < n;++e){
                    if (arr[c] <= arr[d]){
                        tmp[e] = arr[c++];
                    } else {
                        tmp[e] = arr[d++];
                    }
                }
                for(;c < a+L;++e, ++c){
                    tmp[e] = arr[c];
                }
                for(;e>=a;--e){
                    arr[e] = tmp[e];
                }
            }
            fw.write(Arrays.toString(arr));
        }
    }
}

