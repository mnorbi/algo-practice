import java.util.*;
/**
1: 1
12: 2
123: 2
1234: 4
12345: 2
123456: 4
1234567: 6
12345678: 8
123456789: 2
12345678910: 4
1234567891011: 6
123456789101112: 8
12345678910111213: 10
1234567891011121314: 12
123456789101112131415: 14
12345678910111213141516: 16
**/
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(true){
      int N = sc.nextInt();
      if (N == 0) break;
      int ans = (N-Integer.highestOneBit(N))<<1;
      System.out.println(ans == 0 ? N : ans);
    }
  }
}
