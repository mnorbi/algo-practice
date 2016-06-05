import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextInt()){
      int n = sc.nextInt();
      int k = sc.nextInt();
      Set<Frac> set = new TreeSet<Frac>();
      for(int i = 1; i <= n; ++i){
        for(int j = n; j >= i; --j){
          set.add(new Frac(i,j));
        }
      }
      Iterator<Frac> it = set.iterator();
      for(;k > 1; --k){
        it.next();
      }
      Frac ans = it.next();
      int gcd = gcd(ans.nom, ans.den);
      System.out.println(ans.nom/gcd+"/"+ans.den/gcd);
    } 
  }
  private static int gcd(int a, int b){
    int ans = 1;
    while(true){
      if (a == b){
        return ans*a;
      }
      if (a == 1 || b == 1){
        return ans;
      }
      if ((a&1) == 0 && (b&1) == 0){
        ans <<= 1;
        a >>= 1;
        b >>= 1;
        continue;
      }
      if ((a&1) == 0){
        a >>= 1;
        continue;
      }
      if ((b&1) == 0){
        b >>= 1;
        continue;
      }
      int tmp = Math.max(a,b)-Math.min(a,b);
      b = Math.min(a,b);
      a = tmp;
    }
  }
}
class Frac implements Comparable<Frac>{
  int nom;
  int den;
  
  Frac(int nom, int den){
    this.nom = nom;
    this.den = den;
  }

  public int compareTo(Frac other){
    return Integer.compare(nom*other.den, other.nom*den);
  }
}
