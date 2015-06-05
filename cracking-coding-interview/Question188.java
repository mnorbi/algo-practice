import java.util.*;

public class Question188{
	public static void main(String[] args){
		SuffixTree sTree = SuffixTree.of("some longer string which is going to be searched multipple times for smaller strings");
		String[] smallerStrings = new String[] {"is", "to", "string"};
		for(String s : smallerStrings){
			List<Integer> matchIdxs = sTree.matchIdxs(s);
			System.out.print(String.format("'%s': ", s));
			print(matchIdxs);
			System.out.println();
		}
	}

	private static void print(List<Integer> in){
		if (in == null || in.size() == 0){
			System.out.print("no match");
			return;
		}
		for(Integer i : in){
			System.out.print(i + " ");
		}
	}
}

class SuffixTree{
	private final SuffixNode root = new SuffixNode(null);

	static SuffixTree of(String string){
		if (string == null) return null;

		SuffixTree sTree = new SuffixTree();

		for(int i = 0; i < string.length(); i++){
			SuffixNode node = sTree.root;
			for(int j = i; j < string.length(); node = node.add(string.charAt(j++), i));
		}

		return sTree;
	}

	List<Integer> matchIdxs(String pattern){
		SuffixNode node = root;
		for(int i = 0; i < pattern.length() && node != null; node = node.getNode(pattern.charAt(i++)));
	
		if (node == null) return Collections.<Integer>emptyList();
	
		return Collections.unmodifiableList(node.idxs);
	}

	@Deprecated
	private int[] subtractFromEach(List<Integer> in, int val){
		int[] ret = new int[in.size()];

		for(int i = 0; i < in.size(); i++){
			ret[i] = in.get(i)-val;
		}
		return ret;
	}
}

class SuffixNode{
	final Character c;
	final List<Integer> idxs = new ArrayList<Integer>();
	final Map<Character, SuffixNode> nodes = new HashMap<Character, SuffixNode>();

	SuffixNode(Character c){
		this.c = c;
	}

	SuffixNode add(Character c, Integer idx){
		SuffixNode node;
		if (!nodes.containsKey(c)){
			node = new SuffixNode(c);
			nodes.put(c, node);
		} else {
			node = nodes.get(c);
		}

		node.idxs.add(idx);

		return node;
	}
	
	SuffixNode getNode(Character c){
		return nodes.get(c);
	}
}
