class GenPerms{
  public static void main(String[]args){
    genPerm(new int[]{1, 2, 3}, 0);
  }
  static void genPerm(int[] arr, int i){ 
     if (i >= arr.length){
       print(arr);
       return;
     }
     for(int j = i; j < arr.length; ++j){
       swap(arr, i, j);
       genPerm(arr, i+1);
       swap(arr, i, j);
     }
  }
  static void print(int[]arr){
    for(int i : arr){
      System.out.print(i +" ");
    }
    System.out.println();
  }
  static void swap(int[]arr, int i, int j){
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
