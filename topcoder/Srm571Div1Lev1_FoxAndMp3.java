/**

Problem Statement
    
Fox Ciel loves music. She currently has n songs in her mp3 player. Their file names are "1.mp3", "2.mp3", and so on, until string(n)+".mp3".   Sadly, Ciel's mp3 player does not sort the files according to the number. Instead, it simply sorts the file names in lexicographic order, as strings. So, for instance, if n=10 then the sorted order looks as follows: "1.mp3", "10.mp3", "2.mp3", ..., "9.mp3".   You are given the int n. If n is at most 50, return a String[] containing the entire sorted list of file names. If n is more than 50, return a String[] containing the first 50 elements of the sorted list of file names.
Definition
    
Class:
FoxAndMp3
Method:
playList
Parameters:
int
Returns:
String[]
Method signature:
String[] playList(int n)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
The string A is lexicographically smaller than the string B if either of the following two conditions holds: 1. A is a proper prefix of B; 2. There is an index i such that the first (i-1) characters of A and B are equal, and character i of A has a smaller ASCII value than character i of B.
Constraints
-
n will be between 1 and 1,000,000,000, inclusive.
Examples
0)

    
3
Returns: {"1.mp3", "2.mp3", "3.mp3" }
We have 3 files: "1.mp3", "2.mp3", and "3.mp3". This is also their lexicographic order.
1)

    
10
Returns: 
{"1.mp3",
 "10.mp3",
 "2.mp3",
 "3.mp3",
 "4.mp3",
 "5.mp3",
 "6.mp3",
 "7.mp3",
 "8.mp3",
 "9.mp3" }
This is the example from the problem statement.
2)

    
16
Returns: 
{"1.mp3",
 "10.mp3",
 "11.mp3",
 "12.mp3",
 "13.mp3",
 "14.mp3",
 "15.mp3",
 "16.mp3",
 "2.mp3",
 "3.mp3",
 "4.mp3",
 "5.mp3",
 "6.mp3",
 "7.mp3",
 "8.mp3",
 "9.mp3" }

3)

    
32
Returns: 
{"1.mp3",
 "10.mp3",
 "11.mp3",
 "12.mp3",
 "13.mp3",
 "14.mp3",
 "15.mp3",
 "16.mp3",
 "17.mp3",
 "18.mp3",
 "19.mp3",
 "2.mp3",
 "20.mp3",
 "21.mp3",
 "22.mp3",
 "23.mp3",
 "24.mp3",
 "25.mp3",
 "26.mp3",
 "27.mp3",
 "28.mp3",
 "29.mp3",
 "3.mp3",
 "30.mp3",
 "31.mp3",
 "32.mp3",
 "4.mp3",
 "5.mp3",
 "6.mp3",
 "7.mp3",
 "8.mp3",
 "9.mp3" }

4)

    
100000009
Returns: 
{"1.mp3",
 "10.mp3",
 "100.mp3",
 "1000.mp3",
 "10000.mp3",
 "100000.mp3",
 "1000000.mp3",
 "10000000.mp3",
 "100000000.mp3",
 "100000001.mp3",
 "100000002.mp3",
 "100000003.mp3",
 "100000004.mp3",
 "100000005.mp3",
 "100000006.mp3",
 "100000007.mp3",
 "100000008.mp3",
 "100000009.mp3",
 "10000001.mp3",
 "10000002.mp3",
 "10000003.mp3",
 "10000004.mp3",
 "10000005.mp3",
 "10000006.mp3",
 "10000007.mp3",
 "10000008.mp3",
 "10000009.mp3",
 "1000001.mp3",
 "10000010.mp3",
 "10000011.mp3",
 "10000012.mp3",
 "10000013.mp3",
 "10000014.mp3",
 "10000015.mp3",
 "10000016.mp3",
 "10000017.mp3",
 "10000018.mp3",
 "10000019.mp3",
 "1000002.mp3",
 "10000020.mp3",
 "10000021.mp3",
 "10000022.mp3",
 "10000023.mp3",
 "10000024.mp3",
 "10000025.mp3",
 "10000026.mp3",
 "10000027.mp3",
 "10000028.mp3",
 "10000029.mp3",
 "1000003.mp3" }

5)

    
1
Returns: {"1.mp3" }

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class FoxAndMp3{
	static final int MAX = 50;
	
	public String[] playList(int n){
		String[] playList = new String[Math.min(n,MAX)];
		playListRec(1, n, 0, playList);
		return playList;
	}
	
	private int playListRec(int from, int to, int idx, String[] playList){
		if(from > to || idx >= playList.length){
			return idx;
		}
		playList[idx++] = String.format("%d.mp3", from);
		idx = playListRec(from*10, to, idx, playList);
		for(int i = 1; i <= 9 && idx < MAX; i++){
			idx = playListRec(from+i, to, idx, playList);
		}
		return idx;
	}
}
