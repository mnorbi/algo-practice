import java.util.*;
class BiggestNumberFromDigits{
  public static void main(String[]args){
    System.out.println(biggestNumberFrom(-909090));
  }
  static int biggestNumberFrom(int n){
    if (n == 0) return 0;
    int sign = n < 0 ? -1 : 1;
    n = n < 0 ? -n : n;

    int[] digits = new int[10];
    Arrays.fill(digits,0);
    while(n > 0){
      ++digits[n%10];
      n/=10;
    }

    int from = sign > 0 ? digits.length-1 : 1;
    int to = sign > 0 ? 0 : digits.length-1;
    int res = 0;
    for(int a = from; -sign*a <= to; a = a-sign){
      if (digits[a] > 0){
        --digits[a];
        res = a;
        from = sign > 0 ? from : 0;
        break;
      }
    }
    for(int a = from; -sign*a <= to; a = a-sign){
      while(digits[a] > 0){
        --digits[a];
        res *= 10;
        res += a;
      }
    }
    return sign*res;
  }
}
