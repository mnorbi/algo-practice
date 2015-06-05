import java.util.*;
class ReverseWordsInAString{
  public static void main(String[] args){
    System.out.println(reverseWords(""));
    System.out.println(reverseWords("    "));
    System.out.println(reverseWords("aaa"));
    System.out.println(reverseWords("coding"));
    System.out.println(reverseWords("coding is fun"));
    System.out.println(reverseWords("coding  is      fun"));
  }
  public static String reverseWords(String string){
    if (string == null) return null;
    char[] charr = string.toCharArray();
    reverse(charr, 0, charr.length);
    for(int i = 0; i < charr.length;){
      int j = nextWhitespaceOrEnd(charr, i);
      reverse(charr, i, j);
      i = nextNonWhitespaceOrEnd(charr, j);
    }
    return new String(charr);
  }
  static int nextNonWhitespaceOrEnd(char[] charr, int i){
    int k;
    for(k = i; k < charr.length; ++k){
      if (!isWhitespace(charr[k])) return k;
    }
    return k;
  }
  static int nextWhitespaceOrEnd(char[] charr, int i){
    int k;
    for(k = i; k < charr.length; ++k){
      if (isWhitespace(charr[k])) return k;
    }
    return k;
  }
  static void reverse(char[] charr, int from, int to){
    for(int i = from, j = to-1; i < j; ++i, --j){
      swap(charr, i, j);
    }
  }
  static boolean isWhitespace(char c){
    return Character.isWhitespace(c);
  }
  static void swap(char[] charr, int i, int j){
    char tmp = charr[i];
    charr[i] = charr[j];
    charr[j] = tmp;
  }
}
