/**

Problem Statement
    
You have implemented a sorting algorithm that requires exactly c*n*lg(n) nanoseconds to sort n integers. Here lg denotes the base-2 logarithm. Given time nanoseconds, return the largest double n such that c*n*lg(n) <= time.
Definition
    
Class:
SortEstimate
Method:
howMany
Parameters:
int, int
Returns:
double
Method signature:
double howMany(int c, int time)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
lg(n) = ln(n)/ln(2) where ln denotes the natural log.
-
Your return value must have a relative or absolute error less than 1e-9.
Constraints
-
c will be between 1 and 100 inclusive.
-
time will be between 1 and 2000000000 inclusive.
Examples
0)

    
1
8
Returns: 4.0
Given 8 nanoseconds we can sort 4 integers since
	1*4*lg(4) = 4*2 = 8
1)

    
2
16
Returns: 4.0
Now that c = 2 we need twice as many nanoseconds to sort 4 integers.
2)

    
37
12392342
Returns: 23104.999312341137
We can almost sort 23105 integers, but not quite.
3)

    
1
2000000000
Returns: 7.637495090348122E7
Largest possible return.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class SortEstimate{
	private static final double ERROR = 10E-9;
	//n [0..2*10**7]
	public double howMany(int c, int time){
		double lo = 0;
		double hi = 2*10E+07;
		double mid = lo;
		for(int i = 0; i < 100; i++){
			mid = lo + (hi-lo)/2;
			if (c*Math.log(mid)/Math.log(2) < time/mid){
				lo = mid;
			} else {
				hi = mid;
			}
		}
		return mid;
	}
	
}
