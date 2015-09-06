import java.util.*;
import java.util.stream.*;
class FourSum {
    public static void main(String[]args){
		FourSum fs = new FourSum();
		System.out.println(fs.fourSum(IntStream.of(-5,1,1,2,2,3).boxed().collect(Collectors.toCollection(ArrayList::new)), 0));
                System.out.println(fs.fourSumSingle(IntStream.of(1, 0, -1, 0, -2, 2).boxed().collect(Collectors.toCollection(ArrayList::new)), 0));
    }
    public ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> a, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        if (a == null || a.size() == 0) return ret;

        Collections.sort(a);
        int N = a.size();
        for (int i = 0; i + 3 < N; ++i) {
            if (i > 0 && a.get(i - 1).equals(a.get(i))) {
                continue;
            }
            for (int j = i + 1; j + 2 < N; ++j) {
                if (j > i + 1 && a.get(j - 1).equals(a.get(j))) {
                    continue;
                }
                for (int k = j + 1, l = a.size() - 1; k < l; ) {
                    int sum = a.get(i) + a.get(j) + a.get(k) + a.get(l);
                    if (sum > target) {
                        --l;
                    } else if (sum < target) {
                        ++k;
                    } else {//(sum == target)
                        ret.add(new ArrayList<>(Arrays.asList(a.get(i), a.get(j), a.get(k), a.get(l))));
                        for (++k; a.get(k).equals(a.get(k - 1)); ++k) ;//for(--l; a.get(l).equals(a.get(l+1)); --l);
                    }
                }
            }
        }

        return ret;
    }
    public ArrayList<Integer> fourSumSingle(ArrayList<Integer> a, int target) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();

        for (int i = 2; i + 1 < a.size(); ++i) {
            for (int j = i + 1; j < a.size(); ++j) {
                int sum = a.get(i) + a.get(j);
                if (!map.containsKey(sum)) {
                    map.put(sum, new ArrayList<int[]>());
                }
                map.get(sum).add(new int[]{i, j});
            }
        }
        for (int i = 0; i + 3 < a.size(); ++i) {
            for (int j = i + 1; j + 2 < a.size(); ++j) {
                int key = target - (a.get(i) + a.get(j));
                if (map.containsKey(key)) {
                    List<int[]> list = map.get(key);
                    Iterator<int[]> it = list.iterator();
                    while (it.hasNext()) {
                        int[] ids = it.next();
                        if (ids[0] > i && ids[0] > j && ids[1] > i && ids[1] > j) {
                            return new ArrayList<>(
                                    Arrays.asList(a.get(i), a.get(j), a.get(ids[0]), a.get(ids[1])));
                        }
                    }
                    map.remove(key);
                }
            }
        }
        return new ArrayList<>();
    }
}
