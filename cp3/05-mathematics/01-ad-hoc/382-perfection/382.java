import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    System.out.println("PERFECTION OUTPUT");
    while(true){
      int nextInt = sc.nextInt();
      if (nextInt == 0) break;
      int sum = nextInt > 1 ? 1 : 0;
      int i = 2;
      for(; i*i <= nextInt; ++i){
        if (nextInt%i == 0){
          sum += i + nextInt/i;
        }
      }
      if (i*i == nextInt) sum -= i;
      System.out.print(String.format("%5d  ", nextInt));
      if (sum < nextInt){
        System.out.println("DEFICIENT");
      } else if (sum > nextInt){
        System.out.println("ABUNDANT");
      } else {
        System.out.println("PERFECT");
      }
    }
    System.out.println("END OF OUTPUT");
  }
}
