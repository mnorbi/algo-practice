import java.util.*;

public class Srm216Div1Lev2{
        public static void main(String[] args){
                System.out.println(new Refactoring().refactor(1916006400));
        }
}
class Refactoring{
	public int refactor(int n){
		return refactor(2, n, new HashMap<Integer, Map<Integer, Integer>>());
	}

	private int refactor(int min, int n, Map<Integer, Map<Integer, Integer>> memo){
		if(min >= n) return 0;
		Map<Integer, Integer> map = memo.get(min);
		if (map != null){
			if (map.containsKey(n)) return map.get(n);
		} else {
			map = new HashMap<Integer, Integer>();
			memo.put(min, map);
		}
		int ret = 0;
		for(int i = min; i*i <= n; i++){
			if (n%i == 0){
				ret += 1 + refactor(i, n/i, memo);
			}
		}
		map.put(n, ret);
		return ret;
	}

}
