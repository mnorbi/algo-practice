import java.util.*;
/*
(())
([])
({})
()()
[]()
{}()
[()]
[[]]
[{}]
()[]
[][]
{}[]
{()}
{[]}
{{}}
(){}
[]{}
{}{}
18

*/
class GenerateParanthesesComplex{
  public static void main(String[]args){
    System.out.println(generate(2));
  }
  static int generate(int len){
    char[][] pars = new char[][]{
      {'(',')'},
      {'[',']'},
      {'{','}'}
    };
    StringBuilder sb = new StringBuilder();
    sb.append(new char[2*len]);
    return generate(pars, len, len, new ArrayDeque<Character>(), sb);
  }
  static int generate(char[][] pars, int open, int close, Deque<Character> stack, StringBuilder sb){
    if (open == 0 && close == 0){
      System.out.println(sb.toString());
      return 1;
    }
    int count = 0;
    if (close > 0){
      for(int i = 0; i < pars.length; ++i){
        sb.setCharAt(open+close-1, pars[i][1]);
        stack.push(pars[i][0]);
        count += generate(pars, open, close-1, stack, sb);
        stack.pop();
      }
    }
    if (open > close){
      Character c = stack.pop();
      sb.setCharAt(open+close-1, c);
      count += generate(pars, open-1, close, stack, sb);
      stack.push(c);
    }
    return count;
  }
}
