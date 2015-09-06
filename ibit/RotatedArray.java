class RotatedArray {
	// DO NOT MODIFY THE LIST
	public int findMin(final List<Integer> a) {
	    int lo = 0;
	    int hi = a.size()-1;
	    int best = a.get(0);//attention
	    while(lo<=hi){
	        int mid = lo + (hi-lo)/2;
	        int midVal = a.get(mid);
	        if (midVal > a.get(0)){//attention
	            lo = mid+1;
	        } else {
	            best = midVal;
	            hi = mid-1;
	        }
	    }
	    return best;
	}
}
