/**

Problem Statement
    
When purchasing a new home, the purchasers often take out a loan to pay for it. In this problem, we will be considering loans with the following terms:
At the beginning of each month, the purchasers pay a fixed amount towards settling the loan, which decreases the amount they owe.
At the end of the month, the amount the purchasers owe increases due to interest. Each month, 1/12 of the annual interest rate is added to the amount owed. Hence, if the annual interest rate is 12%, then the debt increases by 1% each month. You may assume that the amount owed after adding interest is always rounded up to the nearest dollar greater than or equal to the actual value.
Your task is, given the annual interest rate in tenths of a percent, the original amount of the loan, and the period over which the loan is to be repaid, calculate the minimum integral monthly payment so that the loan is repaid in term years or less. All monetary units are in dollars.  For example, if loan = 1000, interest = 50, and term = 1, then the loan is for $1000, to be paid back in one year, at an annual interest rate of 5%, or (5/12)% per month. If the purchasers pay back $86 every month, then the total amount owed will be as follows after each month:
month | after making payment | after interest accrues
------+----------------------+------------------------------------
 1    | 1000 - 86 = 914      | ceiling(914 * (1 + 5/12/100)) = 918
 2    | 918 - 86  = 832      | ceiling(832 * (1 + 5/12/100)) = 836
 3    | 836 - 86  = 750      | 754
 4    | 754 - 86  = 668      | 671
 5    | 671 - 86  = 585      | 588
 6    | 588 - 86  = 502      | 505
 7    | 505 - 86  = 419      | 421
 8    | 421 - 86  = 335      | 337
 9    | 337 - 86  = 251      | 253
 10   | 253 - 86  = 167      | 168
 11   | 168 - 86  = 82       | 83
 12   | 86 is more than enough to pay off the rest 
Clearly, 85 a month wouldn't be enough, since we just barely paid off the loan at 86.
Definition
    
Class:
Mortgage
Method:
monthlyPayment
Parameters:
int, int, int
Returns:
int
Method signature:
int monthlyPayment(int loan, int interest, int term)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
loan will be between 100 and 2,000,000,000, inclusive.
-
interest will be between 1 and 1,000,000, inclusive.
-
term will be between 1 and 1000, inclusive.
Examples
0)

    
1000
50
1
Returns: 86
From the problem statement.
1)

    
2000000000
6000
1
Returns: 671844808
interest = 6000 means that the monthly interest is a whopping 50%!
2)

    
1000000
1000000
1000
Returns: 988143
The interest is so high that even if we had 1000 years to pay back the loan, we'd still have to pay almost a million dollars a month.
3)

    
1000000
129
30
Returns: 10868

4)

    
1999999999
1000000
1
Returns: 1976284585

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class Mortgage{
	public int monthlyPayment(int loan, int interest, int term){
		long lo = 0;
		long hi = Integer.MAX_VALUE;
		long start = loan;
		while(lo<hi){
			long mid = (hi+lo)/2;

			long owed = loan;
			for(int i = 0; i < 12*term && owed > 0; i++){
				owed -= mid;
				owed += accrue(owed, interest);
				if (owed >= start) break;
			}
			
			if (owed > 0) lo = mid+1;
			else hi = mid;
		}
		
		return (int)lo;
	}

	private long accrue(long owed, long interest){
		long accrue = owed*interest;
		accrue = accrue/12000 + ((accrue%12000 > 0) ? 1 : 0);
		return accrue;
	}
	
}
