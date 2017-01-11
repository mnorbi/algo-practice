import java.util.*;
class CombinationsToHundred{
  public static void main(String[]args){
    solve(new ArrayList<String>(Arrays.asList("+", "1")),1,2);
  }
  static void solve(List<String> tokens, int accum, int nextDigit){
    if (nextDigit > 9){
      if (accum == 100) { print(tokens); }
      return;
    }
    tokens.add("+"); tokens.add(""+nextDigit);
    solve(tokens, accum+nextDigit, nextDigit+1);
    removeLastFrom(tokens); removeLastFrom(tokens);

    tokens.add("-"); tokens.add(""+nextDigit);
    solve(tokens, accum-nextDigit, nextDigit+1);
    removeLastFrom(tokens); removeLastFrom(tokens);

    String last = removeLastFrom(tokens);
    int lastSign = getLastFrom(tokens).equals("+")?1:-1;
    tokens.add(last+nextDigit);
    solve(tokens, accum+lastSign*(Integer.valueOf(last+nextDigit)-Integer.valueOf(last)), nextDigit+1);
  }
  static <T> T removeLastFrom(List<T> list){
    T ans = getLastFrom(list);
    list.remove(list.size()-1);
    return ans;
  }
  static <T> T getLastFrom(List<T> list){
    return list.get(list.size()-1);
  }
  static void print(List<String> tokens){
    System.out.print(tokens.get(1));
    for(int a = 2; a < tokens.size(); ++a){
      System.out.print(" "+tokens.get(a));
    }
    System.out.println(" = 100");
  }
}
