import java.util.*;

class IterativeInOrderTreeTraversal{
  public static void main(String[]args){
  	inOrd(
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
  public static void inOrd(Node n){
    if (n == null) return;
    Deque<Node> stack = new LinkedList<Node>();
    while(!stack.isEmpty() || n != null){
    	if (n != null){
    		stack.push(n);
    		n = n.left;
    		continue;
    	}
    	n = stack.pop();
    	print(n);
    	n = n.right;
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
