/**

Problem Statement
    
In this problem you will be given a String text and you will need to find the substring of the text that matches a given template in the best way. The template will be represented by two Strings: prefix and suffix. Consider a string S. The prefix match score of S with respect to a given template is the maximal n >= 0 such that the first n characters of S are equal to the last n characters of prefix and occur in the same exact order. Analogously, the suffix match score of S is the maximal m >= 0 such that the last m characters of S are equal to the first m characters of suffix and occur in the same exact order.
 
For example, if S = "something", prefix = "awesome", and suffix = "ingenious", than the prefix score of S is 4 (the matched characters are "some") and the suffix score is 3 (the matched characters are "ing").
 
The match score of a string S with respect to a given template is the sum of its prefix and suffix match scores. Find the non-empty substring of text with the maximal match score according to the template (prefix, suffix). In case of a tie, return the substring with the maximal prefix score. If there are still several candidates, return one that comes first lexicographically.
Definition
    
Class:
TemplateMatching
Method:
bestMatch
Parameters:
String, String, String
Returns:
String
Method signature:
String bestMatch(String text, String prefix, String suffix)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
String A comes before string B lexicographically if A is a proper prefix of B, or if A has a smaller character at the first position where the strings differ. When comparing the characters, refer to the following list of characters in ascending order: ' ', 'a', 'b', ..., 'z'.
Constraints
-
text will contain between 1 and 50 characters, inclusive.
-
prefix will contain between 1 and 50 characters, inclusive.
-
suffix will contain between 1 and 50 characters, inclusive.
-
text, prefix and suffix will contain only lowercase letters ('a'-'z') and spaces (' ').
Examples
0)

    
"something"
"awesome"
"ingenious"
Returns: "something"
The example from the problem statement.
1)

    
"havka"
"eto"
"papstvo"
Returns: "a"
The return value must be non-empty string, so the correct answer is "a".
2)

    
"abracadabra"
"habrahabr"
"bracket"
Returns: "abrac"

3)

    
"mississippi"
"promise"
"piccolo"
Returns: "ippi"

4)

    
"a a a a a a"
"a a"
"a"
Returns: "a a"

5)

    
"ab"
"b"
"a"
Returns: "b"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class TemplateMatching{
	public String bestMatch(String text, String prefix, String suffix){
		int matchPrefixScore = 0, matchSuffixScore = 0;
		int startIdx = 0, endIdx = 0;
		int n = text.length();
		
		for(int i = 0; i < n; i++){
			for(int j = i; j < n; j++){
				int prefixScore = prefixScore(text, i, j, prefix);
				int suffixScore = suffixScore(text, i, j, suffix);
				if (prefixScore + suffixScore >= matchPrefixScore + matchSuffixScore){
					if (prefixScore > matchPrefixScore || compare(text, startIdx, endIdx, text, i, j) > 0){
						matchPrefixScore = prefixScore;
						matchSuffixScore = suffixScore;
						startIdx = i;
						endIdx = j;
					}
				}
			}
		}
		
		return text.substring(startIdx, endIdx+1);
	}
	
	private int prefixScore(String text, int fr, int to, String prefix){
		int ret = 0;
		for(int i = to, j = prefix.length()-1; i >= fr && j >= 0 && text.charAt(i) == prefix.charAt(j); i--, j--, ret++);
		return ret;
	}
	
	private int suffixScore(String text, int fr, int to, String suffix){
		int ret = 0;
		for(int i = fr; i <= to && ret < suffix.length()-1 && text.charAt(i) == suffix.charAt(ret); i++, ret++);
		return ret;
	}
	
	private int compare(String text1, int s1, int e1, String text2, int s2, int e2){
		int i, j;
		for(i = s1, j = s2; i <= e1 && j <= e2; i++, j++){
			int cmp = Character.compare(text1.charAt(i), text2.charAt(j));
			if (cmp != 0) return cmp;
		}
		
		return Integer.compare(e1-s1, e2-s2);
	}
}
