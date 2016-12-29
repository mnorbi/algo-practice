public class FindTheFirstMissingPositiveEntry_Question22dot2{
    public static void main(String[] args) {
        System.out.println(firstMissingPositiveEntry(new int[]{}));
        System.out.println(firstMissingPositiveEntry(new int[]{0}));
        System.out.println(firstMissingPositiveEntry(new int[]{1}));
        System.out.println(firstMissingPositiveEntry(new int[]{2}));
        System.out.println(firstMissingPositiveEntry(new int[]{5, 4, 3, 2, 1, -1, 0, 0, -1}));
        System.out.println(firstMissingPositiveEntry(new int[]{-1, 1, 4, -1, 3, 2, 1, 1000}));
        System.out.println(firstMissingPositiveEntry(new int[]{3, 5, 4, -1, 5, 1, -1}));
        System.out.println(firstMissingPositiveEntry(new int[]{1,2,3,4,5,1,1,1,1,1,1,1}));
        System.out.println(firstMissingPositiveEntry(new int[]{1,1,1,1,1,1,1,1,2,3,4,5}));
    }

    static int firstMissingPositiveEntry(int[] arr) {
        int p = 1, n = arr.length;
        while(p <= n){
            int val = arr[p-1];
            if (val < p || val > n) {swap(arr, p-1, --n);}
            else if (val == p) {++p;}
            else if (val == arr[val-1]) {swap(arr,p-1,--n);}
            else { swap(arr, p-1, val-1); }
        }
        return p;
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
