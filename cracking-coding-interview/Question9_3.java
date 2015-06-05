public class Question9_3{
	public static void main(String[]args){
		Question9_3 solver = new Question9_3();
		System.out.println(solver.findMagicNumberNoRep(new int[]{-10, 0, 1, 2, 3, 5, 7, 9}));
	}

	public int findMagicNumberNoRep(int[] arr){
		if (arr == null || arr.length == 0){
			return -1;
		}

		int lo = 0;
		int hi = arr.length-1;

		while(lo <= hi){
			int mid = lo + (hi-lo)/2;

			if (mid > arr[mid]){
				lo = mid+1;
			} else if (mid < arr[mid]){
				hi = mid-1;	
			} else {
				return mid;
			}
		}
		return -1;
	}
	//with rep linear scan must be used
}
