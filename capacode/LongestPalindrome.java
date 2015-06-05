mport java.util.*;
class LongestPalindrome{
  public static void main(String[]args){
    System.out.println(longestPalindrome(""));
    System.out.println(longestPalindrome("capacode"));
    System.out.println(longestPalindrome("a"));
    System.out.println(longestPalindrome("123"));
    System.out.println(longestPalindrome("baac"));
  }
  public static int longestPalindrome(String s){
    if (s == null || s.length() == 0) return 0;
    int max = 0;
    for(int i = 0; i < s.length(); ++i){
      int size = palindromeSizeWithCenter(i, i, s);
      max = Math.max(max, size);
      size = palindromeSizeWithCenter(i, i+1, s);
      max = Math.max(max, size);
    }
    return max;
  }
  static int palindromeSizeWithCenter(int i, int j, String s){
    for(;i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j); --i, ++j);
    ++i; --j;
    return j-i+1;
  }
}
