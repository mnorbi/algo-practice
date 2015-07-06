public class SumTheRootToLeafPathsInABinaryTree_10_5{
  public static void main(String[]args){
    System.out.println(binSum(treeA(), 0));//, 0));
  }
  static int binSum(Node n, int pfx){//, int aggr){
    if (n == null) return 0;//aggr;
    int v = pfx << 1 | n.dat;
    if (n.l == null && n.r == null){
      System.out.println(Integer.toBinaryString(v));
      return v;//aggr+v;
    }
    return binSum(n.l, v)+binSum(n.r, v);
    //aggr = binSum(n.l, v, aggr);
    //aggr = binSum(n.r, v, aggr);
    //return aggr;
  }
  static Node treeA(){
    return create(1,
      create(0,
        create(0,
          create(0,null,null),
          create(1,null,null)
        ),
        create(1,
          null,
          create(1,
            create(0,null,null),
            null
          )
        )
      ),
      create(1,
        create(0,
          null,
          create(0,
            create(1,
              null,
              create(1,null,null)
            ),
            create(0,null,null)
          )
        ),
        create(0,
          null,
          create(0,null,null)
        )
      )
    );
  }
  static Node create(int dat, Node l, Node r){
    Node ret = new Node();
    ret.dat = dat;
    ret.l = l;
    ret.r = r;
    return ret;
  }
}
class Node{
  int dat;
  Node l, r;
}
