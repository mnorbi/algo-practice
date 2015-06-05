public class Question4_5{
	public static boolean isBinarySearchTreeWithInOrder(Node node){
		List<Integer> al = new ArrayList<Integer>();
		inOrderCollect(node, al);
		return isSorted(al);
	}

	private static boolean isSorted(List<Integer> list){
		if (list == null || list.isEmpty()) return true;
		Iterator<Integer> it = list.iterator();
		for(Integer prev = it.next(); it.hasNext();){
			Integer next = it.next();
			if (next < prev) return false;
			prev = next;
		}
		return true;
	}

	private static void inOrderCollect(Node node, List<Integer> list){
		if (node == null) return;
		inOrderCollect(node.left, list);
		list.add(node.val);
		inOrderCollect(node.right, list);
	}

	public static boolean isBinarySearchTree(Node node){
		if (node == null) return true;
		return 
			isBinarySearchTree(left) && isBinarySearchTree(right) &&
		   	after(left, node) && before(right, node);
	}

	private static boolean after(Node left, Node node){
		if (left == null) return true;
		Integer maxLeft = max(left);
		return maxLeft == null || maxLeft <= node.val;
	}

	private static boolean before(Node right, Node node){
		if (right == null) return true;
		Integer minLeft = min(left);
		return minLeft == null || minLeft >= node.val;
	}

        private static Integer min(Node node){
                if (node == null) return null;
                Integer leftVal = min(left);
                Integer rightVal = min(rightVal);
                return min(node.val, min(leftVal, rightVal));
        }

        private static min(Integer i1, Integer i2){
                if (i1 == null && i2 == null) return null;
                if (i1 == null) return i2;
                if (i2 == null) return i1;
                return Math.min(i1, i2);
        }

	private static Integer max(Node node){
		if (node == null) return null;
		Integer leftVal = max(left);
		Integer rightVal = max(rightVal);
		return max(node.val, max(leftVal, rightVal));
	}

	private static max(Integer i1, Integer i2){
		if (i1 == null && i2 == null) return null;
		if (i1 == null) return i2;
		if (i2 == null) return i1;
		return Math.max(i1, i2);	
	}
}

class Node{
	Node left;
	Node right;
	Integer val;
}
