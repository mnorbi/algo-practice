import java.util.*;
class Main{
  static int LIMIT = (int)1e9;
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    Sieve sieve = new Sieve((int)Math.ceil(Math.sqrt(LIMIT))+1);
    int tcc = sc.nextInt();
    while(--tcc>=0){
      for(int n = sc.nextInt()+1;;++n){
        if (isSmithNumber(n, sieve)){
          System.out.println(n);
          break;
        }
      }
    }
  }
  private static boolean isSmithNumber(int n, Sieve sieve){
    int digitSum = digitSumOf(n);
    int k = n;
    for(int prime = sieve.isPrime.nextSetBit(0); prime >= 0 && prime*prime <= k; prime = sieve.isPrime.nextSetBit(prime+1)){
      if (k%prime==0){
        int primeDigitSum = digitSumOf(prime);
        while(k%prime == 0){
          digitSum -= primeDigitSum;
          if (digitSum < 0) return false;
          k /= prime;
        }
      }
    }
    //remaining factor k must be prime or 1
    if (k == n) return false;
    if (k > 1){
      digitSum -= digitSumOf(k);
    }
    return digitSum == 0;
  }
  private static int digitSumOf(int n){
    int ans = 0;
    while(n > 0){
      ans += n%10;
      n /= 10;
    }
    return ans;
  }
}
class Sieve{
  final int sieveSize;
  final BitSet isPrime;
  Sieve(int sieveSize){
    this.sieveSize = sieveSize;
    isPrime = new BitSet(sieveSize);
    isPrime.flip(0, isPrime.size());
    isPrime.set(0, false);
    isPrime.set(1, false);
    filterMultiplesOf(2);
    filterMultiplesOf(3);
    for(int n = 6; n < sieveSize; n += 6){
      if (isPrime.get(n-1)){
        filterMultiplesOf(n-1);
      }
      if (isPrime.get(n+1)){
        filterMultiplesOf(n+1);
      }
    }
  }
  private void filterMultiplesOf(int prime){
    for(int n = prime*prime; n < sieveSize; n += prime){
      isPrime.set(n, false);
    }
  }
}
