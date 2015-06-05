import java.util.*;

public class DequeSort{
	public int minDeques(int[] data){
		List<Deque<Integer>> deqs = new ArrayList<>();
		for(int i = 0; i < data.length; i++){
			boolean placed = false;
			for(Deque<Integer> deque : deqs){
				if (isPlaceableBefore(data, data[i], deque.getFirst())){
					deque.addFirst(data[i]);
					placed = true;
				} else if (isPlaceableAfter(data, data[i], deque.getLast())){
					deque.addLast(data[i]);
					placed = true;
				}
			}
			if (!placed){
				Deque<Integer> newDeque = new ArrayDeque<>();
				newDeque.add(data[i]);
				deqs.add(newDeque);
			}
		}
		return deqs.size();
	}
	
	private boolean isPlaceableBefore(int[] data, int toPlace, int target){
		for(int i = 0; i < data.length; i++){
			if (between(toPlace, data[i], target)) return false;
		}
		return true;
	}
	
	private boolean isPlaceableAfter(int[] data, int toPlace, int target){
		for(int i = 0; i < data.length; i++){
			if (between(target, data[i], toPlace)) return false;
		}
		return true;
	}
	
	private boolean between(int a, int b, int c){
		return a < b && b < c;
	}
}
