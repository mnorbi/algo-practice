import java.util.*;
public class Question4_3{
	public static void main(String[] args){
		print(buildBinaryTree(new int[] {1,2,3,4,5,6,7,8}));
	}
	private static void print(Node node){
		List<List<Integer>> intLL = new ArrayList<>();
		preorderCollect(node, intLL, 0);
		for(List<Integer> intL : intLL){
			for(Integer i : intL){
				System.out.print(i);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	private static void preorderCollect(Node node, List<List<Integer>> intLL, int level){
		if (node == null) return;
		while (intLL.size() <= level){
			intLL.add(new ArrayList<Integer>());	
		}
		List<Integer> intL = intLL.get(level);
		intL.add(node.val);
		preorderCollect(node.left, intLL, level+1);
		preorderCollect(node.right, intLL, level+1);
	}
	private static Node buildBinaryTree(int[] arr){
		return buildBinaryTree(arr, 0, arr.length-1);
	}
	private static Node buildBinaryTree(int[] arr, int start, int end){
		if (end < start) return null;
		if (start > end) return null;
		if (start == end){
			return new Node(arr[start], null, null);
		}
		int mid = start+(end-start)/2;
		return new Node(arr[mid], buildBinaryTree(arr, start, mid-1), buildBinaryTree(arr, mid+1, end));
	}
	private static class Node{
		final Node left;
		final Node right;
		final int val;
		public Node(int val, Node left, Node right){
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
