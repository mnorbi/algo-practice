/**

Problem Statement
    
The greatest common divisor (GCD) of two positive integers a and b is the largest integer that evenly divides both a and b. In this problem, you will find the GCD of a positive integer and the factorial of a non-negative integer.
Create a class FactorialGCD with method factGCD which takes an int a and an int b as parameters and returns the GCD of a! (the factorial of a) and b.
Definition
    
Class:
FactorialGCD
Method:
factGCD
Parameters:
int, int
Returns:
int
Method signature:
int factGCD(int a, int b)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
Assume 0! = 1.
Constraints
-
a will be between 0 and 2147483647, inclusive.
-
b will be between 1 and 2147483647, inclusive.
Examples
0)

    
5
20
Returns: 20
5! = 120, so GCD(120,20) = 20.
1)

    
7
5040
Returns: 5040
7! = 5040, GCD(5040,5040) = 5040.
2)

    
0
2425
Returns: 1
Note that 0! = 1.
3)

    
667024
1
Returns: 1

4)

    
4
40
Returns: 8
4! = 24, so GCD(24,40) = 8.
5)

    
2097711064
2147483646
Returns: 2147483646

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class FactorialGCD{
	public int factGCD(int a, int b){
		if (b <= a) return b;
		if (a == 0) return 1;
		int ret = 1;
		for(int i = 2; i <= b && i <= a; i++){
			while(b%i == 0){
				ret *= i;
				b = b/i;
			}
		}
		return ret;
	}
}
