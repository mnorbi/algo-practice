public class MaxPalindromeSubseq{
  public static void main(String[] args){
    System.out.println(maxPalSubseq(""));
    System.out.println(maxPalSubseq("a"));
    System.out.println(maxPalSubseq("agbdba"));
  }
  public static int maxPalSubseq(String string){
    if (string == null || string.length() == 0) return 0;
    if (string.length() == 1) return 1;
    char[] arr = string.toCharArray();
    int N = arr.length;
    int[][] dp = new int[N][N];
  
    for(int i = 0; i < arr.length; ++i){
      dp[i][i] = 1;
    }
    for(int i = 0; i < arr.length-1; ++i){
      dp[i][i+1] = arr[i] == arr[i+1] ? 2 : 1;
    }
  
    for(int len = 3; len <=arr.length; ++len){
      for(int i = 0;; ++i){
        int j = i+len-1;
        if(j >= arr.length) break;
        if (arr[i] == arr[j]){
          dp[i][j] = dp[i+1][j-1]+2;
        } else {
          dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
        }
      }
    }

    StringBuilder prefix = new StringBuilder();
    int i,j;
    for(i = 0, j = N-1; i <= j;){
      if (arr[i] == arr[j]){
        prefix.append(arr[i]);
        ++i; --j;
      } else { 
        if (dp[i][j] == dp[i+1][j]){
          ++i;
        } else {
          --j;
        }
      }
    }
    if (i-j == 1){
      System.out.println(prefix.toString()+prefix.reverse().toString());
    } else {
      System.out.println(prefix.toString()+prefix.reverse().substring(1));
    }
    
    return dp[0][N-1];
  }

}
