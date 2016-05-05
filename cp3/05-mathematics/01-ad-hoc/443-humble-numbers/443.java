import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    LinkedList<Integer> list = new LinkedList<>();
    int maxTarget = Integer.MIN_VALUE;
    while(true){
      int next = sc.nextInt();
      if (next == 0) break;
      list.add(next);
      maxTarget = Math.max(maxTarget, next);
    }
    int[] targets = list.stream().mapToInt(Integer::valueOf).toArray();
    int[] digits = new int[] {2, 3, 5, 7};
    int[] cursor = new int[] {0, 0, 0, 0};
    int[] generated = new int[maxTarget];
    generated[0] = 1;
    for(int r = 1; r < generated.length; ++r){
      int next = Integer.MAX_VALUE;
      for(int i = 0; i < digits.length; ++i){
        next = Math.min(next, digits[i]*generated[cursor[i]]);
      }
      generated[r] = next;
      for(int i = 0; i < digits.length; ++i){
        if (next == digits[i]*generated[cursor[i]]){
          cursor[i]++;
        }
      }
    }
    for(int i = 0; i < targets.length; ++i){
      print(targets[i], generated[targets[i]-1]);
    }
  }
  private static int[][] ending = { { 1, 11}, { 2, 12}, { 3, 13} };
  private static String[] otherSfx = { "st",     "nd",     "rd"};
  private static void print(int idx, int val){
    String sfx = "th";
    for(int i = 0; i < ending.length; ++i){
      if (idx % 10 == ending[i][0]){
        if (idx % 100 == ending[i][1]) break;
        sfx = otherSfx[i]; 
        break;
      }
    }
    System.out.println(String.format("The %d%s humble number is %d.", idx, sfx, val));
  }
}
