class SegmentTree{
        static abstract class AbstractSegmentTree{
                final int[] nodes;
                final int[] content;

                AbstractSegmentTree(int[] content){
                        this.content = content;
                        this.nodes = new int[treeSize(content.length)];
                        buildTree(1, 0, content.length-1);
                }

                int treeSize(int n){
                        int ret = twoToPowCeilLog(n);
                        //(ret<<1)-1 size of the total binary tree
                        //+1 allocated for the unused zero-th
                        //   node to make indexing easy, just
                        //   like in heap implementations
                        return (ret<<1);//-1 + 1
                }

                int twoToPowCeilLog(int n){//2**(ceil(log(n)))
                        --n;
                        n|=n>> 1; n|=n>> 2; n|=n>> 4;
                        n|=n>> 8; n|=n>>16; n|=n>>32;
                        ++n;
                        return n;
                }

                int buildTree(int nIdx, int l, int r){
                        if(l==r){
                                return nodes[nIdx] = l;
                        } else {
                                int m = (l+r) >>> 1;
                                int lCIdx = buildTree(nIdx<<1, l, m);
                                int rCIdx = buildTree((nIdx<<1)+1, m+1, r);
                                return nodes[nIdx] = compareTo(content[lCIdx], content[rCIdx]) < 0 ? lCIdx : rCIdx;
                        }
                }

                int rangeQuery(int qFrom, int qTo){
                        return rangeQuery(1, 0, content.length-1, qFrom, qTo);
                }

                int rangeQuery(int nIdx, int l, int r, int qFrom, int qTo){
                        if (qTo < l || r < qFrom) return -1;// current segment outside query range
                        if (qFrom <= l && r <= qTo) return nodes[nIdx];// current segment inside query range

                        int m = (l+r) >>> 1;
                        int lCIdx = rangeQuery(nIdx<<1, l, m, qFrom, qTo);
                        int rCIdx = rangeQuery((nIdx<<1)+1, m+1, r, qFrom, qTo);

                        if (lCIdx == -1) return rCIdx;
                        if (rCIdx == -1) return lCIdx;

                        return compareTo(content[lCIdx], content[rCIdx]) < 0 ? lCIdx : rCIdx;
                }

                abstract int compareTo(int x, int y);
        }

        static class MinSegmentTree extends AbstractSegmentTree{
                MinSegmentTree(int[] content){
                        super(content);
                }
                int compareTo(int x, int y){
                        return Integer.compare(x, y);
                }
        }

        static class MaxSegmentTree extends AbstractSegmentTree{
                MaxSegmentTree(int[] content){
                        super(content);
                }
                int compareTo(int x, int y){
                        return -1*Integer.compare(x,y);
                }
        }

        static class Solver{
                final int[] arr;
                final MinSegmentTree minSt;
                final MaxSegmentTree maxSt;

                Solver(int[] arr){
                        this.arr = arr;
                        this.minSt = new MinSegmentTree(arr);
                        this.maxSt = new MaxSegmentTree(arr);
                }

                int[] findMaxLessTwoMinRangeOptim(){
                        int lenLo = 1, lenHi = arr.length;//To check the whole array first: int lenLo = 1; int lenHi = arr.length<<1;
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
				new Solver(new int[] {9, 5, 10, 11, 12, 15, 100}).solve();
				new Solver(new int[] {201,100,9,101,11,12,15,4,5,200}).solve();
				new Solver(new int[] {201,4,5,100,9,101,11,12,15,200}).solve();
				new Solver(new int[] {1,1,1,1,1,1,1,1,101,200,1}).solve();
        }
}
