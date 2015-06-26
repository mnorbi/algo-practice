public class FindTheFirstMissingPositiveEntry_Question22dot2{
    public static void main(String[] args) {
        System.out.println(firstMissingPositiveEntry2(new int[]{}));
        System.out.println(firstMissingPositiveEntry2(new int[]{0}));
        System.out.println(firstMissingPositiveEntry2(new int[]{1}));
        System.out.println(firstMissingPositiveEntry2(new int[]{2}));
        System.out.println(firstMissingPositiveEntry2(new int[]{5, 4, 3, 2, 1, -1, 0, 0, -1}));
        System.out.println(firstMissingPositiveEntry2(new int[]{-1, 1, 4, -1, 3, 2, 1, 1000}));
        System.out.println(firstMissingPositiveEntry2(new int[]{3, 5, 4, -1, 5, 1, -1}));
    }

    static int firstMissingPositiveEntry2(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; ) {
            if (arr[i] < 1 || arr[i] > N) {
                arr[i] = arr[--N];
                continue;
            } else if (!swap(arr, arr[i]-1, i)){//-1 because for example 1 is stored on arr[0]
              ++i;
            }
        }

        int i = 0;
        for(; i < N && arr[i]-1 == i; ++i);

        return i+1;
    }

    static boolean swap(int[] arr, int i, int j) {
        if (arr[i] == arr[j]) return false;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return true;
    }
/*
  static int firstMissingPositiveEntry1(int[] arr){
    int max = arr.length+1;

    for(int i = 0; i < arr.length; ++i){
      int idx = arr[i];
      if (idx < 1 || idx >= max){
        --max;
        continue;
      } else {
        int nextIdx = arr[idx-1];
        for(;idx != nextIdx;){
          arr[idx-1] = idx;
          if (nextIdx < 1 || nextIdx >= max){
            --max;
            break;
          } else {
            idx = nextIdx;
            nextIdx = arr[nextIdx-1];
          }
        }
      }
    }
    
    int ret = 1;
    for(; ret < max && ret == arr[ret-1]; ++ret);

    return ret;
  }
*/
}
