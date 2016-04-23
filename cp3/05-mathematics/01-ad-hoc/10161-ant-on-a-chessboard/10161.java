import java.util.*;

class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(true){
      int n = sc.nextInt();
      if (n == 0) break;
      if (n == 1) {
        System.out.println("1 1");
        continue;
      }
      double sqrtN = Math.sqrt(n);
      long prev = (long)Math.floor(sqrtN);
      long next = (long)Math.ceil(sqrtN);
      long dP = n-prev*prev;
      long dN = next*next-n;

      int[] ans = new int[2];
      if (dN <= dP){
        ans[0] = (int)next;
        ans[1] = (int)dN+1;
      } else {
        ans[0] = (int)dP;
        ans[1] = (int)(prev+1);
      }
      if (next%2 == 1){
        int tmp = ans[0];
        ans[0] = ans[1];
        ans[1] = tmp;
      }
      System.out.println(ans[0] + " " + ans[1]);
    }
  }
}
