/**

Problem Statement
    
The Centaur company has N servers, numbered 1 through N. These servers are currently connected into a network. The topology of the network is a tree. In other words, there are exactly N-1 bidirectional cables, each connecting some two servers in such a way that the entire network is connected.
 
The Centaur company is about to split into two new companies: the Human company and the Horse company. When this happens, the companies will divide the servers somehow. Once they divide their servers, they will cut each cable that connects a server of the Horse company and a server of the Human company.
 
While the Horse company has a lot of cables, the Human company does not have any. Therefore, when dividing the servers, the Human company must get a set of servers that will remain connected after the cables are cut.
 
You are given two int[]s a and b, each containing N-1 elements. These two arrays describe the original cables: for each i, there is a cable that connects the servers a[i] and b[i].
 
Compute and return the number of different ways in which the two companies may divide the servers. (It is possible that one of the companies will get no servers at all.)
Definition
    
Class:
CentaurCompanyDiv2
Method:
count
Parameters:
int[], int[]
Returns:
long
Method signature:
long count(int[] a, int[] b)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
N can be determined as (1 + the length of a).
Constraints
-
N will be between 2 and 51, inclusive.
-
a and b will contain exactly N-1 elements.
-
Each element of a and b will be between 1 and N, inclusive.
-
The network defined by a and b will be a tree (as explained in the problem statement).
Examples
0)

    
{1}
{2}
Returns: 4
There are 2^2 = 4 ways to divide the servers between two companies. For any division, the Human company's servers will remain connected.
1)

    
{2,2}
{1,3}
Returns: 7
There are 2^3 = 8 ways to divide the servers between two companies. However, if the Human company gets server 1 and server 3, and the Horse company gets server 2, the Human company's servers will not be connected. Therefore the number of valid ways is 8 - 1 = 7.
2)

    
{1,2,3,4,5,6,7,8,9}
{2,3,4,5,6,7,8,9,10}
Returns: 56

3)

    
{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
{2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51}
Returns: 1125899906842675
The answer overflows a 32-bit integer data type.
4)

    
{10, 7, 2, 5, 6, 2, 4, 9, 7}
{8, 10, 10, 4, 1, 6, 2, 2, 3}
Returns: 144

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class CentaurCompanyDiv2{
	public long count(int[] a, int[] b){
		int[][] adjMat = new int[a.length+1][a.length+1];
		for(int i = 0; i < a.length; i++){
			adjMat[a[i]-1][b[i]-1] = 1;
			adjMat[b[i]-1][a[i]-1] = 1;
		}
		long[][] memo = init(new long[2][a.length+1],Long.MIN_VALUE);
		return recCount(adjMat, -1, 0, 1, memo) + recCount(adjMat, -1, 0, 0, memo) + 1;
	}
	
	private long recCount(int[][] adjMat, int parentId, int rootId, int included, long[][] memo){
		if(memo[included][rootId] > -1) return memo[included][rootId];
		
		memo[1][rootId] = 1;
		memo[0][rootId] = 0;
		for(int i = 0; i < adjMat.length; i++){
			if (i != parentId && adjMat[rootId][i] > 0){
				memo[1][rootId] *= (recCount(adjMat, rootId, i, 1, memo) +1);
				memo[0][rootId] += (recCount(adjMat, rootId, i, 0, memo) + memo[1][i]);
			}
		}
		return memo[included][rootId];
	}
	
	private long[][] init(long[][] arr, long v){
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				arr[i][j] = v;
			}
		}
		return arr;
	}
}
