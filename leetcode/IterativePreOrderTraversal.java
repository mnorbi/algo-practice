import java.util.*;

class IterativePreOrderTreeTraversal{
  public static void main(String[]args){
  	preOrd(
        new Node(3,
                      new Node(2,
                         new Node(1, null, null),
                         null
                      ),
                      new Node(6,
                          new Node(4,
                             new Node(3, null, null),
                             new Node(5, null, null)
                          ),
                          new Node(7,null, null)
                      )
        )
    );
  }
  public static void preOrd(Node n){
    if (n == null) return;
    Deque<Node> stack = new LinkedList<Node>();
    stack.push(n);
    while(!stack.isEmpty()){
    	n = stack.pop();
    	if (n == null) continue;
    	print(n);
		stack.push(n.right);
		stack.push(n.left);
    }
    System.out.println();
  }
  private static void print(Node n){
    System.out.print(n.v+" ");
  }
}
class Node{
  Node left;
  Node right;
  int v;
  public Node(int v, Node l, Node r){
    this.left = l;
    this.right = r;
    this.v = v;
  }
}
