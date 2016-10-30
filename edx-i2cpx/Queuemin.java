import java.io.*;
import java.util.*;

public class Queuemin{
  public static void main(String[]args) throws IOException {
    try(
            BufferedReader in = new BufferedReader(new FileReader("queuemin.in"));
            BufferedWriter out = new BufferedWriter(new FileWriter("queuemin.out"));
    ){
      int n = Integer.valueOf(in.readLine());
      QueueMin<Integer> queueMin = new QueueMin<>((i1,i2)->i1.compareTo(i2));
      while(n-->0){
        String[] sarr = in.readLine().split(" ");
        if ("+".equals(sarr[0])){
          queueMin.addFirst(Integer.valueOf(sarr[1]));
        } else if ("-".equals(sarr[0])){
          queueMin.removeLast();
        } else {
          out.write(""+queueMin.getMin());
          out.newLine();
        }
      }
    }
  }

  private static class QueueMin<T>{
    Deque<T> queue = new ArrayDeque<>();
    Deque<T> mins = new ArrayDeque<>();
    Comparator<T> cmp;

    QueueMin(Comparator<T> cmp){
      this.cmp = cmp;
    }

    void addFirst(T val){
      queue.addFirst(val);
      while(mins.peekFirst() != null && cmp.compare(mins.peekFirst(), val) > 0){
        mins.removeFirst();
      }
      mins.addFirst(val);
    }

    T removeLast(){
      T val = queue.removeLast();
      if (cmp.compare(mins.peekLast(), val) == 0){
        mins.removeLast();
      }
      return val;
    }

    T getMin(){
      return mins.peekLast();
    }
  }

}
