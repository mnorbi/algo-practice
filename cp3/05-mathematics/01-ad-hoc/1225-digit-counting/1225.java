import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    int testCnt = sc.nextInt();
    for(int i = 1; i <= testCnt; ++i){
      int N = sc.nextInt();
      int[] digits = new int[10];
      for(int digit = 0; digit < 10; ++digit){
        digits[digit] = 0;
        for(int j = 1; N/j > 0; j*=10){
          int pfx = N/j/10;
          int sfx = N%j;
          int mid = (N-pfx*j*10)/j;

	  if (pfx == 0 && (digit > mid || digit == 0)) break;

          if (digit < mid){
            digits[digit] += pfx*j+(j-1)+1;
          } else if (digit > mid){
            if (pfx > 0){
              digits[digit] += (pfx-1)*j;
            }
            digits[digit] += (j-1)+1;
          } else {
            digits[digit] += pfx*j+sfx+1;
          }

          int zeroCorrection = (digit == 0) ? -j : 0;
	  digits[digit] += zeroCorrection;
        }
        if (digit > 0) System.out.print(" ");
        System.out.print(digits[digit]);
      }
      System.out.println();
    }
  }
}
