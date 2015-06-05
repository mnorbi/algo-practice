/**
Problem Statement
    
Consider a function randomInt(integer N) that takes an integer N and returns an integer uniformly at random in the range 0 to N-1. If that function is nested, as in randomInt(randomInt(N)), the probability distribution changes, and some numbers are more likely than others. Given an int nestings defining the number of times the function is nested (1 indicates randomInt(N), 2 indicates randomInt(randomInt(N)), and so on), an int N and an int target, return the probability that the result of nestings nested calls to randomInt with the parameter N will result in target.
Definition
    
Class:
NestedRandomness
Method:
probability
Parameters:
int, int, int
Returns:
double
Method signature:
double probability(int N, int nestings, int target)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
Calling randomInt(0) causes an error to occur, and hence can never cause an outcome of target.
-
Your return must have a relative or absolute error less than 1E-9.
Constraints
-
N will be between 1 and 1000, inclusive.
-
nestings will be between 1 and 10, inclusive.
-
target will be between 0 and N-nestings, inclusive.
Examples
0)

    
5
2
1
Returns: 0.21666666666666667
There are 3 ways of ending up with a 1 after calling randomInt(randomInt(5)). The inner call can result in a 4, 3, or a 2. Each of these possibilities occurs with a probability of 1/5. The probabilities of achieving a 1 with each of those results are 1/4, 1/3, and 1/2, respectively. This gives us a final probability of (1/5)*(1/4+1/3+1/2) = 13/60.
1)

    
10
4
0
Returns: 0.19942680776014104

2)

    
1000
10
990
Returns: 1.0461776397050886E-30
**/
class NestedRandomness{
	public double probability(int N, int nestings, int target){
		double[] arr = new double[N-target];
		double d = 1.0/N;
		for(int i = 0; i < arr.length; i++){
			arr[i] = d;
		}
		for(int level = 2; level <= nestings; level++){
			for(int i = 1; i <= arr.length - level + 1; i++){
				arr[i-1] = 0;
				for(int j = 0; j < i; j++){
					arr[j] += arr[i]/i;
				}
			}
		}
		return arr[0];
	}
}
