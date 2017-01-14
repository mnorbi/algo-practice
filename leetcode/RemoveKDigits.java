import java.util.Deque;
import java.util.LinkedList;

class RemoveKDigits{
  public static void main(String[] args) {
    RemoveKDigits solver = new RemoveKDigits();
    System.out.println(solver.removeKDigits("10200300", 2));
    System.out.println(solver.removeKDigits("1432219", 3));
  }

  String removeKDigits(String s, int k) {
    if (s == null || s.length() <= k) return "0";
    Deque<Character> stack = new LinkedList<>();

    /*stack invariants:
       - is always non decreasing
       - must never contain the 0 element
    */
    int a = 0;
    while(a < s.length() && k > 0){
      char c = s.charAt(a);
      if (stack.isEmpty() || stack.peekLast() <= c){
        if (c != '0'){
          stack.addLast(c);
        }
        ++a;
      } else {
        stack.removeLast();
        --k;
      }
    }

    if (stack.isEmpty()) { while (a < s.length() && s.charAt(a) == '0') { ++a; } }

    StringBuilder sb = new StringBuilder();
    while(!stack.isEmpty()){ sb.append(stack.removeFirst()); }
    if (a < s.length()) { sb.append(s.substring(a)); }

    return sb.length() > 0 ? sb.toString() : "0";
  }
}
