class SearchInTwoSortedArrays {
    static final int[] EMPTY_ARRAY = new int[0];

    public static void main(String[]args){
        System.out.println(search(5, new int[]{1, 3, 5}, new int[]{2, 4, 6, 8, 10}));
    }

    static int search(int k, int[] arr1, int[] arr2) {
        if (arr1 == null) arr1 = EMPTY_ARRAY;
        if (arr2 == null) arr2 = EMPTY_ARRAY;

        if (k <= 0 || k > arr1.length + arr2.length) return -1;

//        int n1 = arr1.length;
//        int n2 = arr2.length;
        int lo = Math.max(0, k - arr2.length);
        int hi = Math.min(k, arr1.length);

        int k1=0;
        int k2=0;
        while(lo <= hi){
            k1 = lo + (hi-lo)/2;//k * n1 / (n1 + n2);
            k2 = k - k1;
            int v1 = valAt(arr1, k1 - 1), v1_next = valAt(arr1, k1);
            int v2 = valAt(arr2, k2 - 1), v2_next = valAt(arr2, k2);

            if (between(v2, v1, v2_next)) {
                return v1;
            }else if (between(v1, v2, v1_next)) {
                return v2;
            }
            if (v1 > v2_next) {
                hi = k1-1;//n1 = k1 - 1;
            } else {
                lo = k1+1;//n2 = k2 - 1;
            }
        }
        if (k2 == 0){
            return valAt(arr1, k1);
        } else {
            return valAt(arr2, k2);
        }
     }

    static int valAt(int[] arr, int idx) {
        if (idx < 0) return Integer.MIN_VALUE;
        if (idx >= arr.length) return Integer.MAX_VALUE;
        return arr[idx];
    }

    static boolean between(int a, int b, int c){
        boolean ret = a <= b && b <= c;
        return ret;
    }
}
