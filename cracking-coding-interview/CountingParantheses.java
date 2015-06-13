public class CountingParantheses{
  public static void main(String[]args){
    long count = allCount(6);
    System.out.println(count);
    count = allCountDp(6);
    System.out.println(count);
  }
  private static long allCountDp(int len){
    if (len <= 1) return len;

    long[] allCount = new long[] {0, 1};
    long onionCount = 0;
    long psFxCount = 0;
    long midCount = 0;
    for(int i = 2; i <= len; ++i){
      psFxCount = (onionCount+psFxCount/2)<<1;
      onionCount = allCount[(i-1)%2];
      midCount = (i==2) ? 1 : allCount[(i-2)%2];
      allCount[i%2] = onionCount+psFxCount+midCount;
   }
   return allCount[len%2];
  }
  private static long allCount(long len){
    if (len == 0) return 0;
    if (len == 1) return 1;
    long ret = onionCount(len);
    ret += pfxCount(len);
    ret += sfxCount(len);
    ret += midCount(len);
    return ret;
  }
  private static long onionCount(long len){
    if (len <= 1) return 0;
    return allCount(len-1);
  }
  private static long pfxCount(long len){
    if (len <= 1) return 0;
    long ret = onionCount(len-1);
    ret += pfxCount(len-1);
    return ret;
  }
  private static long sfxCount(long len){
    if (len <= 1) return 0;
    long ret = onionCount(len-1);
    ret += sfxCount(len-1);
    return ret;
  }
  private static long midCount(long len){
    if (len <= 1) return 0;
    if (len == 2) return 1;
    long ret = allCount(len-2);
    return ret;
  }
}
