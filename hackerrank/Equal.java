import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Equal {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int tcc = stdin.nextInt();
        for (int tci = 0; tci < tcc; tci++) {
            int N = stdin.nextInt();
            int[] cnt = new int[1002];
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < N; ++j) {
                int i = stdin.nextInt()+2;
                ++cnt[i];
                min = Math.min(min,i);
            }
            System.out.println(solve(cnt, min));
        }
    }
    static int solve(int[] cnt, int min){
        int[] shift = new int[]{0,1,2};
        int[] ret = new int[]{0,0,0};
        
        for(int i = 0; i < cnt.length; ++i){
            if (cnt[i] != 0){
                for(int j = 0; j < shift.length; ++j){
                    ret[j] += cnt[i]*cost(min-shift[j], i);
                }
            }
        }
        return min(ret);
    }
    static int min(int[]arr){
      int ret = arr[0];
      for(int i : arr){
        ret = Math.min(ret, i);
      }
      return ret;
    }
    static int cost(int lo, int hi){
        int d = hi-lo;
        int ret = d/5;
        d %= 5;
        ret += d/2;
        d %= 2;
        ret += d;
        return ret;
    }
}
