import java.util.*;
import java.io.*;
import java.math.*;
class Main{
    private static BigInteger[] X_ARR = new BigInteger[37];
    private static BigInteger[] Y_ARR = new BigInteger[37];
    public static void main(String[]args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            String next = in.readLine();
            if (next == null || "".equals(next)) break;
            String[] tokens = next.split(" ");
            String X;
            String Y;
            {
              int a = 0;
              for(;tokens[a].equals("");++a);X = tokens[a++];
              for(;tokens[a].equals("");++a);Y = tokens[a++];
            }
            Arrays.fill(X_ARR, null);
            Arrays.fill(Y_ARR, null);
            int xMinBase = minBase(X);
            int yMinBase = minBase(Y);
            for(int a = xMinBase; a < 37; ++a){
                X_ARR[a] = new BigInteger(X, a);
            }
            for(int a = yMinBase; a < 37; ++a){
                Y_ARR[a] = new BigInteger(Y, a);
            }
            Pair bases = searchForBases(X_ARR, xMinBase, 37, Y_ARR, yMinBase, 37);
            print(X, Y, bases, sb);
        }
        System.out.print(sb);
    }
    private static Pair searchForBases(BigInteger[] xArr, int xLo, int xHi, BigInteger[] yArr, int yLo, int yHi){
        boolean swap = false;
        if (xArr.length > yArr.length){
          swap = true;
          {BigInteger[] tmp = xArr; xArr = yArr; tmp = yArr;}
          {int tmp = xLo; xLo = yLo; yLo = tmp;}
          {int tmp = xHi; xHi = yHi; yHi = tmp;}
        }
        for(int a = xLo; a < xHi; ++a){
            int b = -1;
            int lo = yLo, hi = yHi-1;
            while(lo <= hi){
                int mid = lo + (hi-lo)/2;
                int cmp = yArr[mid].compareTo(xArr[a]);
                if (cmp > 0){
                    hi = mid-1;
                } else if (cmp < 0){
                    lo = mid+1;
                } else {
                    b = mid;
                    hi = mid-1;
                }
            }
            if (b >= 0) {
              return !swap ? new Pair(a, b) : new Pair(b, a);
            }
        }
        return null;
    }
    private static void print(String X, String Y, Pair bases, StringBuilder sb){
        if (bases != null){
            sb.append(X).append(" (base ").append(bases.a).append(") = ").append(Y).append(" (base ").append(bases.b).append(")");
        } else {
            sb.append(X).append(" is not equal to ").append(Y).append(" in any base 2..36");
        }
        sb.append('\n');
    }
    private static int minBase(String s){
        int minBase = 2;
        for(int a = 0; a < s.length(); ++a){
            char c = s.charAt(a);
            minBase = Math.max(minBase, digitOf(c)+1);
        }
        return minBase;
    }
    private static int digitOf(char c){
        if ('0' <= c && c <= '9'){
            return c - '0';
        } else {
            return c - 'A' + 10;
        }
    }
}
class Pair{
    int a, b;
    Pair(int a, int b){
        this.a = a;
        this.b = b;
    }
}
