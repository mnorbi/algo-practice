import java.util.*;
class BinaryTreeTraversalWithoutRecusrion{
  public static void main(String[]args){
    Node node =
      new Node(1,
        new Node(2,
          new Node(3,null,null),
          new Node(4, null, null)),
        new Node(5,
          null,
          new Node(6,null,null)));
    System.out.println("Preorder:");
    preorder(node);
    System.out.println("Postorder:");
    postorder(node);
    System.out.println("Postorder with two stacks:");
    postOrderWithTwoStacks(node);
    System.out.println("Inorder:");
    inorder(node);
  }
  public static void preorder(Node node){
    if (node == null) return;
    Deque<Node> stack = new ArrayDeque<>();
    stack.push(node);
    while(!stack.isEmpty()){
      Node n = stack.pop();
      print(n);
      if (n.right != null){
        stack.push(n.right);
      }
      if (n.left != null){
        stack.push(n.left);
      }
    }
  }
  public static void postorder(Node node){
    if (node == null) return;
    Node prev = node;
    Deque<Node> stack = new ArrayDeque<>();
    stack.push(node); 
    while(!stack.isEmpty()){
      Node n = stack.peek();
      if (n.left != null && n.left != prev && n.right != prev){
        stack.push(n.left);
      } else if (n.right != null && n.right != prev){
        stack.push(n.right);
      } else {
        print(stack.pop());
      }
      prev = n;
    }
  }
  public static void postOrderWithTwoStacks(Node node){
    if (node == null) return;
    Deque<Node> stack = new ArrayDeque<>();
    Deque<Node> ret = new ArrayDeque<>();
    stack.push(node);
    while(!stack.isEmpty()){
      Node n = stack.pop();
      ret.push(n);
      if (n.left != null){
        stack.push(n.left);
      }
      if (n.right != null){
        stack.push(n.right);
      }
    }
    while(!ret.isEmpty()){
      print(ret.pop());
    }
  }
  public static void inorder(Node node){
    Deque<Node> deq = new ArrayDeque<>();
    while(!deq.isEmpty() || node != null){
      if (node == null){
        node = deq.pop();
        print(node);
        node = node.right;
      } else {
        deq.push(node);
        node = node.left;
      }
    }
  }
  static void print(Node n){
    System.out.println(n.toString());
  }
}
class Node{
  Node left, right;
  int val;
  Node(int val, Node left, Node right){
    this.left = left;
    this.right = right;
    this.val = val;
  }
  public String toString(){
    return ""+val;
  }
}
