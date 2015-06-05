import java.util.*;

public class Question17_12{
	public static void main(String[] args){
		AllPairsSummingToSpecificValue solver = new AllPairsSummingToSpecificValue();
		print(solver.find(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11 }, 10));
	}
	private static void print(List<Pair> pairs){
		for(Pair pair : pairs){
			System.out.print(pair);
			System.out.println("");
		}
		System.out.println();
	}
}
class AllPairsSummingToSpecificValue{
	public List<Pair> find(int[] arr, int targetSum){
		if (arr == null || arr.length == 0) return Collections.emptyList();
		arr = Arrays.copyOf(arr, arr.length);
		Arrays.sort(arr);
		List<Pair> ret = new ArrayList<>();
		for(int s = 0, e = arr.length-1; s < e;){
			int sum = arr[s]+arr[e];
			if(sum == targetSum){
				ret.add(new Pair(arr[s++],arr[e--]));
			} else if (sum < targetSum){
				s++;
			} else if (sum > targetSum){
				e--;
			}
		}
		return ret;
	}
}
class Pair{
	int first;
	int second;
	Pair(int first, int second){
		this.first = first;
		this.second = second;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.first);
		sb.append(" ");
		sb.append(this.second);
		return sb.toString();
	}
}
