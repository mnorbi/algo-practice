class AdvancingThroughAnArray{
  public static void main(String[]args){
    System.out.println(isReachable(new int[]{3,3,1,0,2,0,1}));
    System.out.println(isReachable(new int[]{3,2,0,0,2,0,1}));
  }
  static boolean isReachable(int[] arr){
    int front = 0, cur = 0;
    while(cur < arr.length){
      front = Math.max(front, cur+arr[cur]);
      if (front <= cur) { break; }
      ++cur;
    }
    return cur >= arr.length-1;
  }
}
