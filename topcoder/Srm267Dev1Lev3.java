import java.util.*;

public class Srm267Dev1Lev3{
	public static void main(String[] args){
		HairCuts hc = new HairCuts();
		System.out.println(hc.maxCut(new String[]{"04:22","09:00"}, "05:52"));
                System.out.println(hc.maxCut(new String[]{"09:00","09:22","09:22"}, "10:11"));
                System.out.println(hc.maxCut(new String[]{"09:00","04:00","04:02"}, "04:09"));
                System.out.println(hc.maxCut(new String[]{"04:40", "10:54", "12:30", "03:46", "04:48", "01:57", "04:47", "10:29", "10:39"}, "05:21"));
	}
}
class HairCuts{
	double maxCut(String[] enter, String lastExit){
		int n = enter.length;
		double[] ts = new double[n];
		for(int i = 0; i < n; i++){
			ts[i] = parse(enter[i]);
		}
		Arrays.sort(ts);
		double last = parse(lastExit);

		double lo = 5.0;
		double hi = last-ts[n-1];
		boolean wasMatch = false;
		for(int i = 0; i<100; i++){
			double mid = lo + (hi-lo)/2;
			double cursor = 0; int j = 0;
			for(j = 0; j < n && Double.compare(cursor, last) < 0; j++){
				cursor = Double.compare(cursor, ts[j]) < 0 ? ts[j] : cursor;
				cursor += mid;
			}
			if (j == n && Double.compare(cursor, last) <= 0){
				wasMatch = true;
				lo = mid;
			} else {
				hi = mid;
			}
		}
		if (wasMatch) return lo;
		return -1;
	}

	double parse(String ts){
		int hour = Integer.parseInt(ts.substring(0,2));
		hour = hour < 9 ? 12+hour : hour;
		return hour*60.0+Integer.parseInt(ts.substring(3));
	}
}
