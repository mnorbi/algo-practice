import java.util.*;
class FirstUniqueCharIdx{
  public static void main(String[] args){
    System.out.println(firstUniqueCharIdx(""));
    System.out.println(firstUniqueCharIdx("aaa"));
    System.out.println(firstUniqueCharIdx("aabcdb"));
    System.out.println(firstUniqueCharIdx("aabb"));
    System.out.println(firstUniqueCharIdx("aa bb"));
    System.out.println(firstUniqueCharIdx("aabcdb"));
    System.out.println(firstUniqueCharIdx("abcdabcd"));
    System.out.println(firstUniqueCharIdx("abcdedcba"));
  }
  public static int firstUniqueCharIdx(String string){
    Set<Character> nonUnique = new HashSet<Character>();
    Map<Character, Integer> posMap = new LinkedHashMap<Character, Integer>();
    for(int i = 0; i < string.length(); ++i){
      char c = string.charAt(i);
      if (nonUnique.contains(c)) {
        continue;
      } else if (posMap.containsKey(c)) {
        nonUnique.add(c);
        posMap.remove(c);
        continue;
      } else {
        posMap.put(c, i);
      }
    }
    if (!posMap.isEmpty()){
    	return posMap.entrySet().iterator().next().getValue();
    }
    return -1;
  }
}
