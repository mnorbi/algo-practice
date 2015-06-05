public class Question18_4{
	public static void main(String[]args){
		System.out.println(numbersOfTwoInRangeBruteForce(123056));
		System.out.println(numbersOfTwoInRangeOptimized(123056));
	}
	private static int numbersOfTwoInRangeBruteForce(int n){
		int ret = 0;
		for(int i = 2; i <= n; ++i){
			ret+=countTwos(i);
		}
		return ret;
	}
	private static int countTwos(int i){
		int tmp = i;
		int ret = 0;
		while(i > 1){
			if (i%10 == 2){
				++ret;
			}
			i/=10;
		}
		return ret;
	}
	/**
		Mistakes:
			actual:"count=" expected:"count+="
			wrong case analysis in the last mid=2 case:
				actual:"count += Math.max(1, left+1)*(right+1);"
				expected:"count += Math.max(1, left)*Math.max(1,(i/10))+right+1;"
			slow calculation of: left=n/i, mid=n%i/(i/10), right=n%i%(i/10), drawing might have helped
			
	**/
	private static int numbersOfTwoInRangeOptimized(int n){
		if (n == 0|| n==1) return 0;
		int i = 1;

		int left, mid, right, count = 0;
		while(true){
			i*=10;
			left = n/i;
			mid = (n%i)/(i/10);
			right = (n%i)%(i/10);

			if (mid > 2){
				count += (left+1)*Math.max(1,(i/10));
			} else if (mid < 2){
				count += Math.max(1, left)*Math.max(1,(i/10));
			} else {
				count += Math.max(1, left)*Math.max(1,(i/10))+right+1;
			}

			if (left < 2) break;
		};
		return count;
	}
}
