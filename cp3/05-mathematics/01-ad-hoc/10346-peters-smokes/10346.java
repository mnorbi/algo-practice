import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextInt()){
      int N = sc.nextInt();
      int k = sc.nextInt();
      int sum = 0, r = 0;
      while(N > 0){
        sum += N;
        int NN = (N+r)/k;
        r = (N+r)%k;
        N = NN;
      }
      System.out.println(sum);
    }
  }
}
