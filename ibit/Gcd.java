class Gcd{
  public static void main(String[]args){
    System.out.println(gcd(13, 26));
  }
  static int gcd(int a, int b){
    if (a < b){
      int tmp = a;
      a = b;
      b = tmp;
    }
    if (b == 0) return a;
    while(a%b!=0){
      int tmp = a%b;
      a = b;
      b = tmp;
    }
    return b;
  }
}
