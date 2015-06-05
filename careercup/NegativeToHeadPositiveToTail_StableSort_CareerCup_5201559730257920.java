/**
Give you an array which has n integers,it has both positive and negative integers.Now you need sort this array in a special way.After that,the negative integers should in the front,and the positive integers should in the back.Also the relative position should not be changed. 
eg. -1 1 3 -2 2 ans: -1 -2 1 3 2. 
o(n)time complexity and o(1) space complexity is perfect.
**/
/**
        Mistakes:
                for loop early ending due to integer division bug (N/len/2+1 needed instead of N/len/2)
**/
class NegativeToHeadPositiveToTail_StableSort_CareerCup_5201559730257920{
        public static void main(String[]args){
                print(specialSort(new int[] {-1}));
                print(specialSort(new int[] {1}));
                print(specialSort(new int[] {-1,1}));
                print(specialSort(new int[] {1,-1}));
                print(specialSort(new int[] {-1, 1, 3, -2, 2}));
                print(specialSort(new int[] { 11, 1, 3, -2, -5, 2 }));
        }
        private static void print(int[]arr){
                for(int i : arr){
                        System.out.printf(" %d",i);
                }
                System.out.println();
        }
        public static int[] specialSort(int[] arr){
                int N = arr.length;
                for(int len = 1; len < N; len*=2){
                        for(int i = 0; i < N/len/2+1; ++i){
                                int pIdx = findFirstNonNegative(arr, i*len, len);
                                if (pIdx < 0) continue;
                                int nIdx = findFirstNegative(arr, (i+1)*len, len);
                                if (nIdx < 0) continue;
                                reverse(arr, pIdx, nIdx);
                                reverse(arr, nIdx, Math.min(nIdx+len, N));
                                reverse(arr, pIdx, Math.min(nIdx+len, N));
                        }
                }
                return arr;
        }
        private static int findFirstNonNegative(int[] arr, int from, int len){
                for(int i = from; i < Math.min(from+len, arr.length); ++i){
                        if (Integer.compare(arr[i], 0) >= 0){
                                return i;
                        }
                }
                return -1;
        }
        private static int findFirstNegative(int[] arr, int from, int len){
                for(int i = from; i < Math.min(from+len, arr.length); ++i){
                        if (Integer.compare(arr[i], 0) < 0){
                                return i;
                        }
                }
                return -1;
        }
        private static void reverse(int[] arr, int from, int until){
                for(int i = from, j = until-1; i < j; ++i, --j){
                        swap(arr, i, j);
                }
        }
        private static void swap(int[] arr, int i, int j){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
        }
}
