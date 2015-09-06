import java.util.*;
class MedianArray {
        public static void main(String[]args){
		System.out.println(new MedianArray().findMedianSortedArrays(Arrays.asList(0,2,4), Arrays.asList(1,3,5)));
	}
	// DO NOT MODIFY BOTH THE LISTS
	public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
	    int N = a.size();
	    int M = b.size();
	    
	    int size = N+M;
	    int k = (size+1)/2;
	    int lo = 0;//Math.max(0, k-M);
	    int hi = N;//Math.min(k, N);
	    
	    while(lo<=hi){
  	        //kA+kB = k
  	        int kA = lo + (hi-lo)/2;
  	        int kB = k-kA;
            
            int kA_val = val(a, kA-1);
            int kA_next_val = val(a, kA);
            
            int kB_val = val(b, kB-1);
            int kB_next_val = val(b, kB);
            
            if (between(kB_val, kA_val, kB_next_val)){
                return medianFrom(size, kA_val, kA_next_val, kB_next_val);
            } else if (between(kA_val, kB_val, kA_next_val)){
                return medianFrom(size, kB_val, kA_next_val, kB_next_val);
            } else if (kA_val > kB_next_val) {
                hi = kA-1;
            } else {
                lo = kA+1;
            }
	    }
	    throw new IllegalStateException();
	}
	int val(List<Integer> list, int i){
	    if (i < 0) return Integer.MIN_VALUE;
	    if (i >= list.size()) return Integer.MAX_VALUE;
	    return list.get(i);
	}
	double medianFrom(int size, int v1, int v2, int v3){
        if ((size & 1) == 1){
            return v1;
        } else {
            return (0.0 + v1+Math.min(v2, v3))/2;
        }
	}
	boolean between(int v1, int v2, int v3){
	    if (v1 <= v2 && v2 <= v3){
	        return true;
	    }
	    return false;
	}
}

