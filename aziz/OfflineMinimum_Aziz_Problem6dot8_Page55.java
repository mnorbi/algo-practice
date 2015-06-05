import java.util.PriorityQueue;
/**
Problem 6.8, pg. 55: Suppose you know the permutation sigma and the extract sequence
(i[0], i[1] ... , i[m-1]) in advance. How would you efficiently compute the order in which the
m elements are removedfrom S?
*/
class OfflineMinimum_Aziz_Problem6dot8_Page55 {
    public static void main(String[]args){
        int[] ret = offlineMin(new int[]{ 4, 7, 8, 5, 6, 9, 0, 3, 1, 2, 10 },new int[]{ 3, 5, 9 });
        print(ret);
        Integer[] ret2 = offlineMin2(new int[]{ 4, 7, 8, 5, 6, 9, 0, 3, 1, 2, 10 },new int[]{ 3, 5, 9 });
        print(ret2);
    }

    static Integer[] offlineMin2(int[] A, int[] E){
        Integer ret[] = new Integer[E.length];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0, pre = 0; i < E.length; pre = E[i], ++i){
            for(int j = pre; j < E[i]; ++j){
                queue.add(A[j]);
            }
            if (!queue.isEmpty()){
                ret[i] = queue.remove();
            }
        }
        return ret;
    }

    static void print(Integer[] arr){
        for(int i : arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    static void print(int[] arr){
        for(int i : arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    static int findSet(int[] set, int x){
        int y = x;
        while(set[y] != y){
            y = set[y];
        }
        while(set[x] != x){
            int next = set[x];
            set[x] = y;
            x = next;
        }
        return x;
    }
    static int findSetRec(int[] set, int x){
        if (set[x] != x){
            set[x] = findSetRec(set, set[x]);
        }
        return set[x];
    }
    static void unionSet(int[] set, int x, int y){
        int xRoot = findSetRec(set, x);
        int yRoot = findSetRec(set, y);
        set[Math.min(xRoot, yRoot)] = Math.max(xRoot, yRoot);
    }

    static int[] createArray(int length, int initVal){
        int[] ret = new int[length];
        for(int i = 0; i < length; ++i){
            ret[i] = initVal;
        }
        return ret;
    }

    static int[] range(int lo, int hi){
        int[] ret = new int[hi-lo+1];

        for(int i = lo; i <= hi; ++i){
            ret[i-lo] = i;
        }
        return ret;
    }

    static int[] createPartition(int[] A, int[] E){
        int[] R = createArray(A.length, E.length);
        for(int i = 0, pre = 0; i < E.length; pre = E[i] + 1, ++i){
            for(int j = pre; j <= E[i]; ++j){
                R[A[j]] = i;
            }
        }
        return R;
    }

    static int[] offlineMin(int[] A, int[] E){
        int[] R = createPartition(A, E);
        int[] ret = createArray(E.length, -1);
        int[] set = range(0, E.length+1);
        for(int i = 0; i < A.length; ++i){
            int s = findSet(set, R[i]);
            if (s != E.length && ret[s] == -1){
                ret[set[R[i]]] = i;
                unionSet(set, set[R[i]], set[R[i]]+1);
            }
        }
        return ret;
    }

}