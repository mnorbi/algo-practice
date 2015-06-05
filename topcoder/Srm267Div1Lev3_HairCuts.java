import java.util.*;

/**

Problem Statement
    
Our barbershop opens at 9:00 am and closes at 5:00 pm, but (of course) the barber keeps working until all the customers who entered the shop before 5:00 have been served. Each customer enters the shop and if the barber is free, immediately starts getting his hair cut. Otherwise, the customer waits until everyone who entered before him has finished getting their hair cut.
We have the sequence of times when customers entered the shop and we know when the last customer left the shop. We also know that each haircut took at least 5 minutes. We want to get a bound on how long the longest haircut took.
Create a class HairCuts that contains a method maxCut that is given String[] enter and String lastExit and that returns the smallest possible time (in minutes) that the longest haircut took. If the inputs are inconsistent with the rule that each haircut takes at least 5 minutes, return -1.
The enter times and the lastExit are 5 character Strings in the format hh:mm with hh being a 12 hour time.
Definition
    
Class:
HairCuts
Method:
maxCut
Parameters:
String[], String
Returns:
double
Method signature:
double maxCut(String[] enter, String lastExit)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
The returned value must be accurate to within a relative or absolute value of 1E-9.
Constraints
-
lastExit and all elements of enter are formatted as described in the statement.
-
enter contains between 1 and 50 elements, inclusive.
-
Each hh in enter will equal "09","10","11","12","01","02","03", or "04.
-
The hh in lastExit will equal "09","10","11","12","01",...,"05", or "06".
-
Each time will have mm equal to "00","01",...,"58", or "59".
-
lastExit will be a time that is later than every enter time.
Examples
0)

    
{"04:22","09:00"}
"05:52"
Returns: 90.0
At 9:00 the first customer entered. The last (second) customer entered at 4:22 and was not done until 5:52. For the first customer to have delayed the second customer his cut would have to have taken more than 7 hours. So the smallest time for the longest haircut occurs when the first customer has a short cut, and the second customer is getting his hair cut all the time he is in the shop (from 4:22 to 5:52) which is 90 minutes.
1)

    
{"09:00","09:22","09:22"}
"10:11"
Returns: 23.666666666666863
If the first customer's cut takes 23 2/3 minute then the second and third customers are already waiting. They could both take the same amount of time. Since this keeps the barber busy all the time with 3 equal length haircuts, this must be the smallest time for the longest haircut.
2)

    
{"09:00","04:00","04:02"}
"04:09"
Returns: -1.0
This data would require two customers to get haircuts between 4:00 and 4:09 and that would violate the rule that each haircut takes at least 5 minutes.
3)

    
{  "04:40", "10:54", "12:30", "03:46", "04:48", "01:57", "04:47", "10:29", "10:39"} 
"05:21"
Returns: 13.6666666666669

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class HairCuts{
        public double maxCut(String[] enter, String lastExit){
                int n = enter.length;
                double[] ts = new double[n];
                for(int i = 0; i < n; i++){
                        ts[i] = parse(enter[i]);
                }
                Arrays.sort(ts);
                double last = parse(lastExit);

                double lo = 5.0;
                double hi = last-ts[n-1];
                boolean wasMatch = false;
                for(int i = 0; i<100; i++){
                        double mid = lo + (hi-lo)/2;
                        double cursor = 0; int j = 0;
                        for(j = 0; j < n && Double.compare(cursor, last) < 0; j++){
                                cursor = Double.compare(cursor, ts[j]) < 0 ? ts[j] : cursor;
                                cursor += mid;
                        }
                        if (j == n && Double.compare(cursor, last) <= 0){
                                wasMatch = true;
                                lo = mid;
                        } else {
                                hi = mid;
                        }
                }
                if (wasMatch) return lo;
                return -1;
        }

        double parse(String ts){
                int hour = Integer.parseInt(ts.substring(0,2));
                hour = hour < 9 ? 12+hour : hour;
                return hour*60.0+Integer.parseInt(ts.substring(3));
        }
}
