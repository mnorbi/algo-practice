import java.util.*;

class MinimizeMaxDistanceToGasStation{

	double minimize(int[] arr, int K){
		if (arr == null || arr.length < 2) return 0;
		Map<Integer, Integer> dists = new HashMap<>();
		for(int a = 1; a < arr.length; ++a){
			int dist = arr[a]-arr[a-1];
			if (!dists.containsKey(dist)){
				dists.put(dist, 0);
			}
			dists.put(dist, dists.get(dist)+1);
		}
		PriorityQueue<Dist> pq = new PriorityQueue<>((d1,d2)-> -Double.compare(d1.len,d2.len));//#bug #comparison was increasing
		for(Map.Entry<Integer, Integer> entry : dists.entrySet()){
			Dist dist = new Dist();
			dist.len = entry.getKey();
			dist.cnt = entry.getValue();
			pq.add(dist);
		}
		while(K>0){
			Dist dist = pq.remove();
			if (K < dist.cnt){
				return dist.len;
			} else if (K >= dist.cnt){
				K -= dist.cnt;
				dist.len /= 2;
				dist.cnt *= 2;
				pq.add(dist);
			}
		}
		return pq.remove().len;
	}
	static class Dist{
		int cnt;
		double len;
	}
	
}
