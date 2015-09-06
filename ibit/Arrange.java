class Arrange{
  public static void main(String[]args){
    print(arrange(new int[]{4, 0, 2, 1, 3}));
  }
  static int[] arrange(int[] arr){
    int N = arr.length;
    for(int i = 0; i < arr.length; ++i){
      if (arr[i] < 0) continue;
      int orig = arr[i];
      int prev = i;
      for(;;){
        int cur = arr[prev];
        int next = arr[cur];
        arr[prev] = next-N;
        if (next == i){
          arr[cur] = orig-N;
          break;
        }
        prev = cur;
      }
    }
    for(int i = 0; i < arr.length; ++i){
      arr[i] += N;
    }
    return arr;
  }
  static void print(int[]arr){
    for(int i = 0; i < arr.length; ++i){
      System.out.print(arr[i]);
      System.out.print(" ");
    }
    System.out.println();
  }
}
