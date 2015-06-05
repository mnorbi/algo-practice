/**

Problem Statement
    
We say that the sequence of numbers {s[0], s[1], s[2], ..., s[N-1]} is sorted in ascending order if we have s[i] <= s[i+1] for each i between 0 and N-2, inclusive.
 
For example, the sequences {1,2,3}, {1,1,1}, and {2,2,4,4,6,6} are sorted in ascending order, but the sequences {1,2,1}, {7,4,6}, and {3,2,1} are not.
 
You are given the sequence of numbers as a int[] s. Your task is to order the elements of this sequence into a sequence that will NOT be sorted in ascending order. Find and return the lexicographically smallest sequence we can obtain. If there is no possibility to get a sequence that is not sorted in ascending order, return an empty int[] instead.
Definition
    
Class:
UnsortedSequence
Method:
getUnsorted
Parameters:
int[]
Returns:
int[]
Method signature:
int[] getUnsorted(int[] s)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
Sequence of numbers A is lexicographically smaller than B if A contains a smaller number on the first position on which they differ.
-
The sequence s may contain duplicates.
-
Note that the sequence consisting of only one element is always sorted.
Constraints
-
s will contain between 1 and 50 elements, inclusive.
-
Each element of s will be between 1 and 1000, inclusive.
Examples
0)

    
{1,2}
Returns: {2, 1 }
There is only one possible sequence that is not in ascending order: {2,1}.
1)

    
{1,2,3}
Returns: {1, 3, 2 }
Out of all six possible sequences, five are not in ascending order. Here they are, in lexicographic order:
{1,3,2}
{2,1,3}
{2,3,1}
{3,1,2}
{3,2,1}
2)

    
{7,2,2}
Returns: {2, 7, 2 }
Remember, that the given sequence may contain duplicates.
3)

    
{1000}
Returns: { }
As mentioned in the Notes section, the sequence consisting of only one element is always sorted. So there is no possibility to unsort this sequence.
4)

    
{1,1}
Returns: { }
There are also some longer sequences that cannot be unsorted.
5)

    
{1,2,4,3}
Returns: {1, 2, 4, 3 }

6)

    
{2,8,5,1,10,5,9,9,3,10,5,6,6,2,8,2,10}
Returns: {1, 2, 2, 2, 3, 5, 5, 5, 6, 6, 8, 8, 9, 10, 9, 10, 10 }

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class UnsortedSequence{
	public int[] getUnsorted(int[] s){
		if (s == null || s.length <= 1){
			return new int[0];
		}
		
		java.util.Arrays.sort(s);
		
		for(int i = s.length-1; i > 0; i--){
			if(s[i-1] != s[i]){
				int tmp = s[i-1];
				s[i-1] = s[i];
				s[i] = tmp;
				return s;
			}
		}
		
		return new int[0];
	}
}
