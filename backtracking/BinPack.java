import java.util.*;
class BinPack{
    public static void main(String[]args) {
        binPack(new int[]{10, 23, 14, 6}, 30);
        binPack(new int[]{1, 1, 1, 1, 1}, 2);
    }
    static void binPack(int[] arr, int limit){
        State st = new State(-1, new int[arr.length], 0);
        State solution = binPack(arr, limit, st, null);
        System.out.println(solution.lastBucket + 1);
    }
    static State binPack(int[] arr, int limit, State st, State minSofar){
        if (st.isSolution(arr)){
            return st;
        }
        State min = null;
        for(State nextState : st.nextStates(arr, limit, minSofar)){
            State nextSolution = binPack(arr, limit, nextState, min);
            min = State.min(min, nextSolution);
        }
        return min;
    }
}

class State{
    int[] alloc;
    int lastItem;
    int lastBucket;

    State(int lastItem, int[] alloc, int lastBucket){
        this.lastItem = lastItem;
        this.alloc = Arrays.copyOfRange(alloc, 0, alloc.length);
        this.lastBucket = lastBucket;
    }
    static State min(State st1, State st2){
        if (st1 == null) return st2;
        if (st2 == null) return st1;
        if (st1.lastBucket > st2.lastBucket) {
            return st2;
        }
        return st1;
    }
    boolean isSolution(int[]arr){
        return lastItem >= arr.length-1;
    }
    List<State> nextStates(int[]arr, int limit, State minSofar){
        if (min(this, minSofar) != this){
            return Collections.emptyList();
        }
        List<State> ret = new ArrayList<>();
        int nextItem = lastItem+1;
        for(int i = 0; i <= (minSofar == null ? nextItem : Math.min(nextItem, minSofar.lastBucket)); ++i){
            alloc[i] += arr[nextItem];
            if (alloc[i] <= limit){
                ret.add(new State(nextItem, alloc, Math.max(i, this.lastBucket)));
            }
            alloc[i] -= arr[nextItem];
            if (alloc[i] == 0) break;
        }
        return ret;
    }
}
