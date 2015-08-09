/**

Intput: (27, 28),(15, 46), (5, 43), (7, 15), (46, 90), (7, 64), (18, 42), (67, 84), (48, 100), (21, 27), (26, 68)

Output: (5,100)
**/
import java.util.*;
class MergeIntervals{
  public static void main(String[]args){
    System.out.println(
      mergeIntervals(
        Interval.from(27,28 , 15,46,  5,43 , 7,15 , 46,90 , 7,64 , 18,42 , 67,84 , 48,100 , 21,27 , 26,68)));
  }
  static List<Interval> mergeIntervals(Interval[] ivs){
    Arrays.sort(ivs, Interval.START_COMPARATOR);
    List<Interval> ret= new ArrayList<>();
    int lo = ivs[0].start;
    int hi = ivs[0].end;
    for(Interval iv : ivs){
      if (iv.start <= hi){
        hi = Math.max(hi, iv.end);
      } else {
        ret.add(new Interval(lo, hi));
        lo = iv.start;
        hi = iv.end;
      }
    }
    ret.add(new Interval(lo, hi));
    return ret;
  }
}
class Interval{
  static final Comparator<Interval> START_COMPARATOR = new Comparator<Interval>(){
      public int compare(Interval iv1, Interval iv2){
        return Integer.compare(iv1.start, iv2.start);
      }
    };
  int start, end;
  Interval(int s, int e){
    this.start = s;
    this.end= e;
  }
  static Interval[] from(int...vals){
    Interval[] ret = new Interval[vals.length/2];
    for(int i = 0, j = 0; i < vals.length; i+=2, ++j){
      ret[j] = new Interval(vals[i], vals[i+1]);
    }
    return ret;
  }
  public String toString(){
    return String.format("[%d,%d]", start,end);
  }
}
