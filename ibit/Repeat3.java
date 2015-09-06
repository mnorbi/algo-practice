import java.util.*;

class Repeat3 {
    public static void main(String[]args){
      System.out.println(repeatedNumber(Arrays.asList(1, 1, 1, 2, 3, 4, 5)));
    }
    public static int repeatedNumber(final List<Integer> a) {
        int repeat = 3;
        boolean[] filled = new boolean[repeat - 1];
        int[] arr = new int[repeat - 1];
        int[] cnt = new int[repeat - 1];

        for (int i : a) {
            int id = findId(arr, i);
            if (id == -1 || filled[id] == false) {
                id = findId(cnt, 0);
                if (id == -1) {
                    decreaseAll(cnt, filled);
                } else {
                    arr[id] = i;
                    ++cnt[id];
                    filled[id] = true;
                }
            } else {
                ++cnt[id];
            }
        }

        for (int i = 0; i < arr.length; ++i) {
            if (filled[i] && cnt[i] > 0) {
                int iCnt = count(a, arr[i]);
                if (iCnt > a.size() / repeat) return arr[i];
            }
        }
        return -1;
    }

    private static void decreaseAll(int[] cnt, boolean[] filled) {
        for (int j = 0; j < cnt.length; ++j) {
            if (cnt[j] > 0) {
                --cnt[j];
            }
            if (cnt[j] == 0) {
                filled[j] = false;
            }
        }
    }

    private static int findId(int[] arr, int i) {
        for (int j = 0; j < arr.length; ++j) {
            if (arr[j] == i) return j;
        }
        return -1;
    }

    private static int count(List<Integer> a, int i) {
        int ret = 0;
        for (int j : a) {
            if (j == i) ++ret;
        }
        return ret;
    }
}
