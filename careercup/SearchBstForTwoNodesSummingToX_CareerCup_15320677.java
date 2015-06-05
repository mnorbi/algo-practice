import java.util.*;
/**
	Given a BST and a value x. Find two nodes in the tree whose sum is equal x. Additional space: O(height of the tree). It is not allowed to modify the tree

**/
class SearchBstForTwoNodesSummingToX_CareerCup_15320677{
        public static void main(String[]args){
                int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
                System.out.println(findMatchingPairWithBst(arr, 3));
        }
        private static Pair findMatchingPairWithBst(int[] arr, int target){
                if (arr == null || arr.length == 0) {
                        return null;
                }
                Node root = Node.parse(arr);
                Deque<Node> leftStack = new LinkedList<Node>();
                Deque<Node> rightStack = new LinkedList<Node>();
                fillLeft(root, leftStack);
                fillRight(root, rightStack);

                Node left = nextIncreasing(leftStack);
                Node right = nextDecreasing(rightStack);
                while(left != right){
                        int sum = left.val + right.val;

                        if (sum < target){
                                left = nextIncreasing(leftStack);
                        } else if (sum > target){
                                right = nextDecreasing(rightStack);
                        } else {
                                return new Pair(left.val, right.val);
                        }
                }
                return null;
        }
        private static void fillLeft(Node node, Deque<Node> stack){
                if (node == null) {
                        return;
                }
                while(node != null){
                        stack.push(node);
                        node = node.left;
                }
        }
        private static void fillRight(Node node, Deque<Node> stack){
                if (node == null){
                        return;
                }
                while(node != null){
                        stack.push(node);
                        node = node.right;
                }
        }
        private static Node nextIncreasing(Deque<Node> stack){
                Node next = stack.pop();
                fillLeft(next.right, stack);
                return next;
        }
        private static Node nextDecreasing(Deque<Node> stack){
                Node next = stack.pop();
                fillRight(next.left, stack);
                return next;
        }
}
class Pair{
        int x, y;

        Pair(int x, int y){
                this.x = x;
                this.y = y;
        }
        public String toString(){
                return String.format("(%d,%d)", x, y);
        }
}
class Node{
        int val;
        Node left;
        Node right;
        static Node parse(int[] arr){
                return parse(arr, 0, arr.length-1);
        }
        static Node parse(int[] arr, int lo, int hi){
                if (lo > hi) {
                        return null;
                }

                int mid = lo + (hi-lo)/2;
                Node node = new Node();
                node.val = arr[mid];
                node.left = parse(arr, lo, mid-1);
                node.right = parse(arr, mid+1, hi);
                return node;
        }
}
