import java.util.*;
import java.io.*;
public class Testgen{
    public static void main(String[]args) throws IOException{
        try(
                BufferedReader in = new BufferedReader(new FileReader("testgen.in"));
                BufferedWriter out = new BufferedWriter(new FileWriter("testgen.out"));
        ){
            int k = Integer.valueOf(in.readLine());
            int primeCnt = (int)Math.floor(Math.log(k)/Math.log(2));
            long[] divCnts = new long[k+1];
            Arrays.fill(divCnts, 1);
            int[] powPlusOne = new int[k +1];
            BitSet primes = new BitSet(k+1);
            primes.set(2,k+1,true);
            int first = 2;
            long divCnt = 2L;
            for(int a = 2; primeCnt > 0; ++a){
                if (primes.get(a)){
                    powPlusOne[a] = 2;
                    divCnts[a] = 2;
                    --primeCnt;
                    for(int b = a+a; b <= k; b+=a){
                        primes.set(b,false);
                        powPlusOne[b] = ((b/a)%a != 0) ? 2 : (powPlusOne[b/a]+1);
                        divCnts[b] *= powPlusOne[b];
                        if (divCnt < divCnts[b] || divCnt == divCnts[b] && b < first){
                            divCnt = divCnts[b];
                            first = b;
                        }
                    }
                }
            }
            int maxTestCount = k+1-first;
            out.write(""+maxTestCount);
        }
    }
}
