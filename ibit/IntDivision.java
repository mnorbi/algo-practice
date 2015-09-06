class IntDivision{
  public int divide(int dividend, int divisor){
    long num = dividend;
    long den = divisor;

    int sign = (num < 0) ^ (den < 0) ? -1 : 1;

    num = Math.abs(num);
    den = Math.abs(den);

    long ret = 0;
    for(long t = 0, i = 31; i >= 0; --i){
      long tmp = t+(den<<i);
      if (tmp <= num){
        t = tmp;
        ret |= (1<<i);
      }
    }

    ret = sign < 0 ? -ret : ret;

    ret = (ret < Integer.MIN_VALUE || ret > Integer.MAX_VALUE) ? Integer.MAX_VALUE : ret;

    return (int)ret;
  }
}
