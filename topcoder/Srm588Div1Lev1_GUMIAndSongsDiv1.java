/**

Problem Statement
    
Gumi loves singing. She knows N songs. The songs are numbered 0 through N-1. She now has some time and she would love to sing as many different songs as possible. You are given a int[] duration. For each i, duration[i] is the duration of song i in Gumi's time units. Gumi finds it difficult to sing songs with quite different tones consecutively. You are given a int[] tone with the following meaning: If Gumi wants to sing song y immediately after song x, she will need to spend |tone[x]-tone[y]| units of time resting between the two songs. (Here, || denotes absolute value.) You are also given an int T. Gumi has T units of time for singing. She can start singing any song she knows immediately at the beginning of this time interval. Compute the maximal number of different songs she can sing completely within the given time.
Definition
    
Class:
GUMIAndSongsDiv1
Method:
maxSongs
Parameters:
int[], int[], int
Returns:
int
Method signature:
int maxSongs(int[] duration, int[] tone, int T)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
duration and tone will each contain between 1 and 50 elements, inclusive.
-
duration and tone will contain the same number of elements.
-
Each element of duration will be between 1 and 100,000, inclusive.
-
Each element of tone will be between 1 and 100,000, inclusive.
-
T will be between 1 and 10,000,000, inclusive.
Examples
0)

    
{3, 5, 4, 11}
{2, 1, 3, 1}
17
Returns: 3
There are four songs. Two songs have tone 1 and their durations are 5 and 11, respectively. One song has tone 2 and its duration is 3. One song has tone 3 and its duration is 4. Gumi has 17 units of time to sing. It is impossible for Gumi to sing all four songs she knows within the given time: even without the breaks the total length of all songs exceeds 17. Here is one way how she can sing three songs: First, she sings song 0 in 3 units of time. Second, she waits for |2-3|=1 unit of time and then sings song 2 in 4 units of time. Finally, she waits for |3-1|=2 units of time and then sings song 1 in 5 units of time. The total time spent is 3+1+4+2+5 = 15 units of time.
1)

    
{100, 200, 300}
{1, 2, 3}
99
Returns: 0
In this case, T is so small that she can't sing at all.
2)

    
{1, 2, 3, 4}
{1, 1, 1, 1}
100
Returns: 4
There is plenty of time, so she can sing all of 4 songs.
3)

    
{9, 11, 13, 17}
{2, 1, 3, 4}
20
Returns: 1

4)

    
{87,21,20,73,97,57,12,80,86,97,98,85,41,12,89,15,41,17,68,37,21,1,9,65,4,
 67,38,91,46,82,7,98,21,70,99,41,21,65,11,1,8,12,77,62,52,69,56,33,98,97}
{88,27,89,2,96,32,4,93,89,50,58,70,15,48,31,2,27,20,31,3,23,86,69,12,59,
 61,85,67,77,34,29,3,75,42,50,37,56,45,51,68,89,17,4,47,9,14,29,59,43,3}
212
Returns: 12

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class GUMIAndSongsDiv1{
	public int maxSongs(int[] duration, int[] tone, int T){
		int maxSongs = 0;
		int N = duration.length;
		for(int i = 0; i < N; i++){
			int minTone = Integer.MAX_VALUE;
			int maxTone = Integer.MIN_VALUE;
			int durSum = 0, restTime = 0;
			for(int j = i; j < N && durSum <= T; j++){
				durSum += duration[j];
				minTone = Math.min(minTone, tone[j]);
				maxTone = Math.max(maxTone, tone[j]);
				restTime = j==i ? 0 : maxTone - minTone;
				if(durSum+restTime<T){
					maxSongs = Math.max(maxSongs,j-i+1);
				}
			}
		}
		return maxSongs;
	}
}
