class QuickSort{
	public static void main(String[]args){
	  int[] arr = new int[] {1, 3, 5, 7,9, 2, 4, 6, 8};
	  quicksort(arr, 0, arr.length-1);
	  print(arr);
	}
	public static void quicksort(int[] arr, int lo, int hi){
	  if (hi <= lo) return;
	  swap(arr, lo, lo+(hi-lo)/2);
	  int j = partition(arr, lo, hi);
	  quicksort(arr, lo, j-1);
	  quicksort(arr, j+1, hi);
	}
	public static int partition(int[] arr, int lo, int hi){
	  int i = lo+1;
	  int j = hi;
	  for(;;){
	    for(; i < hi && arr[i] < arr[lo]; ++i);
	    for(; arr[lo] < arr[j]; --j);
	    if (i >= j) break;
	    swap(arr, i, j);
	  }
	  swap(arr, lo, j);
	  return j;
	}
	
	public static void swap(int[]arr, int i, int j){
	  int tmp = arr[i];
	  arr[i] = arr[j];
	  arr[j] = tmp;
	}
	public static void print(int[]arr){
	  for(int i : arr){
	    System.out.print(i);
	    System.out.print(" ");
	  }
	  System.out.println();
	}
}