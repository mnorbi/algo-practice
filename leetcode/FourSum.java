import java.util.*;
import java.util.stream.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int a = 0; a+3 < nums.length;){
            for(int b = a+1; b+2 < nums.length;){
                int c = b+1, d = nums.length-1;
                while(c < d){
                    int sum = nums[a]+nums[b]+nums[c]+nums[d];
                    if (sum == target){
                        result.add(Arrays.stream(new int[]{nums[a],nums[b],nums[c],nums[d]}).mapToObj(Integer::valueOf).collect(Collectors.toList()));
                    }
                    if (sum <= target){
                       for(++c;c < d && nums[c] == nums[c-1];++c);
                    }
                    if (sum >= target){
                       for(--d;c < d && nums[d] == nums[d+1];--d);
                    }
                }
                for(++b;b+2 < nums.length && nums[b] == nums[b-1];++b);
            }
            for(++a;a+3 < nums.length && nums[a] == nums[a-1];++a);
        }
        return result;
    }
}
