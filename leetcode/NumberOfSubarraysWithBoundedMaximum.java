class NumberOfSubarraysWithBoundedMaximum{

    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int ans = 0;
        int lo = -1, max = -1, hi = 0;
        while(hi < A.length){
            if (A[hi] <= R){
                if (A[hi] >= L){
                    max = hi;
                }
                ans += (hi-lo)-(hi-max);
            } else {
                lo = max = hi;
            }
            ++hi;
        }
        return ans;
    }

}
