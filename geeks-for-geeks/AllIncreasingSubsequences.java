import java.util.*;
import java.util.concurrent.*;

class AllIncreasingSubsequences{

    static int solve1(int arr[])
    {
        // count[] array is used to store all
        // sub-sequences possible using that 
        // digit count[] array covers all 
        // the digit from 0 to 9
        int count[] = new int[10];  
 
        // scan each digit in arr[] 
        for (int i = 0; i < arr.length; i++)
        {
            // count all possible sub-
            // sequences by the digits
            // less than arr[i] digit
            for (int j = arr[i] - 1; j >= 0; j--)
                count[arr[i]] += count[j];  
                 
            // store sum of all sub-sequences 
            // plus 1 in count[] array
            count[arr[i]]++;
        }   
 
        // now sum up the all sequences
        // possible in count[] array
        int result = 0;
        for (int i = 0; i < 10; i++)
            result += count[i];
 
        return result;
    }

	
	static int solve2(int[] arr){
		int n = arr.length;
		int max = 9;
		//dp[i] = subsequences with all the digits not above i
		int[] dp = new int[max+1];
	
/*		F(i,x<a[i])
			F(i-1,x)
		F(i,a[i])
			F(i-1,a[i]-1)
			+F(i-1,a[i])
		F(i,x>a[i])
			F(i-1,x)
			+F(i-1,a[i]-1)
*/
		
		for(int a = 0; a < n; ++a){
			int delta = 1 + (arr[a] == 0 ? 0 : dp[arr[a]-1]);
			for(int b = arr[a]; b <= max; ++b){
				dp[b] += delta;
			}
		}	
		return dp[9];
	}

        public static void main (String[] args)
        {
                while(true){
                        int[] arr = rndArr(rnd(3,101), 0, 10);
                        int ans1 = solve1(arr);
                        int ans2 = solve2(arr);
                        if (ans1 != ans2){
                                System.out.println(ans1);
                                System.out.println(ans2);
                                System.out.println(Arrays.toString(arr));
                                throw new RuntimeException();
                        }
                }
        }
        static int[] rndArr(int size, int min, int max){
                int[] arr = new int[size];
                for(int a = 0; a < arr.length; ++a){
                        arr[a] = rnd(min, max);
                }
                return arr;
        }
        static int rnd(int min, int max){
                return ThreadLocalRandom.current().nextInt(Math.min(min,max), Math.max(min, max));
        }
}
