class CountSortMain{
	static class CountSort{
	        private static final int[] EMPTY = new int[0];
	        private final int radix;
	
	        CountSort(int radix){
	                this.radix = radix;
	        }
	
	        int[] sortIdx(int[] arr){
	                return sort(arr, true);
	        }
	
	        int[] sort(int[] arr){
	                return sort(arr, false);
	        }
	
	        int[] sort(int[] arr, boolean sortIdx){
	                int N = arr.length;
	                if (arr == null || N == 0){
	                        return EMPTY;
	                }
	                int[] count = new int[N+1];
	                for(int i = 0; i<N; i++){//freq. count, stored shifted for stability
	                        count[arr[i]+1]++;
	                }
	                for(int r = 0; r<radix; r++){//cum. freq.
	                        count[r+1] += count[r];
	                }
	
	                int[] ans = new int[N];
	                for(int i = 0; i<N; i++){//move
	                        ans[count[arr[i]]++] = sortIdx ? i : arr[i];
	                }
	
	                return ans;
	        }
	}


    public static void main(String[] args){
            String[] names =
                    new String[] {
                            "Anderson", "Brown", "Davis", "Garcia", "Harris",
                            "Jackson", "Johnson", "Jones", "Martin", "Martinez",
                            "Miller", "Moore", "Robinson", "Smith", "Taylor",
                            "Thomas", "Thompson", "White", "Williams", "Wilson"};

            int[] keys =
                    new int[] {
                            2, 3, 3, 4, 1,
                            3, 4, 3, 1, 2,
                            2, 1, 2, 4, 3,
                            4, 4, 2, 3, 4};

            CountSort countSorter = new CountSort(4);
            int[] sortedIdx = countSorter.sortIdx(keys);
            for(int i = 0; i<sortedIdx.length; i++){
                    System.out.println(String.format("%s\t%d", names[sortedIdx[i]], keys[sortedIdx[i]]));
            }
    }

}
