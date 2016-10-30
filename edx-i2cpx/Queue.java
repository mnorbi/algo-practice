import java.io.*;

public class Queue{
  public static void main(String[]args) throws IOException {
    try(
            BufferedReader in = new BufferedReader(new FileReader("queue.in"));
            BufferedWriter out = new BufferedWriter(new FileWriter("queue.out"));
    ){
      int n = Integer.valueOf(in.readLine());
      Deque<String> queue = new Deque<>();
      while(n-->0){
        String[] sarr = in.readLine().split(" ");
        if ("+".equals(sarr[0])){
          queue.push(sarr[1]);
        } else {
          out.write(queue.pop());
          out.newLine();
        }
      }
    }
  }
  private static class Deque<T>{
    int tail = 0, head = 0;
    int cnt = 0;
    T[] arr = (T[])new Object[1];
    public void push(T val){
      if (cnt == arr.length){
        grow();
      }
      ++cnt;
      arr[head] = val;
      head = (head+1)%arr.length;
    }
    public T pop(){
      if (cnt == 0) return null;
      T ret = arr[tail];
      arr[tail] = null;
      tail = (tail+1)%arr.length;
      --cnt;
      if (cnt < arr.length/3){
        shrink();
      }
      return ret;
    }
    private void grow(){
      T[] newArr = (T[])new Object[arr.length*2];
      replaceArr(newArr);
    }
    private void shrink(){
      T[] newArr = (T[])new Object[arr.length/3];
      replaceArr(newArr);
    }
    private void replaceArr(T[] newArr){
      for(head = 0; head < cnt; tail = (tail+1)%arr.length, ++head){
        newArr[head] = arr[tail];
      }
      arr = newArr;
      tail = 0;
      head = head%arr.length;
    }
  }
}

