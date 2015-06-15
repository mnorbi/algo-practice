public class StringSegmentationRuntimeAnalysis{
  public static void main(String[]args){ 
     StringSegmentationRuntimeAnalysis ss = 
       new StringSegmentationRuntimeAnalysis("12345678901234567890", new Dictionary());
     ss.segment();
     System.out.printf("Input length[%d], runtime[%d]\n", ss.chars.length, ss.runtime);
  }

  private int runtime = 0;
  private Dictionary dict;
  private char[] chars;
  private Boolean[] dp;

  StringSegmentationRuntimeAnalysis(String s, Dictionary dict){ 
    this.dict = dict;
    this.chars = s.toCharArray();
    this.runtime = 0;
    this.dp = new Boolean[chars.length];
  }

  boolean segment(){
    return segment(0);
  }

  /**
   Key insight: if we use DP, there
   are only N suffixes which could be cached
   for later.
  **/
  boolean segment(int from){
    ++runtime;
    if (from >= chars.length){
      return true;
    } 
    if (dp[from] != null){
      return dp[from];
    }
    for(int i = from+1; i <= chars.length; ++i){
      runtime += i-from;//using Rabin Karp rolling hash, runtime could be linear instead of current quadratic, but we need to preprocess the dictionary
      if (dict.contains(chars, from,i)){
        if (segment(i)){
          return true;
        }
      }
    }
    dp[from] = false;

    return dp[from];
  }

  
}
class Dictionary{
  boolean contains(char[] arr, int lo, int hi){
    if (lo+1 >= arr.length) return false;
    return false;
  }
}
