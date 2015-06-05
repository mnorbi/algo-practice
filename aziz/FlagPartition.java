public class FlagPartition{
    public static void main(String[]args){
        int[] arr = dutchFlagPartition(new int[]{2, 4, 3, 4, 2, 1}, 0);
        print(arr);
    }
    private static void print(int[] arr){
        for(int i : arr){
            System.out.printf("%d ", i);
        }
        System.out.println();
    }
    public static int[] dutchFlagPartition(int[] arr, int p){
        if (arr == null || arr.length == 0) return arr;
        if (p < 0 || p >= arr.length) return arr;

        int i = 0;
        int j = arr.length-1;
        int k = arr.length-1;
        int pivot = arr[p];
        for(;i <= j;){
            if (arr[i] >= pivot){
                swap(arr, i, j);
            }
            while (j >= i && arr[j] > pivot){
                --j;
            }
            while(j >= i && arr[j] == pivot){
                swap(arr, j, k);
                --j;
                --k;
            }
            while(i < arr.length && arr[i] < pivot){
                ++i;
            }
        }
        for(++j, ++k; k < arr.length && j < k; ++k, ++j){
            swap(arr, j, k);
        }
        return arr;
    }
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
