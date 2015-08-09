import java.util.*;

class InsertInterval {
    public static void main(String[]args){
        ArrayList<Interval> al = new ArrayList<>();
        al.add(new Interval(1, 3));
        al.add(new Interval(6, 9));
        System.out.println(insert(al, new Interval(2,5)));
    }
    public static ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> ret = new ArrayList<>();
        int i;
        for(i = 0; i < intervals.size(); ++i){
            Interval iv =intervals.get(i);
            if (iv.start >= newInterval.start) break;
            if (iv.end >= newInterval.start) break;
            ret.add(iv);
        }

        if (i < intervals.size()){
            int start = newInterval.start;
            int end = newInterval.end;
            for(; i < intervals.size(); ++i){
                Interval iv = intervals.get(i);
                if (iv.start > newInterval.end) break;
                start = Math.min(start, iv.start);
                end = Math.max(end, iv.end);
            }
            ret.add(new Interval(start, end));
        } else {
            ret.add(newInterval);
        }

        for(; i <intervals.size(); ++i){
            ret.add(intervals.get(i));
        }
        return ret;
    }
}
class Interval{
    int start, end;
    Interval(int s, int e){
        this.start = s;
        this.end = e;
    }
    public String toString(){
        return String.format("[%d,%d]", start, end);
    }
}
