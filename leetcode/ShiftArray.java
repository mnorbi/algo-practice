import java.util.*;
class ShiftArray{
  public static void main(String[]args){
    print(rotate(new char[]{'a','b','c','d','e','f','g'}, 3, Dir.LEFT));
    print(rotate(new char[]{'a','b','c','d','e','f','g'}, 3, Dir.RIGHT));
  }
  public static char[] rotate(char[] arr, int k, Dir dir){
    if (arr == null || arr.length == 0) return arr;
    k = k%arr.length;
    if (k == 0) return arr;
    if (dir == Dir.LEFT){
      reverse(arr, 0, k);
      reverse(arr, k, arr.length);
      reverse(arr, 0, arr.length);
    } else {
    	reverse(arr, 0, arr.length);
    	reverse(arr, 0, k);
    	reverse(arr, k, arr.length);
    }
    return arr;
  }
  private static void reverse(char[] arr, int from, int to){
    for(int i = from, j = to-1; i < j; ++i, --j){
      swap(arr, i, j);
    }
  }

  private static void swap(char[] arr, int i, int j){
    char tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
  private static void print(char[] arr){
    for(char c: arr){
      System.out.printf("%s ", c);
    }
    System.out.println();
  }
}
enum Dir{
	LEFT, RIGHT
}
