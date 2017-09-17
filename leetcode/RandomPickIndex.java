import java.util.*;
class Solution {
    private int[] nums;
    private Random random;
    private Map<Integer, Integer> heads;
    
    public Solution(int[] nums) {
        this.nums = nums;
        this.random = new Random();
        this.heads = new HashMap<>();
        Map<Integer, Integer> prev = new HashMap<>();
        for(int a = 0; a < nums.length; ++a){
            if (!heads.containsKey(nums[a])){
                heads.put(nums[a], a);
            } else {
                nums[prev.get(nums[a])] = a;
            }
            prev.put(nums[a],a);
            nums[a] = nums.length;
        }
    }
    
    public int pick(int target) {
        int ans = heads.get(target);
        for(int a = nums[ans], b = 1; a < nums.length; a = nums[a]){
            if (random.nextInt(++b) == 0){
                ans = a;
            }
        }
        return ans;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
