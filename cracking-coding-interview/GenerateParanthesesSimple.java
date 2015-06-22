/*
(((())))
((()()))
(()(()))
()((()))
((())())
(()()())
()(()())
(())(())
()()(())
((()))()
(()())()
()(())()
(())()()
()()()()
14
*/
class GenerateParanthesesSimple{
  public static void main(String[]args){ 
    System.out.println(generate(4));
  }
  static int generate(int len){
    StringBuilder sb = new StringBuilder();
    sb.append(new char[2*len]);
    return generate(len, len, sb);
  }
  static int generate(int open, int close, StringBuilder sb){
    if (open == 0 && close == 0){
      System.out.println(sb.toString());
      return 1;
    }
    int count = 0;
    if (close > 0){
      sb.setCharAt(open+close-1, ')');
      count += generate(open, close-1, sb);
    }
    if (open > close){
      sb.setCharAt(open+close-1, '(');
      count += generate(open-1, close, sb);
    }
    return count;
  }
}
