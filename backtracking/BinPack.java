import java.util.*;
class BinPack{
    public static void main(String[]args) {
        binPack(new int[]{10, 23, 14, 6}, 30);
        binPack(new int[]{1, 1, 1, 1, 1}, 2);
    }
    static void binPack(int[] arr, int limit){
        State st = new State(0, new int[arr.length], 0);
        State solution = binPack(arr, limit, st, null);
        System.out.println(solution.bucketSize);
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
    int doneSize;
    int bucketSize;

    State(int doneSize, int[] alloc, int bucketSize){
        this.doneSize = doneSize;
        this.alloc = Arrays.copyOfRange(alloc, 0, bucketSize+1);
        this.bucketSize = bucketSize;
    }
    static State min(State st1, State st2){
        if (st1 == null) return st2;
        if (st2 == null) return st1;
        if (st1.bucketSize > st2.bucketSize) {
            return st2;
        }
        return st1;
    }
    boolean isSolution(int[]arr){
        return doneSize == arr.length;
    }
    List<State> nextStates(int[]arr, int limit, State minSofar){
        if (min(this, minSofar) != this){
            return Collections.emptyList();
        }
        List<State> ret = new ArrayList<>();
        for(int i = 0; i < untilBucket(doneSize+1, minSofar); ++i){
            alloc[i] += arr[doneSize];
            if (alloc[i] <= limit){
                ret.add(new State(doneSize+1, alloc, Math.max(i+1, this.bucketSize)));
            }
            alloc[i] -= arr[doneSize];
            if (alloc[i] == 0) break;
        }
        return ret;
    }

    private int untilBucket(int doneSize, State minSofar) {
        if (minSofar == null){
            return doneSize;
        } else {
            return Math.min(minSofar.bucketSize, doneSize);
        }
    }
}
