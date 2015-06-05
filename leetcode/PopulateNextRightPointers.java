import java.util.*;

class PopulateNextRightPointers{
  public static void main(String[]args){
    /**
        _1_
       /   \
      2     3
     / \   / \
    4   5  6  7
       / \   / \
       8 9  10 11
    **/
    Node root =
      new Node(1,
        new Node(2,
          new Node(4),
          new Node(5,
            new Node(8),
            new Node(9)
          )
        ),
        new Node(3,
          new Node(6),
          new Node(7,
            new Node(10),
            new Node(11)
          )
        )
      );
    fillNextRights(root);
    printLevels(root);
  }
  public static void fillNextRights(Node root){
    Node level = root;
    while(level != null){
      fillNextLevelBelow(level);
      level = nextLevel(level);
    }
  }

  private static void fillNextLevelBelow(Node levelHead){
      Node cursor = levelHead;
      while(cursor != null){
        connect(cursor.leftChild, cursor.rightChild);
        Node next = nextSiblingWithChild(cursor);
        connect(rightMostChild(cursor), leftMostChild(next));
        cursor = next;
      }
  }

  private static void connect(Node a, Node b){
    if (a == null) return;
    a.nextRight = b;
  }

  private static Node nextSiblingWithChild(Node node){
    if (node == null) return null;
    Node cursor = node.nextRight;
    while(!(cursor == null || hasChild(cursor))){
      cursor = cursor.nextRight;
    }
    return cursor;
  }

  private static boolean hasChild(Node node){
    if (node == null) return false;
    if (node.leftChild == null && node.rightChild == null) return false;
    return true;
  }

  private static Node rightMostChild(Node node){
    if (node == null) return null;
    if (node.rightChild != null) return node.rightChild;
    return node.leftChild;
  }

  private static Node leftMostChild(Node node){
    if (node == null) return null;
    if (node.leftChild != null) return node.leftChild;
    return node.rightChild;
  }

  private static Node nextLevel(Node levelHead){
    Node child = leftMostChild(levelHead);
    if (child != null) return child;
    return leftMostChild(
                 nextSiblingWithChild(levelHead));
  }
  private static void printLevels(Node level){
    while(level != null){
      printLevel(level);
      level = nextLevel(level);
    }
  }
  private static void printLevel(Node level){
    Node cursor = level;
    while(cursor != null){
      System.out.print(cursor.val+" ");
      cursor = cursor.nextRight;
    }
    System.out.println();
  }
}
class Node{
  int val;
  Node leftChild;
  Node rightChild;
  Node nextRight;
  Node(int val, Node left, Node right){
    this.val = val;
    this.leftChild = left;
    this.rightChild = right;
  }
  Node(int val){
    this.val = val;
  }
}
