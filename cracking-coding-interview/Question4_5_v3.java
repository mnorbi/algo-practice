public class Question4_5_v3{
	public static boolean checkBst(Node n){
		return checkBst(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean checkBst(Node n, Integer min, Integer max){
		if (n == null){
			return true;
		}
		if (!between(min, n.val, max)){
			 return false;
		}
		if (!checkBst(n.left, min, n.val)){
			 return false;
		}
		if (!checkBst(n.right, n.val, max)){
			 return false;
		}
		return true;
	}
	
	private static boolean between(Integer lo, Integer x, Integer hi){
		return Integer.compare(lo, x) <= 0 &&
			Integer.compare(x, hi) < 0;
	}
}
class Node{
	int val;
	Node left;
	Node right;
}
