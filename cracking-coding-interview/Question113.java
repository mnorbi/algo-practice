public class Question113{
	public static void main(String[] args){
		System.out.println(
			search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}, 55)
		);
	}

	private static int search(int[] arr, int val){
		int shift = findShift(arr);
		return binSearch(arr, shift, val);
	}

	private static int findShift(int[] arr){
		int lo = 0;
		int hi = arr.length-1;

		while(lo<hi){
			int mid = lo + (hi-lo)/2;

			if (arr[mid] > arr[lo]){
				lo = mid+1;
			} else {
				hi = mid;
			}
		}

		return lo;
	}

	private static int binSearch(int[] arr, int shift, int val){
		int n = arr.length;
		int lo = 0;
		int hi = n-1;

		while(lo<=hi){
			int mid = lo + (hi-lo)/2;

			int midVal = arr[(mid+shift)%n];
			if (midVal < val){
				lo = mid+1;
			} else if (midVal > val){
				hi = mid-1;
			} else {
				return (mid+shift)%n;
			}
		}

		return -1;
	}
	
}
