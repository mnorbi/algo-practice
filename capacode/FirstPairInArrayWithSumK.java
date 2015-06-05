import java.util.*;
class FindAPairInArrayWithSumK{
  public static void main(String[] args){
    TestCase[] testCases = new TestCase[] {
      new TestCase(7, new int[]{8, 3, 2, 5, 1}, true),
      new TestCase(16, new int[]{8, 3, 2, 5, 1}, false),
      new TestCase(12, new int[]{8, 3, 2, 5, 1}, false),
      new TestCase(8, new int[]{8, 3, 2, 5, 1}, true),
      new TestCase(10, new int[]{5}, false),
      new TestCase(0, new int[]{}, false)
    };
    for(TestCase testCase : testCases){
      testCase.verify(isPairSummingToKWithSort(testCase.k, testCase.arr));
      testCase.verify(isPairSummingToKWithHashMap(testCase.k, testCase.arr));
    }
  }
  public static boolean isPairSummingToKWithSort(int k, int[] arr){
    Arrays.sort(arr);
    for(int i = 0, j = arr.length-1; i < j;){
      int sum = arr[i]+arr[j];
      if(sum < k){
        ++i;
        continue;
      } else if (sum > k){
        --j;
        continue;
      } else {
        return true;
      }
    }
    return false;
  }
  public static boolean isPairSummingToKWithHashMap(int k, int[] arr){
    Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    for(int i : arr){
      Boolean duplicate = Boolean.FALSE;
      if (map.containsKey(i)){
        duplicate = Boolean.TRUE;
      }
      map.put(i, duplicate);
    }
    for(int i : arr){
      int candidate = k-i;
      if (map.containsKey(candidate)){
        if (candidate != i || hasDuplicates(map, i)){
         return true;
        }
      }
    }
    return false;
  }
  static boolean hasDuplicates(Map<Integer, Boolean> map, int i){
    return map.containsKey(i) && map.get(i);
  }

}
class TestCase{
  int k;
  int[] arr;
  boolean expected;
  TestCase(int k, int[] arr, boolean expected){
    this.k = k;
    this.arr = arr;
    this.expected = expected;
  }
  void verify(boolean actual){
    assert expected == actual : String.format("testcase[%s],expected[%s],actual[%s]",this,expected,actual);
  }
  public String toString(){
    return String.format("%s,int[%s]",k,toString(arr));
  }
  private String toString(int[] arr){
    if (arr == null || arr.length == 0) return "";
    StringBuilder sb = new StringBuilder();
    sb.append(arr[0]);
    for(int i = 1; i < arr.length; ++i){
      sb.append(", ");
      sb.append(arr[i]);
    }
    return sb.toString();
  }
}

