import java.util.*;
import java.io.*;
public class Postfix{
  public static void main(String[]args) throws IOException {
    try(BufferedReader in = new BufferedReader(new FileReader("postfix.in")); BufferedWriter out = new BufferedWriter(new FileWriter("postfix.out"));){
        String line = in.readLine();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int a = 0; a < line.length(); ++a){
          char c = line.charAt(a);
          if (isWhitespace(c)){
            continue;
          } else if (isDigit(c)){
            stack.push(valueOf(c));
          } else {
            int v2 = stack.pop();
            int v1 = stack.pop();
            int res = eval(c, v1, v2);
            stack.push(res);
          }
        }
        out.write(""+stack.pop());
        out.newLine();
    }
  }
  private static boolean isWhitespace(char c){
    return Character.isWhitespace(c);
  }
  private static boolean isDigit(char c){
    return Character.isDigit(c);
  }
  private static int eval(char op, int v1, int v2){
    if (op == '-') return v1-v2;
    if (op == '+') return v1+v2;
    if (op == '*') return v1*v2;
    throw new UnsupportedOperationException(""+op);
  }
  private static int valueOf(char c){
    return c-'0';
  }
}
