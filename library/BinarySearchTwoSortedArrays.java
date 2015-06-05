class BinarySearchTwoSortedArrays{
    public static void main(String[]args){
        int[] A = new int[]{2, 6, 7, 9, 12};
        int[] B = new int[]{3, 4, 5};
        System.out.println(findRank(A, B, -1));
        System.out.println(findRank(A, B, 0));
        System.out.println(findRank(A, B, 1));
        System.out.println(findRank(A, B, 2));
        System.out.println(findRank(A, B, 3));
        System.out.println(findRank(A, B, 4));
        System.out.println(findRank(A, B, 5));
        System.out.println(findRank(A, B, 6));
        System.out.println(findRank(A, B, 7));
        System.out.println(findRank(A, B, 8));
        System.out.println(findRank(A, B, 9));
        System.out.println(findRank(A, B, 10));
        System.out.println(findMedian(A,B));
    }
    private static int findMedian(int[] A, int[] B){
        return findRank(A, B, (A.length+B.length+1)/2);
    }
    private static int findRank(int[] A, int[] B, int rank){
        if ((A == null || A.length == 0) && (B == null || B.length == 0)) return -1;
        if (A == null) A = new int[0];
        if (B == null) B = new int[0];
        int sizeA = A.length;
        int sizeB = B.length;
        if (sizeA+sizeB < rank) return -1;

        int loA = 0;
        int loB = 0;

        while(rank > 0){
            int searchSizeA = (rank+1)/2;//!! analyse endless loop if rank/2 is used
            int searchSizeB = rank-searchSizeA;
            int i = loA + searchSizeA-1;
            int j = loB + searchSizeB-1;
            int Ai = 0 <= i && i < A.length ? A[i] : i < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            int Bj = 0 <= j && j < B.length ? B[j] : j < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            ++i; ++j;
            int Ai_next = 0 <= i && i < A.length ? A[i] : i < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            int Bj_next = 0 <= j && j < B.length ? B[j] : j < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            if (Bj < Ai && Ai < Bj_next){
                return Ai;
            } else if (Ai < Bj && Bj < Ai_next){
                return Bj;
            } else if (Ai <= Bj){
                loA = loA + searchSizeA;
                rank -= searchSizeA;
                sizeA = sizeA-searchSizeA;
            } else {
                loB = loB + searchSizeB;
                rank -= searchSizeB;
                sizeB = sizeB-searchSizeB;
            }
        }
        return -1;
    }
}
