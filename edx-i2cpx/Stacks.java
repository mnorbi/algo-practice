import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;

public class Stacks {
    public static void main(String[] var0) throws IOException {
         try(BufferedReader in = new BufferedReader(new FileReader("stacks.in"));
             BufferedWriter out = new BufferedWriter(new FileWriter("stacks.out"));){
                int N = Integer.valueOf(in.readLine()).intValue();
                String[] line = in.readLine().split(" ");
                out.write(""+threeVarSolution(N, line));
        }
    }

    private static int threeVarSolution(int N, String[] arr){
        if (N == 0) return 0;
        int a = 0;
        int maxH = 0;
        int width = 0;
        for(;a<N && arr[a].equals("0");++a){
            ++maxH;
        }
        if (maxH != 0) { ++width; }
        else { maxH = 1; }
        int toFill = 0;
        for(;a<N;++a){
            if (!arr[a].equals("0")){
                toFill += maxH-1;
                ++width;
            } else {
                if (toFill > 0){ --toFill; }
                else {
                    ++maxH;
                    toFill = width-1;
                }
            }
        }
        return maxH;
    }

    private static int stackSolution(int N, String[] line){
      ArrayDeque<Node> deq = new ArrayDeque<>();
      for(int a = 0; a < N;) {
          if(line[a].equals("0")) {
              int cnt = 0;
              while(a < N && line[a].equals("0")) {
                  a++;
                  ++cnt;
              }

              if(deq.isEmpty()) {
                  int height = cnt;
                  int size = 1;
                  deq.push(new Stacks.Node(size, height));
              } else {
                  int height = deq.peekLast().height;
                  int size = deq.peekLast().size;
                  deq.removeLast();

                  while(!deq.isEmpty()) {
                      int delta = (deq.peekLast().height - height) * size;
                      if(delta > cnt) {
                          break;
                      }

                      Stacks.Node pre = deq.removeLast();
                      height = pre.height;
                      size += pre.size;
                      cnt -= delta;
                  }

                  if(cnt % size != 0) {
                      deq.addLast(new Stacks.Node(cnt % size, cnt / size + height + 1));
                  }

                  deq.addLast(new Stacks.Node(size - cnt % size, cnt / size + height));
              }
          } else {
              int cnt = 0;
              while(a < N && !line[a].equals("0")) {
                  a++;
                  ++cnt;
              }

              int size = cnt;
              int height = 1;
              deq.addLast(new Stacks.Node(size, height));
          }
      }

      return deq.peekFirst().height;
    }

    private static class Node {
        int size;
        int height;

        private Node(int size, int height) {
            this.size = size;
            this.height = height;
        }
    }
}
