class TrimTree{
  public static void main(String[]args){
    Node result = trim(
            new Node(6,
                    new Node(3,
                            new Node(2, new Node(1),null),
                            new Node(4, null,new Node(5))),
                    new Node(9,
                            new Node(8, new Node(7),null),
                            new Node(10))),
            0,0);
    int i = 0;
  }
  static Node trim(Node node, int min, int max){
    if (min > max){
      int tmp = min;
      min = max;
      max = tmp;
    }
    return trim(node, min, max, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  static Node trim(Node node, int min, int max, int treeRangeMin, int treeRangeMax){
    if (node == null) { return null; }
    if (min <= treeRangeMin && treeRangeMax <= max) { return node; }
    if (node.val < min) { return trim(node.r, min, max, node.val, treeRangeMax); }
    if (node.val > max) { return trim(node.l, min, max, treeRangeMin, node.val); }
    node.l = trim(node.l, min, max, treeRangeMin, node.val);
    node.r = trim(node.r, min, max, node.val, treeRangeMax);
    return node;
  }
}
class Node {
  int val;
  Node l, r;
  Node(int val){
    this(val, null,null);
  }
  Node(int val, Node l, Node r){
    this.val = val;
    this.l = l;
    this.r = r;
  }
}
