import java.util.*;
class ElementsClosestToMedian_simplified_partitioning{
    public static void main(String[]argS){
        print(closestToMedian(new int[]{7, 12, 10, 11, 2, 14, 29, 3, 4}, 5));//2 3 4 7 (10) 11 12 14 29
        print(closestToMedian(new int[]{7, 12, 10, 11, 2, 14, 29, 3, 4, 1}, 7));//1 2 3 4 (7 10) 11 12 14 29
        print(closestToMedian(new int[]{1}, 1));
        print(closestToMedian(new int[]{1,2}, 1));
        print(closestToMedian(new int[]{2,1}, 1));
        print(closestToMedian(new int[]{1,2}, 2));
    }

    static int[] closestToMedian(int[] arr, int k){
        if (arr == null) return arr;
        if (k == 0) return EMPTY_ARRAY;
        if (k >= arr.length) return arr;

        int n = arr.length;

        int lMedId = (n-1)/2;
        int rMedId = n/2;

        kthElement(lMedId, arr, 0, n);
        if (n%2 == 0){
            kthElement(0, arr, rMedId, n);
        }

        Median median = new Median(arr[lMedId], arr[rMedId]);

        kthElement(k-1, arr, 0, n, distanceToMedianComparator(median));

        return Arrays.copyOfRange(arr, 0, k);
    }

    /*
        0 based kth index of the element in "c" based sorted order from the array arr[lo:hi)
    */
    static void kthElement(int k, int[] arr, int lo, int hi, IntComparator c){
        //k: 0 based
        //lo: 0 based included
        //hi: 0 based excluded
        int n = hi-lo;
        if (k < 0 || k >= n) return;
        //convert into [lo,hi) space to avoid changing k later
        k = lo+k;
        while(true){
            int pivot = arr[lo];
            //p: tail of pivots, inclusive
            //q: head of pivots, exclusive
            //r: head of abovePivotsm exclusive
            int p = lo, q = lo+1, r = hi-1;
            while(q <= r){
                int cmp = c.compare(arr[q],pivot);
                if (cmp < 0) { swap(arr, p++, q++); }//swap first pivot with underPivot
                else if (cmp == 0) { ++q; }
                else {
                    cmp = c.compare(arr[r], pivot);
                    if (cmp <= 0){ swap(arr, q, r--); }//q must not move, we need to move underPivot to left
                    else { --r; }
                }
            }
            //p: after last belowPivot element
            //q: after last pivot element
            if (k < p){//k in belowPivot zone
                hi = p;
            } else if (k >= q){//k in abovePivot zone
                lo = q;
            } else {//k in pivot zone
                return;
            }
        }
    }

    static void kthElement(int k, int[] arr, int lo, int hi){
        kthElement(k, arr, lo, hi, NATURAL_COMPARATOR);
    }

    static IntComparator distanceToMedianComparator(final Median median){
        return new IntComparator(){
            public int compare(int a, int b){
                int ret = Integer.compare(median.distance(a), median.distance(b));
                return ret;
            }
        };
    }

    static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static void print(int[]arr){
        for(int i : arr){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }

    static final int[] EMPTY_ARRAY = new int[0];

    static final IntComparator NATURAL_COMPARATOR = new IntComparator(){
        public int compare(int a, int b){
            return Integer.compare(a, b);
        }
    };

    interface IntComparator{
        int compare(int a, int b);
    }

    static class Median{
        int a, b;
        Median(int a, int b){
            this.a = a;
            this.b = b;
        }

        int distance(int v){
            return Math.min(Math.abs(a-v), Math.abs(b-v));
        }
    }

}

