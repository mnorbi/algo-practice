import java.util.*;
/**
Design an optimised algorithm to solve snakes and ladders with the least amount of roles possible under the assumption that you get whatever role you want when you role the dice.
Problems:
  identify correctly the nodes
  no need for dijkstra, BFS is enough, distance is always one
**/
class SnakesAndLaddersWithLeastDiceRoll_CareerCup_5739350128066560{
  public static void main(String[]args){
    System.out.println(
      findMinDiceRoll(
        1, 30,
        new int[][]{ {27,1}, {21,9},{17,4}, {19,7}, {11,26},
                          {3,22},{5,8},{20,29} }));
  }
  private static int findMinDiceRoll(int start, int end, int[][] artifacts){
    Map<Integer, Integer> artifactMap = artifactMapFrom(artifacts);
    BitSet visited = new BitSet();
    Deque<Node> queue = new LinkedList<Node>();
    visited.set(start);
    queue.addLast(new Node(1, 0));
    while(!queue.isEmpty()){
      Node node = queue.removeFirst();
      if (node.id == end) return node.len;
      for(int i = 1; i <= 6; ++i){
        int nextId = node.id+i;
        if (nextId < start || nextId > end || visited.get(nextId)) continue;
        visited.set(nextId);
        if (!artifactMap.containsKey(nextId)){
          queue.addLast(new Node(nextId, node.len+1));
        } else {
          int pairId = artifactMap.get(nextId);
          visited.set(pairId);
          queue.addLast(new Node(pairId, node.len+1));
        }
      }
    }
    return -1;
  }
  private static Map<Integer, Integer> artifactMapFrom(int[][] artifacts){
    Map<Integer, Integer> ret = new HashMap<Integer, Integer>();
    for(int i = 0; i < artifacts.length; ++i){
      ret.put(artifacts[i][0], artifacts[i][1]);
    }
    return ret;
  }
}
class Node{
  int id, len;
  Node(int id, int len){
    this.id = id;
    this.len = len;
  }
}
