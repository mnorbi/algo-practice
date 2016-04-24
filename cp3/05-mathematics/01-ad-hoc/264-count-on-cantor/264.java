import java.util.*;
/**
  Need to figure out the biggest triangle size
  containing less or equal elements:
    k*(k+1)/2 = n
    hence k = floor(sqrt(1+8n)/2)
    Need to figure out the remaining elements after the triangle:
      diff = n-k*(k+1)/2
    
    note that all elements on the diagonal sum to the same value
    for diagonal k elements sum up to k+1

    if diff is zero, we are on the k-th diagonal (sum of terms is k+1)
    and this is the last element in the triangle so it takes the
    form of 1/k (if k is odd)  or k/1 (if k is even)
    
    if diff is not zero, the item is on the next diagonal, k+1th diagonal
    and elements summing up to k+2 it takes the form diff/((k+2)-diff) for
    odd (and swapped for even)
    
    

**/
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextInt()){
      int n = sc.nextInt();
      int k = (int)((Math.sqrt(1+8*n)-1)/2);
      int[] ans = new int[2];
      if (k*(k+1)/2 == n){
        ans[0] = 1;
        ans[1] = k;
      } else {
        int diff = n-k*(k+1)/2;
        ans[0] = diff;
        ans[1] = (k+2)-diff;
      }
      if (k%2 == 0) {
        int tmp = ans[0];
        ans[0] = ans[1];
        ans[1] = tmp;
      }
      System.out.println(String.format("TERM %d IS %d/%d", n, ans[0], ans[1]));
    }
  }
}
