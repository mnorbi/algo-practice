/**

Problem Statement
    
A palindrome is a String that is spelled the same forward and backwards. Given a String base that may or may not be a palindrome, we can always force base to be a palindrome by adding letters to it. For example, given the word "RACE", we could add the letters "CAR" to its back to get "RACECAR" (quotes for clarity only). However, we are not restricted to adding letters at the back. For example, we could also add the letters "ECA" to the front to get "ECARACE". In fact, we can add letters anywhere in the word, so we could also get "ERCACRE" by adding an 'E' at the beginning, a 'C' after the 'R', and another 'R' before the final 'E'. Your task is to make base into a palindrome by adding as few letters as possible and return the resulting String. When there is more than one palindrome of minimal length that can be made, return the lexicographically earliest (that is, the one that occurs first in alphabetical order).
Definition
    
Class:
ShortPalindromes
Method:
shortest
Parameters:
String
Returns:
String
Method signature:
String shortest(String base)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
base contains between 1 and 25 characters, inclusive.
-
Every character in base is an uppercase letter ('A'-'Z').
Examples
0)

    
"RACE"
Returns: "ECARACE"
To make "RACE" into a palindrome, we must add at least three letters. However, there are eight ways to do this by adding exactly three letters:
    "ECARACE"  "ECRARCE"  "ERACARE"  "ERCACRE"
    "RACECAR"  "RAECEAR"  "REACAER"  "RECACER"
Of these alternatives, "ECARACE" is the lexicographically earliest.
1)

    
"TOPCODER"
Returns: "REDTOCPCOTDER"

2)

    
"Q"
Returns: "Q"

3)

    
"MADAMIMADAM"
Returns: "MADAMIMADAM"

4)

    
"ALRCAGOEUAOEURGCOEUOOIGFA"
Returns: "AFLRCAGIOEOUAEOCEGRURGECOEAUOEOIGACRLFA"

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class ShortPalindromes{
	public String shortest(String base){
		if (base == null || "".equals(base) || base.length() == 1) return base;
		int N = base.length();
		String[][] dp = new String[N][N];
		for(int i = 0; i < N; i++){
			dp[i][i] = base.substring(i,i+1);
		}
		for(int delta = 1; delta < N; delta++){
			for(int i = 0; i<N-delta; i++){
				int j = i+delta;
				char sc = base.charAt(i);
				char ec = base.charAt(j);
				String p1 = "" + sc + dp[i+1][j] + sc;
				String p2 = "" + ec + dp[i][j-1] + ec;
				String p = smaller(p1,p2);
				if (sc == ec){
					String p3 = "" + sc + ((i+1<=j-1)?dp[i+1][j-1]:"") + ec;
					p = smaller(p,p3);
				}
				dp[i][i+delta] = p;
			}
		}
		return dp[0][N-1];
	}
	
	private String smaller(String s1, String s2){
		if (s1.length() < s2.length()) return s1;
		if (s2.length() < s1.length()) return s2;
		return s1.compareTo(s2) < 0 ? s1 : s2;
	}
}
