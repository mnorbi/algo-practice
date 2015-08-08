import java.util.*;

/**
 *  Optimizations
 *  <ul>
 *  <li>tight bucketing - no intermittent zero buckets</li>
 *  <li>search pruned if partial solution cannot do better than the current minimum</li>
 *  <li>search pruned by estimating a lower bound bucket count for the leftover, pruning done if <i>binCnt + surplusEstimate >= minSofar</i>, where surplusEstimate = <i>ceil((sum(leftOvers)-sum(freeCapacity))/binSize)</i></li>
 *  <li>sort input weights in descending order - with this approach new additional buckets are allocated earlier (remaining capacities are breached earlier with bigger weights) and the previously described prunings could happen because of the increased bucket counts</li>
 *  </ul>
 *
 */
class BinPack{
    public static void main(String[]args) {
        binPack(new int[]{6, 8, 9, 4, 3, 2, 10, 7, 14, 12, 6, 2, 3, 1, 10, 11, 13, 5}, 16);
    }
    static void binPack(int[] arr, int limit){
        Utils.reversedSort(arr);
        State solution = binPack(arr, new State(Utils.sum(arr), limit), Integer.MAX_VALUE);
        Utils.print("Weights", arr, arr.length);
        Utils.print("Bins", solution.itemToBin, solution.itemCnt);
    }

    static State binPack(int[] arr, State state, int minBucketCnt){
        if (state.isSolution(arr)){
            return state;
        }
        State min = null;
        for(State nextState : state.nextStates(arr, minBucketCnt)){
            State nextSolution = binPack(arr, nextState, minBucketCnt);
            min = State.min(min, nextSolution);
            if (min != null){
                minBucketCnt = Math.min(min.binCnt, minBucketCnt);
            }
        }
        return min;
    }
}
class State{
    static final int[] EMPTY_ARRAY = new int[0];

    int[] binLoad;
    int binSize;
    int binCnt;
    int freeBinCpctySum;

    int[] itemToBin;
    int itemCnt;
    int itemSumLeft;

    State(int itemSumLeft, int binSize){
        this(EMPTY_ARRAY, binSize, 0, 0, EMPTY_ARRAY, itemSumLeft, 0);
    }
    State(int[] binLoad, int binSize, int freeBinCpctySum, int binCnt, int[] itemToBin, int itemSumLeft, int itemCnt){
        this.binLoad = Arrays.copyOfRange(binLoad, 0, binCnt + 1);
        this.binSize = binSize;
        this.freeBinCpctySum = freeBinCpctySum;
        this.binCnt = binCnt;

        this.itemToBin = Arrays.copyOfRange(itemToBin, 0, itemCnt + 1);
        this.itemSumLeft = itemSumLeft;
        this.itemCnt = itemCnt;
    }
    List<State> nextStates(int[]arr, int minBucketCnt){
        if (this.binCnt >= minBucketCnt) return Collections.emptyList();

        List<State> ret = new ArrayList<>();
        for(int binId = 0;; ++binId){
            State nextState = nextState(arr, binId, minBucketCnt);
            if (nextState != null) {
                ret.add(nextState);
            }
            if (binLoad[binId] == 0) break;
        }
        return ret;
    }
    State nextState(int[] arr, int binId, int minBucketCnt) {
        State ret = null;
        binLoad[binId] += arr[itemCnt];
        if (binLoad[binId] <= binSize){
            itemToBin[itemCnt] = binId;
            int newBinCnt = Math.max(binId + 1, this.binCnt);
            int newFreeBinCpctySum = freeBinCpctySum + Math.max(0, (newBinCnt-this.binCnt) * binSize);
            int newItemSumLeft = itemSumLeft - arr[itemCnt];
            if (newBinCnt + surplusLowerBoundBinCnt(newFreeBinCpctySum, newItemSumLeft) < minBucketCnt){
                ret = new State(binLoad, binSize, newFreeBinCpctySum, newBinCnt, itemToBin, newItemSumLeft, itemCnt + 1);
            }
            itemToBin[itemCnt] = 0;
        }
        binLoad[binId] -= arr[itemCnt];

        return ret;
    }
    private int surplusLowerBoundBinCnt(int freeBinCpcty, int itemSumLeft) {
        int surplus = itemSumLeft - freeBinCpcty;
        int binCnt = 0;
        if (surplus > 0) {
            binCnt = surplus / binSize + (surplus % binSize == 0 ? 0 : 1);
        }
        return binCnt;
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
}
class Utils {
    static void reversedSort(int[] arr) {
        Arrays.sort(arr);
        reverse(arr);
    }

    static void reverse(int[] arr) {
        for(int i = 0, j = arr.length-1; i < j; ++i, --j){
            swap(arr, i, j);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static void print(String descr, int[] arr, int size) {
        System.out.print(descr+" ");
        for(int i = 0; i < size; ++i){
            System.out.print(arr[i] +" ");
        }
        System.out.println();
    }

    static int sum(int[] arr) {
        int ret = 0;
        for(int i : arr){
            ret+=i;
        }
        return ret;
    }
}
