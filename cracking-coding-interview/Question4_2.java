public class Question4_2{
	public static void main(String[]args){
		new
	}
}
class CommonAncestorWithDownPointers{
	Node findCommonAncestor(Node root, Node a, Node b){
		if (a==b) return a;
		return helperBuggy(root, a, b);
	}

	Node helperBuggy(Node root, Node p, Node q){
		if (root == null || root == p && root == q) return root;

		Node left = null;
		if (root.left != null){
			left = helper(root.left, p, q);
		}

		if (left != null && left != p && left != q) return left;

		Node right = null;
		if (root.right != null){
			right = helper(root.right, p, q);
		}

		if (left == null) return right;

		if (rigth== null) return left;
	
		return root;	
	}
}
class CommonAncestorWithBackPointers{
	Node find(Node a, Node b){
		if (a == b) return a;
		if (a == null || b == null) return null;

		int aDepth = depth(a);
		int bDepth = depth(b);

		if (aDepth > bDepth){
			int tmp = aDepth;
			aDepth = bDepth;
			bDepth = tmp;
			Node tmpNode = a;
			a = b;
			b = tmpNode;
		}

		for(; bDepth > aDepth; --bDepth){
			b = b.parent;
		}
		
		for(; a != null;){ 
			if (a == b) return a;
			a = a.parent;
			b = b.parent;
		}

		return null;
	}
	
}

class Node{
	final int id;
	final Node parent;
	Node(int id){
		this.id = id;
		this.parent = null;
	}
	Node(int id, Node parent){
		this.id = id;
		this.parent = parent;
	}
}
