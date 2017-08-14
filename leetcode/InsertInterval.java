/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        Iterator<Interval> it = intervals.iterator();
        Interval next = null;
        while(true){
            if (it.hasNext()){
                next = it.next();
                if (next.end < newInterval.start){
                    result.add(next);
                } else {
                    break;
                }
            } else {
                next = null;
                break;
            }
        }
        Interval merged = new Interval(newInterval.start, newInterval.end);
        while(next != null && next.start <= merged.end){
            merged.start = Math.min(next.start, merged.start);
            merged.end = Math.max(next.end, merged.end);
            if (it.hasNext()){
                next = it.next();
            } else {
                next = null;
            }
        }
        result.add(merged);
        while(next != null){
            result.add(next);
            if (it.hasNext()){
                next = it.next();
            } else {
                next = null;
            }
        }
        return result;
    }
}
