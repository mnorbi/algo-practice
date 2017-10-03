class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (words == null || words.length == 0) return Collections.emptyList();
        int k = words[0].length();
        Map<String, Integer> cnt = new HashMap<>();
        for(String word : words){
            cnt.merge(word, -1, Integer::sum);
        }
        List<Integer> ans = new ArrayList<>();
        for(int a = 0; a < k; ++a){
            int lo = a, nonZeroCnt = cnt.size();
            for(int hi = a; hi+k <= s.length();){
                String w = s.substring(hi, hi+k);
                hi += k;
                if (cnt.containsKey(w)){
                    if (cnt.get(w) == -1){
                        --nonZeroCnt;
                    } else if (cnt.get(w) == 0){
                        ++nonZeroCnt;
                    }
                    cnt.merge(w, +1, Integer::sum);
                }
                if ((hi-lo)/k > words.length){
                    w = s.substring(lo, lo+k);
                    lo += k;
                    if (cnt.containsKey(w)){
                        if (cnt.get(w) == 1){
                            --nonZeroCnt;
                        } else if (cnt.get(w) == 0){
                            ++nonZeroCnt;
                        }
                        cnt.merge(w, -1, Integer::sum);
                    }
                }
                if (nonZeroCnt == 0){
                    ans.add(lo);
                }
            }
            while(lo+k <= s.length()){
                String w = s.substring(lo, lo+k);
                lo += k;
                if (cnt.containsKey(w)){
                    cnt.merge(w, -1, Integer::sum);
                }
            }
        }
        return ans;
    }
}
