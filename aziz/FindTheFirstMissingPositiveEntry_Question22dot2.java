public class FindTheFirstMissingPositiveEntry_Question22dot2{
    public static void main(String[] args) {
        System.out.println(firstMissingPositiveEntry(new int[]{}));
        System.out.println(firstMissingPositiveEntry(new int[]{0}));
        System.out.println(firstMissingPositiveEntry(new int[]{1}));
        System.out.println(firstMissingPositiveEntry(new int[]{2}));
        System.out.println(firstMissingPositiveEntry(new int[]{5, 4, 3, 2, 1, -1, 0, 0, -1}));
        System.out.println(firstMissingPositiveEntry(new int[]{-1, 1, 4, -1, 3, 2, 1, 1000}));
        System.out.println(firstMissingPositiveEntry(new int[]{3, 5, 4, -1, 5, 1, -1}));
    }

    static int firstMissingPositiveEntry(int[] arr){
      int p =0, n = arr.length;
      while(p < n){
        if (arr[p] < p+1 || arr[p] > n) {swap(arr, p, --n);}
        else if (arr[p] == p+1) {++p;}
        else swap(arr, p, arr[p]-1);
      }
      return p+1;
    }
    static boolean swap(int[] arr, int i, int j) {
        if (arr[i] == arr[j]) return false;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return true;
    }
}
