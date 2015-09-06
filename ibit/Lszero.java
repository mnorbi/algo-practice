public class Solution {
    public ArrayList<Integer> lszero(ArrayList<Integer> A) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int lo = 0, hi = 0;
        for(int sum = 0, i = 1; i <= A.size(); ++i){
            sum += A.get(i-1);
            if (!map.containsKey(sum)){
                map.put(sum, i);
            } else {
                int idx = map.get(sum);
                if (i-idx > hi-lo){
                    hi = i;
                    lo = idx;
                }
            }
        }
        
        ArrayList<Integer> ret = new ArrayList<>();
        for(int i = lo; i < hi; ++i){
            ret.add(A.get(i));
        }
        
        return ret;
    }
}

