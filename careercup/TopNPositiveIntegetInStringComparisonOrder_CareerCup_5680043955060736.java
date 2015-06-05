/**
Output top N positive integer in string comparison order. For example, let's say N=1000, then you need to output in string comparison order as below: 
1, 10, 100, 1000, 101, 102, ... 109, 11, 110, ...

**/
class TopNPositiveIntegetInStringComparisonOrder_CareerCup_5680043955060736{
  public static void main(String[]args){
    numbersInStringComparisonOrderIter(1000);
  }
  public static void numbersInStringComparisonOrder(int N){
    for(int i = 1; i <= 9; ++i){
      numbersInStringComparisonOrder(i, N);
    }
  }
  private static void numbersInStringComparisonOrder(int start, int N){
    if (start > N) return;
    System.out.println(start);
    for(int k = 0; k <= 9; ++k){
      numbersInStringComparisonOrder(start*10+k, N);
    }
  }
  private static void numbersInStringComparisonOrderIter(int N){
      int j = 1;
      do{
          while(j <= N){
            System.out.println(j);
            j = j*10;
          }
          j = j /10;
          for(j = j+1; j %10 != 0 && j <= N; ++j){
            System.out.println(j);
          }
          for(j = j/10; j%10 == 0; j=j/10);
          int tmp = j*10+1;
          if (tmp <= N){
            j = tmp;
          }
      } while(j != 1);
  }
}