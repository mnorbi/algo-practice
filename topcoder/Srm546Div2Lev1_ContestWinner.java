/**

Problem Statement
    
Exactly one million contestants, numbered 1 through 1,000,000, took part in a programming contest. The rules of the contest are simple: the winner is the contestant who solves the largest number of tasks. If there are more contestants tied for most tasks solved, the winner is the one who was the first to have all of their tasks solved.
During the contest the judges were keeping a log of all accepted solutions. You are given this log as a int[] events. The i-th element of events is the number of the contestant who submitted the i-th accepted solution (both indices are 0-based).
For example, if events = {4, 7, 4, 1}, this is what happened during the contest:
Contestant 4 solved her first task.
Contestant 7 solved his first task.
Contestant 4 solved her second task.
Contestant 1 solved his first task.
Compute and return the number of the contestant who won the contest.
Definition
    
Class:
ContestWinner
Method:
getWinner
Parameters:
int[]
Returns:
int
Method signature:
int getWinner(int[] events)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
events will contain between 1 and 50 elements, inclusive.
-
Each element of events will be between 1 and 1,000,000, inclusive.
Examples
0)

    
{4,7,4,1}
Returns: 4
Example from the problem statement.
1)

    
{10,20,30,40,50}
Returns: 10

2)

    
{123,123,456,456,456,123}
Returns: 456

3)

    
{1,2,2,3,3,3,4,4,4,4}
Returns: 4

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class ContestWinner{
	public int getWinner(int[] events){
		int[] solCount = new int[1000000+1];
		int winner = -1;
		int maxTask = -1;
		for(int userId : events){
			++solCount[userId];
			if (solCount[userId] > maxTask){
				maxTask = solCount[userId];
				winner = userId;
			}
		}
		return winner;
	}
}
