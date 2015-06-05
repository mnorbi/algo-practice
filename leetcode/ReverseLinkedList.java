import java.util.*;

class ReverseLinkedList{
  public static void main(String[]args){
    Node root =
      new Node(1,
        new Node(2,
          new Node(3,
            new Node(4,
              new Node(5,
                new Node(6,
                  new Node(7,
                    new Node(8,
                      new Node(9,
                        new Node(10,null)
                      )
                    )
                  )
                )
              )
            )
          )
        )
      );
    print(reverseIter(root));
  }
  private static void print(Node node){
    while(node != null){
      System.out.print(node.val+" ");
      node = node.next;
    }
    System.out.println();
  }
  public static Node reverseRec(Node node){
    if (node == null){
      return null;
    } else if (node.next == null){
      return node;
    } else {
      Node ret = reverseRec(node.next);
      node.next.next = node;
      node.next = null;
      return ret;
    }
  }
  public static Node reverseIter(Node node){
    if (node == null) return null;
    if (node.next == null) return node;
    Node prev = null;
    while(node != null){
      Node next = node.next;
      node.next = prev;
      prev = node;
      node = next;
    }
    return prev;
  }
}
class Node{
  int val;
  Node next;
  Node(int val, Node next){
    this.val = val;
    this.next = next;
  }
}
