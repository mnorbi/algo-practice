import java.util.*;

/**

Problem Statement
    
A "deque" is a data structure which allows constant time insertion and removal at both the front and back ends. In this problem, you will be given a int[] data, and asked to sort the numbers contained therein using the following algorithm. For each number x in data, you must do exactly one of the following:
1. Push x onto the front end of an existing deque.
2. Push x onto the back end of an existing deque.
3. Create a new deque with x as its only element.
You must process each number in data in the order they are given. It is not permissible to skip a number temporarily and process it at a later time. It is also not permissible to insert a number into the middle of an existing deque; only front and back insertions are allowed. To make things easier, data will not contain duplicate elements.
Once all the numbers have been processed, if you have created your deques wisely, you should be able to create a single, sorted list by placing the resulting deques on top of each other in an order of your choice. Your method should return the minimum number of deques needed for this to be possible.
Definition
    
Class:
DequeSort
Method:
minDeques
Parameters:
int[]
Returns:
int
Method signature:
int minDeques(int[] data)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
data will contain between 3 and 50 elements, inclusive.
-
Each element of data will be between -1000 and 1000, inclusive.
-
data will not contain duplicate elements.
Examples
0)

    
{50, 45, 55, 60, 65,
 40, 70, 35, 30, 75}
Returns: 1
Only one deque is necessary to sort this list. The first element, 50, starts the deque. For each successive element encountered, if that element is less than 50, push it onto the front of the deque. Otherwise, if it is greater than 50, push it onto the back of the deque. Once all the data has been processed, the deque will contain all the elements and be in sorted order.
1)

    
{3, 6, 0, 9, 5, 4}
Returns: 2
Your algorithm could process the numbers in the following way:
Create a new deque d1 = {3}.
Create a new deque d2 = {6}.
Push 0 onto the front of d1; d1 = {0, 3}
Push 9 onto the back of d2; d2 = {6, 9}
Push 5 onto the front of d2; d2 = {5, 6, 9}
Push 4 onto the front of d2; d2 = {4, 5, 6, 9}
We can now combine the deques together by putting d2 after d1, resulting in the sorted list {0, 3, 4, 5, 6, 9}. Two deques were used (and it is impossible to succeed using any less than two), so the method returns 2.
2)

    
{0, 2, 1, 4, 3, 6, 5, 8, 7, 9}
Returns: 5
The five deques will be {0,1}, {2,3}, {4,5}, {6,7}, and {8,9}. It is impossible to use fewer than five deques and still be able to combine them into a single, sorted list at the end.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class DequeSort{
	public int minDeques(int[] data){
		List<Deque<Integer>> deqs = new ArrayList<>();
		for(int i = 0; i < data.length; i++){
			boolean placed = false;
			for(Deque<Integer> deque : deqs){
				if (isPlaceableBefore(data, data[i], deque.getFirst())){
					deque.addFirst(data[i]);
					placed = true;
				} else if (isPlaceableAfter(data, data[i], deque.getLast())){
					deque.addLast(data[i]);
					placed = true;
				}
			}
			if (!placed){
				Deque<Integer> newDeque = new ArrayDeque<>();
				newDeque.add(data[i]);
				deqs.add(newDeque);
			}
		}
		return deqs.size();
	}
	
	private boolean isPlaceableBefore(int[] data, int toPlace, int target){
		for(int i = 0; i < data.length; i++){
			if (between(toPlace, data[i], target)) return false;
		}
		return true;
	}
	
	private boolean isPlaceableAfter(int[] data, int toPlace, int target){
		for(int i = 0; i < data.length; i++){
			if (between(target, data[i], toPlace)) return false;
		}
		return true;
	}
	
	private boolean between(int a, int b, int c){
		return a < b && b < c;
	}
}
