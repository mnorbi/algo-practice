import java.util.*;

/**

Problem Statement
    
You own a lot of guitars, and each guitar has a unique serial number. You want to be able to look up serial numbers quickly, so you decide to sort the entire list as follows.
 
Each serial number consists of uppercase letters ('A' - 'Z') and digits ('0' - '9'). To see if serial number A comes before serial number B, use the following steps:
If A and B have a different length, the one with the shortest length comes first.
Else if sum_of_digits(A) differs from sum_of_digits(B) (where sum_of_digits(X) returns the sum of all digits in string X), the one with the lowest sum comes first.
Else compare them alphabetically, where digits come before letters.
Given a String[] serialNumbers, return a String[] with the ordered list of serial numbers in increasing order.
Definition
    
Class:
SerialNumbers
Method:
sortSerials
Parameters:
String[]
Returns:
String[]
Method signature:
String[] sortSerials(String[] serialNumbers)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
serialNumbers will contain between 1 and 50 elements, inclusive.
-
Each element of serialNumbers will contain between 1 and 50 characters, inclusive.
-
serialNumbers will only contain uppercase letters ('A' - 'Z') and digits ('0' - '9').
-
All elements of serialNumbers will be distinct.
Examples
0)

    
{"ABCD","145C","A","A910","Z321"}
Returns: {"A", "ABCD", "Z321", "145C", "A910" }
The first serial is "A" because it has the shortest length. All others have length 4, but "ABCD" has the lowest sum. Next lowest is "Z321", and finally "A910" comes before "145C" because "A" comes before the "1" (they both have sum = 10)
1)

    
{"Z19", "Z20"}
Returns: {"Z20", "Z19" }
1+9 > 2+0, so "Z20" comes first.
2)

    
{"34H2BJS6N","PIM12MD7RCOLWW09","PYF1J14TF","FIPJOTEA5"}
Returns: {"FIPJOTEA5", "PYF1J14TF", "34H2BJS6N", "PIM12MD7RCOLWW09" }

3)

    
{"ABCDE", "BCDEF", "ABCDA", "BAAAA", "ACAAA"}
Returns: {"ABCDA", "ABCDE", "ACAAA", "BAAAA", "BCDEF" }

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class SerialNumbers{
	private static class ComparatorImpl implements Comparator<String>{
		private static final Comparator<String> INSTANCE = new ComparatorImpl();
		
		public int compare(String s1, String s2){
			int ret = Integer.compare(s1.length(), s2.length());
			if (ret != 0) return ret;
	
			ret = Integer.compare(sumOfDigits(s1), sumOfDigits(s2));
			if (ret != 0) return ret;
			
			return s1.compareTo(s2);
		}
		
		private int sumOfDigits(String s){
			int ret = 0;
			for(int i = 0; i < s.length(); i++){
				if (s.charAt(i) >= '0' && s.charAt(i) <= '9') ret++;
			}
			return ret;
		}
	}
	
	public String[] sortSerials(String[] serialNumbers){
		Arrays.sort(serialNumbers, ComparatorImpl.INSTANCE);
		return serialNumbers;
	}
}
