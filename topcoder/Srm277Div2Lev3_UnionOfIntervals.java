import java.util.*;
/**

Problem Statement
    
Given a list of integers, find the n-th smallest number, i.e., the number that appears at index n (0-based) when they are sorted in non-descending order. The numbers will be given in intervals. For example, the intervals (1, 3) and (5, 7) represent the list of numbers { 1, 2, 3, 5, 6, 7 }. A number may be present in more than one interval, and it appears in the list once for each interval it is in. For example, the intervals (1, 4) and (3, 5) represent the list of numbers { 1, 2, 3, 3, 4, 4, 5 }.
The intervals will be given as two int[]s, lowerBound and upperBound. The i-th elements of these int[]s represent the smallest and largest numbers in the i-th interval, inclusive.
Definition
    
Class:
UnionOfIntervals
Method:
nthElement
Parameters:
int[], int[], int
Returns:
int
Method signature:
int nthElement(int[] lowerBound, int[] upperBound, int n)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
n is 0-based, meaning that the first element is indexed 0.
-
A sequence is sorted in non-descending order if and only if for each pair of indices i and j, where i is smaller than j, the element at position i is less than or equal to the element at position j.
Constraints
-
lowerBound will contain between 1 and 50 elements, inclusive.
-
upperBound will contain the same number of elements as lowerBound.
-
Each element of lowerBound and upperBound will be between -2,000,000,000 and 2,000,000,000, inclusive.
-
The i-th element of lowerBound will be less than or equal to the i-th element of upperBound.
-
n will be a non-negative integer less than the total number of elements in the list, but no greater than 2,000,000,000.
Examples
0)

    
{ 1, 5 }
{ 3, 7 }
4
Returns: 6
The numbers are 1, 2, 3, 5, 6 and 7. The number at index 4 is 6.
1)

    
{ 1, 3 }
{ 4, 5 }
3
Returns: 3

2)

    
{ -1500000000 }
{ 1500000000 }
1500000091
Returns: 91
Watch out for overflow errors.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class UnionOfIntervals{
    public int nthElement(final int[] lowerBound, final int[] upperBound, int n){
        Integer[] order = range(0, lowerBound.length);
        Arrays.sort(order, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return Integer.compare(lowerBound[a],lowerBound[b]);
            }
        });

        long lo = min(lowerBound);
        long hi = max(upperBound);

        while(lo < hi){
            long mid = ((long)lo+hi)/2;
            long id = lastIdx(mid, lowerBound, upperBound, order);
            if (id < n){
                lo = mid+1;
            } else {
                hi = mid;
            }
        }
        return (int)lo;
    }

    private long lastIdx(long target, int[] lowerBound, int[] upperBound, Integer[] order){
        long lastIdx = 0;
        for(int i = 0; i < order.length && lowerBound[order[i]] <= target; ++i){
            lastIdx += (long)Math.min(upperBound[order[i]], target) - (long)lowerBound[order[i]] + 1;
        }
        return lastIdx-1;
    }

    private Integer[] range(int lo, int hi){
        Integer[] ret = new Integer[hi-lo];
        for(int i = lo; i < hi; ++i){
            ret[i-lo] = i;
        }
        return ret;
    }
    private int min(int[]arr){
        int ret = arr[0];
        for(int i : arr){
            ret = Math.min(ret, i);
        }
        return ret;
    }
    private int max(int[]arr){
        int ret = arr[0];
        for(int i : arr){
            ret = Math.max(ret, i);
        }
        return ret;
    }
}
