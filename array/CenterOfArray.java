/*
    Natural division of: 0 1 2 3 4 
 	 	  left:  0 1 
	 	  right: 3 4 
      Left+ division of: 0 1 2 3 4 
	 	  left:  0 1 2 
	 	  right: 3 4 
     Right+ division of: 0 1 2 3 4 
	 	  left:  0 1 
	 	  right: 2 3 4 
 Cut out center left of: 0 1 2 3 4 
	 	  left:  0 1 
	 	  right: 3 4 
Cut out center right of: 0 1 2 3 4 
	 	  left:  0 1 
	 	  right: 3 4 
    Natural division of: 0 1 2 3 4 5 
	 	  left:  0 1 2 
	 	  right: 3 4 5 
      Left+ division of: 0 1 2 3 4 5 
	 	  left:  0 1 2 
	 	  right: 3 4 5 
     Right+ division of: 0 1 2 3 4 5 
	 	  left:  0 1 2 
	 	  right: 3 4 5 
 Cut out center left of: 0 1 2 3 4 5 
	 	  left:  0 1 
	 	  right: 3 4 5 
Cut out center right of: 0 1 2 3 4 5 
	 	  left:  0 1 2 
	 	  right: 4 5 

*/
public class CenterOfArray{
  public static void main(String[]args){ 
    int[] arr01234 = new int[]{0,1,2,3,4};
    int[] arr012345= new int[]{0,1,2,3,4,5};

    printWithDivisions(arr01234);
    printWithDivisions(arr012345);
  }
  private static void printWithDivisions(int[] arr){
    int N = arr.length;
    //Natural division divides an array in two equal halves. The central positions, if exists, is not included.
    division("Natural division"    , arr, N/2,       (N+1)/2);
    //Left+ division is splitting the array so that the center, if exists, is included in the left half of an array, otherwise it is the same as natural division.
    division("Left+ division"      , arr, (N+1)/2,   (N+1)/2);
    //Right+ division is splitting the array so that the center, if exists, is included in the right half of an array, otherwise it is the same as natural division.
    division("Right+ division"     , arr, N/2,       N/2    );
    //Cut-out center division is splitting the array so that one element at the center is always excluded from both
    //halves. In case we have no center, we exclude the element closest to the physical center of the array, either
    //left or right.
    division("Cut out center left" , arr, (N+1)/2-1, (N+1)/2);
    division("Cut out center right", arr, N/2,       N/2+1  );
  }
  private static void division(String description, int[] arr, int leftCut, int rightCut){
    int N = arr.length;
    print(String.format("%20s %s: ", description,"of"), arr, 0, N);
    print("\n 	 	  left:  ", arr, 0, leftCut);
    print("\n 	 	  right: ", arr, rightCut, N);
    System.out.println();
  }
  private static void print(String description, int[]arr, int lo, int hi){
    System.out.printf(description);
    for(int i = lo; i < hi; ++i){
      System.out.print(arr[i]);
      System.out.print(" ");
    }
  }
}
