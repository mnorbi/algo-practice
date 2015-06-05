class SparseTable{
        static abstract class AbstractSparseTable{
                final int[] content;
                final int N;
                final int[][] table;

                AbstractSparseTable(int[] content){
                        this.content = content;
                        N = content.length;
                        this.table = buildTable();
                }

                int[][] buildTable(){
                        int[][] table = new int[N][];
                        for(int i = 0; i<N; i++){
                                table[i] = new int[floorLog2(N-i)+1];
                                table[i][0] = i;
                        }
                        for(int j = 1; (1 << j) <= N; j++){
                                for(int i = 0; i + (1 << j) - 1 < N; i++){
                                        int lCIdx = table[i][j-1];
                                        int rCIdx = table[i + (1 << (j-1))][j-1];
                                        table[i][j] = compareTo(content[lCIdx], content[rCIdx]) < 0 ? lCIdx : rCIdx;
                                }
                        }
                        return table;
                }

                int rangeQuery(int qFrom, int qTo){
                        int k = floorLog2(qTo-qFrom+1);
                        int lCIdx = table[qFrom][k];
                        int rCIdx = table[qTo - (1<<k) + 1][k];
                        return compareTo(content[lCIdx], content[rCIdx]) < 0 ? lCIdx : rCIdx;
                }

                int floorLog2(int n){
                        return 31 - Integer.numberOfLeadingZeros(n);
                }

                abstract int compareTo(int x, int y);
        }

        static class MinSparseTable extends AbstractSparseTable{
                MinSparseTable(int[] content){
                        super(content);
                }
                int compareTo(int x, int y){
                        return Integer.compare(x, y);
                }
        }

        static class MaxSparseTable extends AbstractSparseTable{
                MaxSparseTable(int[] content){
                        super(content);
                }
                int compareTo(int x, int y){
                        return -1*Integer.compare(x, y);
                }
        }

        static class Solver{
                final int[] arr;
                final MinSparseTable minSt;
                final MaxSparseTable maxSt;

                Solver(int[] arr){
                        this.arr = arr;
                        this.minSt = new MinSparseTable(arr);
                        this.maxSt = new MaxSparseTable(arr);
                }

                int[] findMaxLessTwoMinRangeOptim(){
                        int lenLo = 0, lenHi = arr.length<<1;
                        int[] maxRange = null;
                        while(lenLo<=lenHi){
                                int lenM = (lenLo+lenHi)>>>1;
                                int[] range = findMaxLessTwoMinRangeWithLen(lenM);
                                if (range == null){
                                        lenHi = lenM-1;
                                } else {
                                        maxRange = range;
                                        lenLo = lenM+1;
                                }
                        }
                        return maxRange;
                }

                int[] findMaxLessTwoMinRangeWithLen(int len){
                        int[] range = null;
                        for(int i = 0, j = len - 1; j >= 0 && j < arr.length; i++, j++){
                                if ((arr[minSt.rangeQuery(i, j)]<<1) > arr[maxSt.rangeQuery(i, j)]){
                                        return new int[] {i, j};
                                }
                        }
                        return null;
                }

                void solve(){
                        int[] range = findMaxLessTwoMinRangeOptim();
                        printArr(range);
                }

                void printArr(int[] range){
                        for(int i=range[0]; i<=range[1]; i++){
                                System.out.print(arr[i]);
                                System.out.print(" ");
                        }
                        System.out.println();
                }
        }

        public static void main(String[] args){
                new Solver(new int[] {4, 5, 100, 9, 10, 11, 12, 15, 200}).solve();
                new Solver(new int[] {4, 7, 5, 6}).solve();
                new Solver(new int[] {20, 7, 5, 6}).solve();
                new Solver(new int[] {20, 4, 1, 3}).solve();
                new Solver(new int[] {4, 5, 9, 10, 11, 12, 17, 20}).solve();
                new Solver(new int[] {4, 5, 100, 9, 10, 11, 12, 15, 101,102,103,104,105,106,107,108,109,110,111,112}).solve();
                new Solver(new int[] {1,1,1,1,1,1,1,1,101,200,1}).solve();
        }
}

