import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
public class Railway {
    public static void main(String[]args) throws IOException{
        try(
                BufferedReader br = new BufferedReader(new FileReader("railway.in"));
                FileWriter fw = new FileWriter("railway.out");
        ){
            int[] arr = toArray(br.readLine());
            int N = arr[0], M = arr[1];
            arr = toArray(br.readLine());
            int[] aux = new int[arr.length];
            boolean possible = true;
            for(int L = 1; L < N; L <<= 1){
                for(int lo = 0; lo < N-L; lo += L+L){
                    if (!merge(M, arr,aux,lo,lo+L,Math.min(N,lo+L+L))) {
                        possible = false;
                        break;
                    }
                }
            }
            if (possible) {
                fw.write("Yes");
            } else {
                fw.write("No");
            }
        }

    }
    private static boolean merge(int M, int[]arr,int[]aux,int lo,int mid,int hi) throws IOException{
        int a = lo, b = mid, t = lo;
        int rightMax = arr[hi-1];
        while(a<mid && b<hi){
            if (arr[a] <= arr[b]){
                aux[t++] = arr[a++];
            } else {
                if ((arr[a]+arr[b]) > M) return false;
                aux[t++] = arr[b++];
            }
        }
        if (a < mid && ((arr[mid-1] + rightMax) > M)) return false;
        while(a<mid){
            aux[t++] = arr[a++];
        }
        while(t-->lo){
            arr[t] = aux[t];
        }
        return true;
    }

    static int[] toArray (String s){
        return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
    }

}

