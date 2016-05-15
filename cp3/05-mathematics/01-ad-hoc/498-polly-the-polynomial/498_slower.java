import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    while(sc.hasNextLine()){
      long[] cArr = toLongArray(sc.nextLine());
      long[] xArr = toLongArray(sc.nextLine());
      for(int x = 0; x < xArr.length; ++x){
        long ret = 0L;
        for(int c = 0; c < cArr.length; ++c){
          ret = ret*xArr[x]+cArr[c];
        }
        if (x > 0) sb.append(' ');
        sb.append(ret);
      }
      sb.append('\n');
    }
    System.out.print(sb);
  }
  private static long[] toLongArray(String s){
    return java.util.stream.Stream.of(s.trim().split("\\s+")).mapToLong(Long::parseLong).toArray();
  }
}
