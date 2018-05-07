import java.util.*;

class MinimizeMaxDistanceToGasStation{
	double minimize(int[] arr, int K){
		if (arr == null || arr.length < 2) return 0;
		PriorityQueue<Dist> pq = new PriorityQueue<>((d1,d2)-> -Double.compare(d1.val(),d2.val()));//#bug #comparison was increasing
		for(int a = 1; a < arr.length; ++a){
			Dist dist = new Dist();
			dist.len = arr[a]-arr[a-1];
			dist.divs = 1;
			pq.add(dist);
		}
		double maxDist = (arr[arr.length-1]-arr[0])/(K+1);
		while(K>0){//#bug #logic
			Dist dist = pq.remove();
			while(K > 0 && pq.isEmpty() || dist.val() >= pq.peek().val() || dist.val() > maxDist){
				++dist.divs;
				--K;
			}
			pq.add(dist);
		}
		return pq.remove().val();
	}
	static class Dist{
		int divs;
		double len;
		double val(){
			return len/divs;
		}
	}
}
