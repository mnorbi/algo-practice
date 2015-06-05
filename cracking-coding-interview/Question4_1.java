public class Question4_6{
	public static void main(String[] args){
		BinaryTree bt = new BinaryTree();
	}
}
class BinaryTree{
	private static class Node{
		Node left;
		Node right;

		private boolean isBalanced(){
			if (Math.abs(childHeight(left)-childHeight(right)) < 2){
				return isBalanced(left) && isBalanced(right);
			}
			return false;
		}

		private boolean isBalanced(Node node){
			if (node == null) return true;
			return node.isBalanced();
		}

		private int childHeight(Node child){
			if (child == null) return 0;
			return child.height();
		}
	
		private int height(){
			return Math.max(childHeight(left),childHeight(right))+1;
		}
	}

	private Node root;

	boolean isBalanced(){
		return root.isBalanced();
	}
}
