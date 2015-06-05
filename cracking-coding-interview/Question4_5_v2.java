public class Question4_5_v2{
	public static boolean inOrderBstCheck(Node node){
		Result res = inOrderBstCheck(node, new MutableInt(Integer.MIN_VALUE));
		return res.ans;
	}

	private static boolean inOrderBstCheck(Node node, Mutable ioPrev){
		if (node == null) return true;

		if (!inOrderBstCheck(node.left, ioPrev) return false;

		if (node.val <= ioPrev.val) return false;
		ioPrev.val = node.val;

		return !inOrderBstCheck(node.right, ioPrev);
	}

}
class MutableInt{
	int val;
	MutableInt(int val){
		this.val = val;
	}
}
class Node{
	Node left;
	Node right;
	int val;
}
