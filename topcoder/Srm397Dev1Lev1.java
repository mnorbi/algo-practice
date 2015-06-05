import java.util.*;


/**
	Mistakes:
		1. node class not created for bfs: did not create Node class to count the distance
		2. array indexing range: missed a -1 for the end
		3. bad hashCode implementation: int[].hashCode is reference and not content based
		4. wrong type in bsf and visited queue: wrong int[] types both in the bfs queue and the visited set, should have used Node
		5. early optimization: mutation off array to generate reverse candidates instead of copy caused an early exit
		
**/
public class Srm397Dev1Lev1{
	public static void main(String[] args){
		SolvingGame solvingGame = new SolvingGame();
                System.out.println(String.format("Expected[%d],Actual[%d]", 0, solvingGame.fewestMoves(new int[] {1,2,3},3)));
                System.out.println(String.format("Expected[%d],Actual[%d]", 1, solvingGame.fewestMoves(new int[] {3,2,1}, 3)));
                System.out.println(String.format("Expected[%d],Actual[%d]", 10, solvingGame.fewestMoves(new int[] {5,4,3,2,1}, 2)));
                System.out.println(String.format("Expected[%d],Actual[%d]", 7, solvingGame.fewestMoves(new int[] {7,2,1,6,8,4,3,5}, 4)));
                System.out.println(String.format("Expected[%d],Actual[%d]", -1, solvingGame.fewestMoves(new int[] {3,2,4,1,5}, 4)));
	}
}
class SolvingGame{
	private static class Node{
		int[] arr;
		Node parent;
		static Node of(int[] arr, Node parent){
			Node ret = new Node();
			ret.arr = arr;
			ret.parent = parent;
			return ret;
		}
		public String toString(){
			StringBuilder sb = new StringBuilder();
			for(int i =0; i < arr.length; i++){
				sb.append(arr[i]);
				sb.append("");
			}
			return sb.toString();
		}
		public int hashCode(){
			return Arrays.hashCode(arr);
		}
		public boolean equals(Object object){
			if (!(object instanceof Node)) return false;
			Node other = (Node)object;
			return Arrays.equals(this.arr, other.arr);
		}
	}
	public int fewestMoves(int[] board, int k){
		Queue<Node> bfs = new ArrayDeque<Node>();
		Set<Node> visited = new HashSet<Node>();

		Node root = Node.of(board, null);
		visited.add(root);
		bfs.add(root);
		while(!bfs.isEmpty()){
			Node nextNode = bfs.remove();
			if (isTargetReached(nextNode.arr)){
				return countSteps(nextNode);
			}
			for(int i = 0; i <= nextNode.arr.length-k; i++){
				Node newNode = Node.of(
					newReversed(nextNode.arr, i, k),
					nextNode);
				if (!visited.contains(newNode)){
					bfs.add(newNode);
					visited.add(newNode);
				}
			}
		}
		return -1;
	}

	private int countSteps(Node node){
		if (node == null) return 0;
		int ret = 0;
		while(node.parent != null){
			++ret;
			node = node.parent;
		}
		return ret;
		
	}
	
	private int[] newReversed(int[] arr, int startIncl, int length){
		arr = Arrays.copyOf(arr, arr.length);
		for(int i = startIncl, j = startIncl+length-1; i < j; i++, j--){
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
		return arr;
	}

	private boolean isTargetReached(int[] arr){
		for(int i = 0; i < arr.length; i++){
			if (arr[i] != i+1) return false;
		}
		return true;
	}
}
