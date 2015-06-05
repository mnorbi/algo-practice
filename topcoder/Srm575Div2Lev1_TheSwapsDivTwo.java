import java.util.*;
import java.math.*;

/**

Problem Statement
    
John has a sequence of integers. Brus is going to choose two different positions in John's sequence and swap the elements at those positions. (The two swapped elements may have the same value.) Return the number of different sequences Brus can obtain after he makes the swap.
Definition
    
Class:
TheSwapsDivTwo
Method:
find
Parameters:
int[]
Returns:
int
Method signature:
int find(int[] sequence)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
sequence will contain between 2 and 47 elements, inclusive.
-
Each element of sequence will be between 1 and 47, inclusive.
Examples
0)

    
{4, 7, 4}
Returns: 3
If Brus swaps elements 0 and 1 (0-based indices), the sequence will change to {7, 4, 4}. If he swaps elements 1 and 2 instead, the sequence will change to {4, 4, 7}. Finally, if the swaps elements 0 and 2, the sequence will remain {4, 7, 4}. These three outcomes are all distinct.
1)

    
{1, 47}
Returns: 1
Brus has to swap the only two elements, producing the sequence {47, 1}. Note that Brus has to make the swap, he cannot keep the original sequence.
2)

    
{9, 9, 9, 9}
Returns: 1
Regardless of which two elements Brus swaps, the resulting sequence will always be {9, 9, 9, 9}.
3)

    
{22, 16, 36, 35, 14, 9, 33, 6, 28, 12, 18, 14, 47, 46, 29, 22, 14, 17, 4, 15, 28, 6, 39, 24, 47, 37}
Returns: 319

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class TheSwapsDivTwo{
	public int find(int[] sequence){
		int N = sequence.length;
		int[] counts = counts(sequence);
		int possible = (N*(N-1))/2;
		int reps = 0;
		for(int i = 0; i < counts.length; i++){
			int x = counts[i];
			if (x > 1){
				reps += ((x*(x-1))/2);
			}
		}
		return possible - reps + (reps == 0 ? 0 : 1);
	}
	
	private int[] counts(int[] sequence){
		int[] counts = new int[48];
		for(int i = 0; i < sequence.length; i++){
			++counts[sequence[i]];
		}
		return counts;
	}
}
