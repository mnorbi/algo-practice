import java.util.*;


public class Srm277Div2Lev3{
	public static void main(String[] args){
		UnionOfIntervals solver = new UnionOfIntervals();
		int ret;
		System.out.println(
			ret = solver.nthElement(
				new int[]{ 1, 5 },
				new int[]{ 3, 7 }, 
				4
			)
		);
		assert(ret == 6);

                System.out.println(
                        ret = solver.nthElement(
                                new int[]{ 1, 3 },
                                new int[]{ 4, 5 },
                                3
                        )
                );
		assert(ret == 3);

		System.out.println(
			ret = solver.nthElement(
				new int[]{ -1500000000 },
				new int[]{ 1500000000 },
				1500000091
			)
		);
		assert(ret == 91);
	}
}
class UnionOfIntervals{
	public int nthElement(int[] lowerBound, int[] upperBound, int n){
		int count = n+1;
		long lo = lowerBound[0];
		long hi = upperBound[upperBound.length-1];
		
		while(lo <= hi){
			long mid = lo + (hi-lo)/2;
			
			long untilMidCount = itemCountUntil(lowerBound, upperBound, mid);
			
			if (untilMidCount < count){
				if (mid == upperBound[upperBound.length-1]) return (int)mid;
				lo = mid+1;
				continue;
			}
			
			if (mid == lowerBound[0]) return (int)mid;
			
			long untilPreMidCount = itemCountUntil(lowerBound, upperBound, mid-1);
			
			if (untilPreMidCount < count) return (int)mid;
			
			hi = mid-1;
		}
		
		throw new IllegalStateException();
	}
	
	long itemCountUntil(int[] lowerBound, int[] upperBound, long query){
		long ret = 0;
		for(int i = 0; i < lowerBound.length; i++){
			if (query >= upperBound[i]){
				ret += upperBound[i] - lowerBound[i] + 1;
			} else if (query >= lowerBound[i]){
				ret += query - lowerBound[i] + 1;
			}
		}
		return ret;
	}
	
}
