class Main{
	static int MAX = 1000000;
	static int[] cycleCount = new int[MAX+1];
	static {
		cycleCount[1] = 1;
	}

	static int cycleCount(int n){
		if (n<=MAX && cycleCount[n] > 0){
			return cycleCount[n];
		}

		int ret;
		if (n%2==0){
			ret = cycleCount(n>>1)+1;
		} else {
			ret = cycleCount(3*n+1)+1;
		}
		if (n<=MAX){
			cycleCount[n] = ret;
		}
		return ret;
	}

	static int cycleCountIter(int n){
		int cycleCount;
		for(cycleCount = 1; n!=1; cycleCount++){
			if (n%2==0){
				n >>= 1;
			} else {
				n = 3*n+1;
			}
		}
		return cycleCount;
	}

	static int findMax(int i, int j){
		int max = -1;
		if (j < i){
			int tmp = i;
			i = j;
			j = tmp;
		}
		for(int k = i; k <= j; k++){
			int cycleCount = cycleCount(k);
			if (max < cycleCount){
				max = cycleCount;
			} 
		}
		return max;
	}
	
	public static void main(String[] args){
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		while(scanner.hasNextInt()){
			int i = scanner.nextInt();
			int j = scanner.nextInt();
			System.out.println(
				String.format("%d %d %d", i, j,	findMax(i,j)));
		}
	}
}
