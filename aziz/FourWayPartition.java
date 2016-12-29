import java.util.*;
class FourWayPartition{
  public static void main(String[]args){
    System.out.println(
      Arrays.toString(
        fourWayPartition(
          new int[]{},
          new int[]{1,2,3,4}
        )
      )
    );
  }

  static int[] fourWayPartition(int[] arr, int[] key){
    int head = 0, one = -1, two = arr.length, three = arr.length;

    while(head < two){
      if (arr[head] == key[0]) { swap(arr, ++one, head++); }
      else if (arr[head] == key[1]) { ++head; }
      else if (arr[head] == key[2]) { swap(arr, --two, head); }
      else { swap(arr, head, --two); swap(arr, two, --three); }
    }
    return arr;
  }
  static void swap(int[] arr, int i, int j){
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
