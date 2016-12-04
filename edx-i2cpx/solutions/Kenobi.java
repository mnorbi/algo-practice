import java.util.*;
import java.io.*;
public class Kenobi{
  public static void main(String[]args) throws IOException{
    withArrayDeque(args);
  }
  public static void withArrayDeque(String[]args) throws IOException{
    try(
      BufferedReader in = new BufferedReader(new FileReader("kenobi.in"));
      FileWriter out = new FileWriter("kenobi.out");
    ){
      int N = Integer.valueOf(in.readLine());
      ArrayDeque<Integer> left = new ArrayDeque<>();
      ArrayDeque<Integer> right = new ArrayDeque<>();
      while(N-->0){
        String[] line = in.readLine().split(" ");
        if (line[0].equals("add")){
          right.addLast(Integer.valueOf(line[1]));
          if (right.size() > left.size()+1){
            left.addLast(right.removeFirst());
          }
        } else if (line[0].equals("take")){
          right.removeLast();
        } else {
          ArrayDeque<Integer> tmp = left;
          left = right;
          right= tmp;
        }
        if (left.size() > right.size()){
          right.addFirst(left.removeLast());
        }
      }
      out.write(""+(left.size()+right.size()));
      out.write("\n");
      boolean empty = true;
      Iterator<Integer> it= left.iterator();
      if (it.hasNext()){
        out.write(it.next().toString());
      } 
      while(it.hasNext()){
        out.write(" ");
        out.write(it.next().toString());
      }
      it = right.iterator();
      if (left.size() == 0 && it.hasNext()){
        out.write(it.next().toString());
      }
      while(it.hasNext()){
        out.write(" ");
        out.write(it.next().toString());
      }
    }
  }
  public static void withLinkedList(String[]args) throws IOException{
    try(
      BufferedReader in = new BufferedReader(new FileReader("kenobi.in"));
      FileWriter out = new FileWriter("kenobi.out");
    ){
      int N = Integer.valueOf(in.readLine());
      Node head = new Node(-1);
      Node tail = head;
      Node mid = head;
      int size = 0;
      while(N-->0){
        String[] line = in.readLine().split(" ");
        if (line[0].equals("add")){
          Node next = new Node(Integer.valueOf(line[1]));
          tail.next = next;
          next.prev = tail;
          tail = next;
          ++size;
          if (size % 2 == 0){
            mid = mid.next;
          }
        } else if (line[0].equals("take")){
          if (size == 0) { continue; }
          if (size % 2 == 0){
            mid = mid.prev;
          }
          tail = tail.prev;
          tail.next = null;
          --size;
        } else {
          if (size <= 1) { continue; }
          if (size % 2 == 0){
            Node nR = head.next;
            Node nL = mid.next;
            Node nT = mid;
            Node nM = tail;

            nL.prev = head;
            head.next = nL;

            nM.next = nR;
            nR.prev = nM;

            nT.next = null;

            mid = nM;
            tail = nT;
          } else {
            Node nR = head.next;
            Node nL = mid.next;
            Node nT = mid;
            Node nM = tail.prev;

            head.next = nL;
            nL.prev = head;
            
            nM.next.next = nR;
            nR.prev = nM.next;

            nT.next = null;

            mid = nM;
            tail = nT;
          }
        }
      }
      out.write(""+size);
      out.write("\n");

      Node cur = head;
      if (cur.next != null){
        out.write(""+cur.next.val);
      } else { return; }
      for(cur = cur.next; cur.next != null; cur = cur.next){
        out.write(" ");
        out.write(""+cur.next.val); 
      }
    }
  }
  private static class Node{
    Node next;
    Node prev;
    int val;
    Node(int val){
      this.val = val;
    }
  }
}
