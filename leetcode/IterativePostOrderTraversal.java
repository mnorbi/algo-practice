import java.util.*;

class IterativePostOrderTreeTraversal{
  public static void main(String[]args){
  	postOrd(
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
  public static void postOrd(Node n){
    if (n == null) return;
    Deque<Node> stack = new LinkedList<Node>();
    Node prev = n;
    for(;;){
      if (n.left != null && n.right != prev && n.left != prev){
        stack.push(n);
        n = n.left;
        continue;
      }
      if (n.right != null && n.right != prev){
        stack.push(n);
        n = n.right;
        continue;
      }

      print(n);
      prev = n;

      if (stack.isEmpty()) break;
      n = stack.pop();
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
