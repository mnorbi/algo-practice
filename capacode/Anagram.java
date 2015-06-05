import java.util.*;
class Anagram{
  public static void main(String[] args){
    TestCase[] testCases = new TestCase[]{
      new TestCase("","",true),
      new TestCase("","abc",false),
      new TestCase("abca","abc",false),
      new TestCase("abcab","aabcb",true),
      new TestCase("night","thing",true)
    };
    for(TestCase testCase: testCases){
      testCase.verify(isAnagramWithSort(testCase.s1,testCase.s2));
      testCase.verify(isAnagramWithHashMap(testCase.s1,testCase.s2));
    }
  }
  public static boolean isAnagramWithSort(String s1, String s2){
    if (s1 == s2) return true;
    if (s1 == null || s2 == null) return false;
    if (s1.length() != s2.length()) return false;
    char[] charr1 = s1.toCharArray();
    char[] charr2 = s2.toCharArray();
    Arrays.sort(charr1);
    Arrays.sort(charr2);
    for(int i = 0; i < charr1.length; ++i){
      if (charr1[i] != charr2[i]) return false;
    }
    return true;
  }

  public static boolean isAnagramWithHashMap(String s1, String s2){
     if (s1 == s2) return true;
     if (s1 == null || s2 == null) return false;
     if (s1.length() != s2.length()) return false;
    Map<Character, Integer> freqTable = new HashMap<Character, Integer>();
    for(int i = 0; i < s1.length(); ++i){
      char c = s1.charAt(i);
      Integer f = 0;
      if (freqTable.containsKey(c)){
        f = freqTable.get(c);
      }
      ++f;
      freqTable.put(c, f);
    }
    for(int i = 0; i < s2.length(); ++i){
      char c = s2.charAt(i);
      if (!freqTable.containsKey(c)){
        return false;
      }
      int f = freqTable.get(c);
      if (f == 1) {
        freqTable.remove(c);
      } else {
        freqTable.put(c, --f);
      }
    }
    return freqTable.size() == 0;
  }
}
class TestCase{
  String s1;
  String s2;
  boolean expected;
  TestCase(String s1,String s2, boolean expected){
    this.s1 = s1;
    this.s2 = s2;
    this.expected = expected;
  }
  void verify(boolean actual){
    assert expected == actual : String.format("testcase[%s],expected[%s],actual[%s]",this,expected,actual);
  }
  public String toString(){
    return String.format("%s,%s",s1,s2);
  }
}
