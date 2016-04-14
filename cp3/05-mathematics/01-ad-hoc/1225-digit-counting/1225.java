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
            digits[digit] += pfx*j+(j-1)+1;//concatenate prefix with sfx rounded up to 99...9, +1 from all zero values
          } else if (digit > mid){
            if (pfx > 0){
              digits[digit] += (pfx-1)*j;//concatenate decreased prefix
            }
            digits[digit] += (j-1)+1;//with 99...9 from suffix, +1 from all zero values
          } else {
            digits[digit] += pfx*j+sfx+1;//concatenate prefix and suffix, +1 from all zero values
          }

          int zeroCorrection = (digit == 0) ? -j : 0;//if digit==0 and prefix not yet active, the placed digit at j+1th does not yield valid number, because of overcounting previously we need to decrease the count with all the numbers from [0,99..9] which is actually j
	  digits[digit] += zeroCorrection;
        }
        if (digit > 0) System.out.print(" ");
        System.out.print(digits[digit]);
      }
      System.out.println();
    }
  }
}
