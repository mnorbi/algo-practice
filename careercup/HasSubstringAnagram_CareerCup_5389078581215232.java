import java.util.*;

/**
 Given two strings a and b, find whether any anagram of string a is a sub-string of string b. For eg:
 if a = xyz and b = afdgzyxksldfm then the program should return true.
 **/
class HasSubstringAnagram_CareerCup_5389078581215232{
    public static void main(String[]args){
        System.out.println(hasSubstringAnagram("xyz", "afdgzyxksldfm"));//true
        System.out.println(hasSubstringAnagram("xyz", "xxzyy"));//true
		System.out.println(hasSubstringAnagram("a", "cdsgsdgsa"));//true
        System.out.println(hasSubstringAnagram("abcdefg", "abcfedgsfdaf"));//true
		System.out.println(hasSubstringAnagram("abc", "ccccccabbbbbbb"));//true
        System.out.println(hasSubstringAnagram("xyz", "afdgzykxksldfm"));//false
        System.out.println(hasSubstringAnagram("xyz", "zxxxyyyz"));//false
        System.out.println(hasSubstringAnagram("xyz", "xxzzyy"));//false
    }
    public static  boolean hasSubstringAnagram(String pattern, String string){
        if (pattern == null || string == null) return false;
        if (string.length() < pattern.length()) return false;

        Map<Character, Integer> charMap = new HashMap<>();
        for(int i = 0; i < pattern.length(); ++i){
            charDelta(charMap, pattern.charAt(i), -1);
        }
        for(int i = 0; i < pattern.length(); ++i){
            charDelta(charMap, string.charAt(i), +1);
        }
        if (charMap.size() == 0){
            return true;
        }
        for(int i = 0, j = pattern.length()-1; j+1<string.length(); ++i, ++j){
            charDelta(charMap, string.charAt(i), -1);
            charDelta(charMap, string.charAt(j+1), +1);
            if (charMap.size() == 0) return true;
        }
        return false;
    }

    private static void charDelta(Map<Character, Integer> charMap, char c, int delta){
        int count = 0;
        if (charMap.containsKey(c)){
            count = charMap.get(c);
        }
        count = count + delta;
        if (count == 0){
            charMap.remove(c);
        } else {
            charMap.put(c, count);
        }
    }
}
