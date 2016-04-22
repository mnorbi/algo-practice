/**
Each swap reduces the number of inversions by one. (inversion example [3,1,2], this has two inversions 3:1 and 3:2)
The bubble sort will remove all the inversions.

Minimum inversion for [1,2...n-1,n]: 0
Maximum inversion for [n-1,n...2,1]: (n-1)*n/2
Let E(n) = expected number of swaps
E(n) = sum(inversionCount = minInversionCount..maxInversionCount){inversionCount/allPossibleOrderingOf(nArray)}
  allPossibleOrderingOf(n) = factorial(n) = n!
E(n) = sum(inversionCount = minInversionCount..maxInversionCount){inversionCount/n!} = sumOfAllPossibleinversionsOnNLengthArray/n!

Let C(n) = sum of possible inversions in an n length, distinct valued array
E(n) = C(n)/n!
Let's take the increasing ordered n array and iterate over all elements i.
Take out the i'th element and put it at the end, we generate (n-i) number of inversions by doing this for each permutation
of a smaller (n-1) length array.

Recurrence relation:
  C(n) = sum(i=1..n){
          numberOfNewlyCreatedInversionByPuttingAtTheEndTheElement(i)*numberOfPossibleOrderingsOfDistinctValuedArrayOfLength(n-1) + C(n-1)
         }
       = sum(i=1..n){
          (n-i)*(n-1)! + C(n-1)
         }
       = n*C(n-1) + sum(i=1..n){ (n-i)*(n-1)! }
       = n*C(n-1) + (n-1)!*sum(i=1..n){ (n-i) }
       = n*C(n-1) + (n-1)!*( sum(i=1..n){n} - sum(i=1..n){i} )
       = n*C(n-1) + (n-1)!*( n*n            - n*(n+1)/2
       = n*C(n-1) + (n-1)!*n*( n-(n+1)/2 )
       = n*C(n-1) + (n-1)!*n*( (2n-n-1)/2)
       = n*C(n-1) + (n-1)!*n*(n-1)/2
Let's divide by n! to switch to E(n)
  C(n) - n*C(n-1) = (n-1)!*n*(n-1)/2 = n!*(n-1)/2
  C(n)/n! - C(n-1)/(n-1)! = (n-1)/2
  E(n)    - E(n-1)        = (n-1)/2

  sum(i=n..1){ E(i) - E(i-1) } = E(n)-E(n-1) + E(n-1)-E(n-2) + E(n-2)-E(n-3) ... = E(n) - E(1) = E(n)
                               = sum(i=1..n){ (i-1)/2 } = 1/2 * n*(n-1)/2 = n*(n-1)/4

E(n) = n*(n-1)/4
  

**/
import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    int tcc = sc.nextInt();
    for(int i = 1; i <= tcc; ++i){
      long n = sc.nextLong();
      long[] ans = new long[] { (n*(n-1)) >> 1, 2 };
      if(ans[0]%2 == 0){
        ans[0] >>= 1;
        ans[1] = 1;
      }
      
      print(i, ans);
    }
  }
  private static void print(int caseId, long[] ans){
    if (ans[1] == 1){
      System.out.println(String.format("Case %d: %d", caseId, ans[0]));
    } else {
      System.out.println(String.format("Case %d: %d/%d", caseId, ans[0], ans[1]));
    }
  }
}
