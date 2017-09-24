class Solution {
    private static int[] treeSize = new int[10];
    private static int[] pows = new int[10];
    
    static {
        treeSize[0] = 1;
        pows[0] = 1;
        for (int a = 1; a <= 9; ++a) {
            pows[a] = pows[a - 1] * 10;
            treeSize[a] = treeSize[a - 1]+pows[a];
        }
    }
    
    public int findKthNumber(int n, int k) {
        int pow = 0;
        for (int a = n/10; a > 0; a /= 10, ++pow) ;
        return findKthNumber(n, n, k - 1, pow, 1, 0);
    }

    private int findKthNumber(int n, int size, int k, int pow, int lo, int pfx) {
        if (k < 0){
            return pfx;
        }
        if (pow == 0) {
            return 10 * pfx + lo + k;
        }
        int msd = n / pows[pow];
        int before = (msd - lo) * treeSize[pow];
        int after = (Math.min(size, 9) - msd) * treeSize[pow - 1];
        int mid = size - before - after;
        if (k < before) {
            return findInCompleteTree(k - 1 - (k / treeSize[pow]) * treeSize[pow], pow - 1, 10 * pfx + lo + k / treeSize[pow]);
        }
        if (k < before + mid) {
            return findKthNumber(n - msd * pows[pow], mid - 1, k - before - 1, pow - 1, 0, 10 * pfx + msd);
        }
        return findInCompleteTree(k - 1 - before - mid - ((k-before-mid)/ treeSize[pow-1]) * treeSize[pow-1], pow - 2, 10 * pfx + msd + 1 + (k - before - mid) / treeSize[pow - 1]);
    }

    private int findInCompleteTree(int k, int pow, int pfx) {
        if (k < 0){
            return pfx;
        }
        if (pow == 0) {
            return 10 * pfx + k;
        }
        return findInCompleteTree(k - 1 - (k/treeSize[pow])*treeSize[pow], pow - 1, 10 * pfx + k / treeSize[pow]);
    }
}
