/**

Problem Statement
    
Rugs come in various sizes. In fact, we can find a rug with any integer width and length, except that no rugs have a distinct width and length that are both even integers. For example, we can find a 4x4 rug, but not a 2x4 rug. We want to know how many different choices we have for a given area.
Create a class RugSizes the contains a method rugCount that is given the desired area and returns the number of different ways in which we can choose a rug size that will cover that exact area. Do not count the same size twice -- a 6 x 9 rug and a 9 x 6 rug should be counted as one choice.
Definition
    
Class:
RugSizes
Method:
rugCount
Parameters:
int
Returns:
int
Method signature:
int rugCount(int area)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
area will be between 1 and 100,000, inclusive.
Examples
0)

    
4
Returns: 2
The choices are 1 x 4 (or equivalently 4 x 1) and 2 x 2.
1)

    
8
Returns: 1
Only 1 x 8 is available. Note that 2 x 4 has the desired area, but is not available since its width and length differ and are both even numbers.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class RugSizes{
	public int rugCount(int area){
		int ret = 0;
		for(int i = 1; i*i <= area; ++i){
			if (area%i == 0){
				int j = area/i;
				if (i%2 != 0 || j%2 != 0 || i == j) ++ret;
			}
		} 
		return ret;
	}
}
