public class LoopRuntime{
  public static void main(String[]args){
    printLoopRuntimeFor(10,    8);
    printLoopRuntimeFor(100,   4);
    printLoopRuntimeFor(10000, 2);
  }
  static void printLoopRuntimeFor(int cols, int rows){
    int N = cols;
    int[] cursor = new int[rows];
    int i = 0;
    long start = System.nanoTime();
    while(i < cursor.length){
      cursor[i] = (cursor[i]+1)%N;
      if (cursor[i] == 0){
        ++i;
      } else {
        i = 0;
      }
    }
    long end = System.nanoTime();
    long delta = end-start;
    System.out.printf("Runtime of loop O(%d**%d) = %d ns (%f ms)\n",N, cursor.length, delta, delta/1000000.0);
  }
}
