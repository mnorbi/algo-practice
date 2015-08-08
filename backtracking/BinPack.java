import java.util.*;

class BinPack{
    public static void main(String[]args) {
        binPack(new int[]{6, 8, 9, 4, 3, 2, 10, 7, 14, 12, 6, 2, 3, 1, 10, 11, 13, 5}, 16);
//        14 13 12 11 10 10 9 8 7 6 6 5 4 3 3 2 2 1
//         0  1  2  3  4  5 6 7 6 4 5 3 2 1 7 0 7 7
//        0: 14, 2        = 16
//        1: 13, 3        = 16
//        2: 12, 4        = 16
//        3: 11, 5        = 16
//        4: 10, 6        = 16
//        5: 10, 6        = 16
//        6:  9, 7        = 15
//        7:  8, 3, 2, 1  = 14
    }
    static void binPack(int[] arr, int limit){
        Arrays.sort(arr);
        reverse(arr);
        State st = new State(sum(arr), limit);
        State solution = binPack(arr, st, null);
        print(arr, arr.length);
        print(solution.itemToBin, solution.itemCnt);
    }

    private static int sum(int[] arr) {
        int ret = 0;
        for(int i : arr){
            ret+=i;
        }
        return ret;
    }

    private static void reverse(int[] arr) {
        for(int i = 0, j = arr.length-1; i < j; ++i, --j){
            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void print(int[] arr, int size) {
        for(int i = 0; i < size; ++i){
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }

    static State binPack(int[] arr, State st, State minSofar){
        if (st.isSolution(arr)){
            return st;
        }
        State min = null;
        for(State nextState : st.nextStates(arr, minSofar)){
            State nextSolution = binPack(arr, nextState, min);
            min = State.min(min, nextSolution);
        }
        return min;
    }
}

class State{
    int[] binLoad;
    int binSize;
    int binCnt;
    int freeCpcty;

    int[] itemToBin;
    int itemCnt;
    int itemSumLeft;
    static final int[] EMPTY_ARRAY = new int[0];

    State(int itemSumLeft, int binSize){
        this(EMPTY_ARRAY, binSize, 0, 0, EMPTY_ARRAY, itemSumLeft, 0);
    }
    State(int[] binLoad, int binSize, int freeCpcty, int binCnt, int[] itemToBin, int itemSumLeft, int itemCnt){
        this.binLoad = Arrays.copyOfRange(binLoad, 0, binCnt + 1);
        this.binSize = binSize;
        this.freeCpcty = freeCpcty;
        this.binCnt = binCnt;

        this.itemToBin = Arrays.copyOfRange(itemToBin, 0, itemCnt + 1);
        this.itemSumLeft = itemSumLeft;
        this.itemCnt = itemCnt;
    }
    static State min(State st1, State st2){
        if (st1 == null) return st2;
        if (st2 == null) return st1;
        if (st1.binCnt > st2.binCnt) {
            return st2;
        }
        return st1;
    }
    boolean isSolution(int[]arr){
        return itemCnt == arr.length;
    }
    List<State> nextStates(int[]arr, State minSofar){
        if (min(this, minSofar) != this){
            return Collections.emptyList();
        }

        List<State> ret = new ArrayList<>();
        for(int binId = 0; binId < minBucketCnt(arr.length, minSofar); ++binId){
            binLoad[binId] += arr[itemCnt];
            itemToBin[itemCnt] = binId;
            if (binLoad[binId] <= binSize){
                State nextState = nextState(arr[itemCnt], binId, minSofar);
                if (nextState != null) {
                    ret.add(nextState);
                }
            }
            binLoad[binId] -= arr[itemCnt];
            if (binLoad[binId] == 0) break;
        }
        return ret;
    }
    State nextState(int nextItem, int binId, State minSofar) {
        int newBinCnt = Math.max(binId + 1, this.binCnt);
        int newFreeBinCpcty = freeCpcty + Math.max(0, (newBinCnt-this.binCnt) * binSize);
        int newItemSumLeft = itemSumLeft - nextItem;
        int overflow = newItemSumLeft - newFreeBinCpcty;
        if (overflow > 0){
            int overflowBinCnt = overflow / binSize + (overflow % binSize == 0 ? 0 : 1);
            int newBinCntWithOverflow = overflowBinCnt + newBinCnt;
            if (newBinCntWithOverflow > minBucketCnt(newBinCntWithOverflow, minSofar)){
                return null;
            }
        }
        return new State(binLoad, binSize, newFreeBinCpcty, newBinCnt, itemToBin, newItemSumLeft, itemCnt + 1);
    }
    int minBucketCnt(int bucketCnt, State minSofar) {
        if (minSofar == null){
            return bucketCnt;
        } else {
            return Math.min(minSofar.binCnt -1, bucketCnt);
        }
    }
}
