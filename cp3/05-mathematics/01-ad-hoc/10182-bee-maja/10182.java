import java.util.*;
class Main{
  public static void main(String[]args){
    int[][] move = new int[][]{
      //move to new circle
      { 0, 1},//1 time
      //moves inside circle
      {-1, 1},//k     times
      {-1, 0},//k+1   times
      { 0,-1},//k+1   times
      { 1,-1},//k+1   times
      { 1, 0},//k+1   times
      { 0, 1} //k+1   times
    };
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextInt()){
      int n = sc.nextInt();
      int k = (int)((Math.sqrt(9+12*(n-1))-3)/6);
      int diff = n-(1+k*(k+1)*3);
      int[] ans = new int[2];
      ans[0] = k;
      ans[1] = 0;
      for(int moveId = 0; diff > 0; ++moveId){
        int moveCount;
        if (moveId == 0){
           moveCount = 1;
        } else if (moveId == 1){
           moveCount = Math.min(diff, k);
        } else {
           moveCount = Math.min(diff, k+1);
        }
        ans[0] += moveCount*move[moveId][0];
        ans[1] += moveCount*move[moveId][1];
        diff -= moveCount;
      }
      System.out.println(ans[0] + " " + ans[1]);
    }
  }
}
