import java.util.*;
import java.io.*;
public class Brackets{
  public static void main(String[]args) throws IOException {
    try(
      BufferedReader in = new BufferedReader(new FileReader("brackets.in"));
      BufferedWriter out = new BufferedWriter(new FileWriter("brackets.out"));
    ){
      while(true){
        String next = in.readLine();
        if (next == null || "".equals(next)) break;
        boolean isValid = true;
        Deque<Character> stack = new ArrayDeque<Character>();
        for(int a = 0; a < next.length(); ++a){
          Character c = next.charAt(a);
          if (isOpen(c)){
            stack.addLast(c);
          } else if (stack.isEmpty() || !Objects.equals(stack.removeLast(),openPairOf(c))){
            isValid = false;
            break;
          }
        }
        if (!stack.isEmpty()) isValid = false;
        out.write(isValid ? "YES" : "NO");
        out.newLine();
      } 
    }
  }
  private static boolean isOpen(Character c){
    return (c.equals('(') || c.equals('['));
  }
  private static Character openPairOf(Character c){
    if (c.equals(')')) return '(';
    if (c.equals(']')) return '[';
    return null;
  }
}
