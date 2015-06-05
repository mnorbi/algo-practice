import java.util.*;
class Question96{
	public static void main(String[] args){
		Set<String> nPairParantheses = nPairParantheses(3);
		for(String s : nPairParantheses){
			System.out.println(s);
		}
	}

	private static Map<Integer, Set<String>> memo = new HashMap<Integer, Set<String>>();
	static {
		memo.put(1, new HashSet<String>(Arrays.asList(new String[] {"()"})));
	}

	public static Set<String> nPairParantheses(int pairs){
		if (pairs <= 0) return Collections.emptySet();
		if (memo.containsKey(pairs)){
			return memo.get(pairs);
		}
		Set<String> ret = new LinkedHashSet<String>();
		memo.put(pairs, ret);

		Set<String> partials = nPairParantheses(pairs-1);
		for(String partial : partials){
			ret.add("(" + partial + ")");
			ret.add("()" + partial);
			ret.add(partial + "()");
		}
		return ret;
	}
}
