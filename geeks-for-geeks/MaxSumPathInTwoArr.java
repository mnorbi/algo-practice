class MaxSumPathInTwoArr{
	static class Solver{
		int[] solve(int[] arr1, int[] arr2){
			int p1 = 0, p2 = 0;
			int i1 = 0, i2 = 0;
			int s = 0;
			int[] ret = null;
			byte runCount = 1;
			while(runCount <= 2){
				while(i1<arr1.length && i2<arr2.length){
					if (arr1[i1] == arr2[i2]){
						i1++;i2++;
						if (sum(arr1,p1,i1) >= sum(arr2,p2,i2)){
							if (runCount == 2){
								copyTo(ret, s, arr1, p1, i1);
							}
							s+=i1-p1;
						} else {
							if (runCount == 2){
                                                                copyTo(ret, s, arr2, p2, i2);
                                                        }
							s+=i2-p2;
						}
						p1=i1; p2=i2;
					} else if (arr1[i1] < arr2[i2]){
						i1++;
					} else {
						i2++;	
					}
				}
				if (p1 < arr1.length && sum(arr1,p1) >= sum(arr2,p2)){
					if (runCount == 2){
						copyTo(ret, s, arr1, p1);
					}
					s+=arr1.length-p1;
				} else if (p2 < arr2.length){
					if (runCount == 2){
						copyTo(ret, s, arr2, p2);
					}
					s+=arr2.length-p2;
				}
				if (runCount == 1){
					ret = new int[s];
				}
				s=p1=p2=i1=i2=0;
				runCount++;
			}
			
			return ret;
		}

		void copyTo(int[]arr1, int from1, int[] arr2, int from2){
			copyTo(arr1, from1, arr2, from2, arr2.length);
		}

		void copyTo(int[] arr1, int from1, int[] arr2, int from2, int to2){
			if (to2>arr2.length || from2>= to2 || from1>=arr1.length ){
				return;
			}
			for(int i = from2; i<to2 && from1<arr1.length; i++){
				arr1[from1++] = arr2[i];
			}
		}
	
		int sum(int[] arr, int from){
			return sum(arr, from, arr.length);
		}
		
		int sum(int[] arr, int from, int to){
			if (to>arr.length || from>=to){
				return 0;
			}
			int sum = 0;
			for(int i = 0; i < to; i++){
				sum+=arr[i];
			}
			return sum;
		}
	}
	public static void main(String[] args){
		Solver solver = new Solver();
		printArr(
			solver.solve(
				new int[] {2, 3, 7, 10, 12, 15, 30, 34},
				new int[] {1, 5, 7, 8, 10, 15, 16, 19}));
		printArr(
			solver.solve(
				new int[] {2, 3, 7, 10, 12},
				new int[] {1, 5, 7, 8}));

		printArr(
			solver.solve(
				new int[] {10, 12},
				new int[] {5, 7, 9}));

		printArr(
			solver.solve(
				new int[] {1, 2, 5, 5, 8},

				new int[] {4, 5, 6, 8}));
	}	
	static void printArr(int[] arr){
		for(int i : arr){
			System.out.print(i+" ");
		}
		System.out.println();
	}
}
