public class BinSearch{
	public static void main(String[] args){
//              System.out.println(simpleBinSearch(new int[] { 1 }, 1));
//              System.out.println(simpleBinSearch(new int[] { 1, 2 }, 1));
//		System.out.println(simpleBinSearch(new int[] { 1, 2, 3, 4, 5, 6, 7}, 4));

//  		  System.out.println(insertionPointAfter(new int[] { 1 }, 0));
//                System.out.println(insertionPointAfter(new int[] { 0 }, 1));
//                System.out.println(insertionPointAfter(new int[] { 0, 1 }, 2));
//                System.out.println(insertionPointAfter(new int[] { 1, 2 }, 0));
//                System.out.println(insertionPointAfter(new int[] { 1, 2 }, 1));
		System.out.println(oneStartPosition(new int[] { 0 }));
		System.out.println(oneStartPosition(new int[] { 1 }));
                System.out.println(oneStartPosition(new int[] { 0, 1 }));
                System.out.println(oneStartPosition(new int[] { 1, 1 }));
                System.out.println(oneStartPosition(new int[] { 0, 0, 0, 1, 1, 1 }));
                System.out.println(oneStartPosition(new int[] { 0, 0, 0, 0, 1, 1 }));
		System.out.println(oneStartPosition(new int[] { 0, 0, 0, 0, 0, 0 }));
	}
	
	public static int simpleBinSearch(int[] in, int val){
		int lo = 0;
		int hi = in.length-1;
		while(lo <= hi){
			int mid = lo+(hi-lo)/2;
			if (in[mid] < val){
				lo = mid+1;
			} else if (in[mid] > val){
				hi = mid-1;
			} else return mid;
		}
		return -1;
	}

	public static int insertionPointAfter(int[] in, int val){
		int lo = 0;
		int hi = in.length;

		while(lo < hi){
			int mid = (hi+lo)>>>1; 
			if (in[mid] < val){
				lo = mid+1;
			} else {
				hi = mid;
			}
		}
		return lo-1;
	}

	public static int oneStartPosition(int[] in){
		int lo = 0;
		int hi = in.length;

		while(lo < hi){
			int mid = (lo+hi)>>>1;

			if (in[mid] == 0){
				lo = mid+1;
			} else {
				hi = mid;
			}
		}
		if (lo < in.length) return lo;

		return -1;
	}
}
