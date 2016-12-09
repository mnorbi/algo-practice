import java.util.*;

public class Columns_of_Binary_Tree_5749533368647680{
  public static void main(String[]args){
/*    Node root =
            new Node(6,
                    new Node(3,
                            new Node(5,
                                    new Node(9,null,null),
                                    new Node(2,
                                            null,
                                            new Node(7,null,null)
                                    )
                            ),
                            new Node(1,null,null)
                    ),
                    new Node(4,
                            null,
                            new Node(0,
                                    new Node(8,null,null),
                                    null
                            )
                    )
            );
*/
    Node root =
	new Node(1,
          new Node(2,
            new Node(4,null,null),
            new Node(5,null,null)
          ),
          new Node(3,
            new Node(6,null,null),
            new Node(7,null,null)
          )
        );
    Column mid = new Column();
    mid.nodes.add(root);
    Deque<Column> deq = new ArrayDeque<>();
    deq.addLast(mid);
    while(!deq.isEmpty()){
      Column col = deq.removeFirst();
      Node node = col.nodes.get(col.nodes.size()-1);
      if (node.l != null){
        col.l = col.l == null ? new Column() : col.l;
        col.l.r = col;
        col.l.nodes.add(node.l);
        deq.addLast(col.l);
      }
      if (node.r != null){
        col.r = col.r == null ? new Column() : col.r;
        col.r.l = col;
        col.r.nodes.add(node.r);
        deq.addLast(col.r);
      }
    }

    Column col = mid;
    while(col.l != null){
      col = col.l;
    }
    while(col != null){
      System.out.print("|");
      col.printNodes();
      col = col.r;
    }
    System.out.println();
  }
}
class Column{
  LinkedList<Node> nodes = new LinkedList<>();
  Column l, r;
  Node node;

  void printNodes(){
    for(Node node : nodes){
      System.out.print(node.val);
      System.out.print(" ");
    }
  }
}
class Node{
  int val;
  Node l, r;
  Node(int val, Node l, Node r){
    this.val = val;
    this.l = l;
    this.r = r;
  }
}

