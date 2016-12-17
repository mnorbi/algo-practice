import java.util.*;
import java.lang.*;
import java.io.*;
/*
Number Formation
dp      Goldman Sachs
Given three integers x,y and z you need to find the sum of all the numbers formed by 
having 4 atmost x times , having 5 atmost y times and having 6 atmost z times as a digit.

Note : Output the sum modulo 10^9+7.

Input
The first line of input contains of an integer 'T' denoting number of test cases. Then T test cases follow . The first line of each test case contains an three integers x , y and z .

Output:
For each test case print in a new line an integer corresponding to the answer.

Constraints:
1<=t<=50
0<=x,y,z<=100


Example:

Input:

1
1 1 1


Output:
3675


Explanation:
The ans for the input is 
4+5+6+45+54+56+65+46+64+456+465+546+564+645+654=3675
*/
class NumberFormation {
    private static int MOD = 1000000007;
    public static void main (String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            long[] inv = modInv(1, 101);
            int tc = Integer.valueOf(br.readLine());
            while(tc-->0){
                long ans = 0;
                int[] arr = toArray(br.readLine());
                long[][][] cntDp = new long[arr[0]+1][arr[1]+1][arr[2]+1];//count of same configuration with different ordering: (x+y+z)!/x!/y!/z!
                long[][][] dp = new long[arr[0]+1][arr[1]+1][arr[2]+1];//sum of values of same configuration with different ordering
                for(int x = 0; x <= arr[0]; ++x){
                    for(int y = 0; y <= arr[1]; ++y){
                        for(int z = 0; z <= arr[2]; ++z){
                            if (x == 0 && y == 0 && z == 0){
                                cntDp[x][y][z] = 1;
                                dp[x][y][z] = 0;
                                continue;
                            }
                            if (x > 0){
                                cntDp[x][y][z] = mul(mul((x+y+z),(cntDp[x-1][y][z])),inv[x]);
                                dp[x][y][z] = add(dp[x][y][z],add(mul(dp[x - 1][y][z], 10), mul(4, cntDp[x-1][y][z])));
                            }
                            if (y > 0){
                                cntDp[x][y][z] = mul(mul((x + y + z), (cntDp[x][y - 1][z])), inv[y]);
                                dp[x][y][z] = add(dp[x][y][z],add(mul(dp[x][y - 1][z], 10), mul(5, cntDp[x][y-1][z])));
                            }
                            if (z > 0){
                                cntDp[x][y][z] = mul(mul((x+y+z),(cntDp[x][y][z-1])),inv[z]);
                                dp[x][y][z] = add(dp[x][y][z], add(mul(dp[x][y][z - 1], 10), mul(6, cntDp[x][y][z-1])));
                            }
                            ans = add(ans,dp[x][y][z]);
                        }
                    }
                }
                System.out.println(ans);
            }
        }
    }

    private static long[] modInv(int lo, int hi) {
        long[] ans = new long[hi];
        for(int a = lo; a < hi; ++a) {
            ans[a] = modInv(a);
        }
        return ans;
    }
    private static long modInv(long b){
        long ans = 1;
        long exp = MOD-2;
        long base = b%MOD;
        while(exp > 0){
            if (exp%2 == 1){
                ans = mul(ans,base);
            }
            base = mul(base,base);
            exp >>= 1;
        }
        return ans;
    }
    private static long mul(long a, long b){
        return ((a%MOD)*(b%MOD))%MOD;
    }
    private static long add(long a, long b){
        return ((a%MOD)+(b%MOD))%MOD;
    }
    private static int[] toArray(String s){
        return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
    }
}
