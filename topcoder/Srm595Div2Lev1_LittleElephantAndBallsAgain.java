/**

Problem Statement
    
Little Elephant from the Zoo of Lviv likes balls. He has some balls arranged in a row. Each of those balls has one of three possible colors: red, green, or blue.

You are given a String S. This string represents all the balls that are initially in the row (in the order from left to right). Red, green, and blue balls are represented by characters 'R', 'G', and 'B', respectively. In one turn Little Elephant can remove either the first ball in the row, or the last one.

Little Elephant wants to obtain a row in which all balls have the same color. Return the smallest number of turns in which this can be done.
Definition
    
Class:
LittleElephantAndBallsAgain
Method:
getNumber
Parameters:
String
Returns:
int
Method signature:
int getNumber(String S)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
S will contain between 1 and 50 characters, inclusive.
-
S will consist only of characters 'R', 'G' and 'B'.
Examples
0)

    
"RRGGBB"
Returns: 4
One possible optimal solution is to remove 2 balls from the front and 2 from the back. The total number of turns is 2+2 = 4. After those 4 turns only green balls remained on the table.
1)

    
"R"
Returns: 0
You don't need to do any turns in this case, so the answer is 0.
2)

    
"RGBRGB"
Returns: 5
In any optimal solution only one of these six balls will remain on the table.
3)

    
"RGGGBB"
Returns: 3

4)

    
"RGBRBRGRGRBBBGRBRBRGBGBBBGRGBBBBRGBGRRGGRRRGRBBBBR"
Returns: 46

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class LittleElephantAndBallsAgain{
	public int getNumber(String S){
		if (S == null || S.length() < 2){
			return 0;
		}
		int width = 0;
		String Sx = S + "x";
		for(int i = 0, j = 1; j < Sx.length(); j++){
			if (Sx.charAt(i) != Sx.charAt(j)){
				int l = j-i;
				if (l > width){
					width = l;
				}
				i = j;
			}
		}
		return S.length()-width;
	}
}
