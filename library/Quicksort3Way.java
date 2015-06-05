class Quicksort3Way {
    public static void main(String[]args){
        int[] arr = new int[] {1, 3, 5, 7,9, 2, 4, 6, 8, 1, 4, 4, 4};
        quicksort3way(arr, 0, arr.length-1);
        print(arr);
    }
    public static void quicksort3way(int[] arr, int lo, int hi){
        if (hi <= lo) return;
        int i = lo+1;
        int lt = lo;
        int gt = hi;
        swap(arr, lo, lo+(hi-lo)/2);
        int pivot = arr[lo];

        while(i <= gt){
            if (arr[i] < pivot){
                swap(arr, i, lt);
                ++i;
                ++lt;
            } else if (arr[i] > pivot){
                swap(arr, i, gt);
                --gt;
            } else {
                ++i;
            }
        }

        quicksort3way(arr, lo, lt-1);
        quicksort3way(arr, gt+1, hi);
    }
    public static void swap(int[]arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static void print(int[]arr){
        for(int i : arr){
            System.out.print(arr[i]);
            System.out.print(" ");
        }
        System.out.println();
    }
}