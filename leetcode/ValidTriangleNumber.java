import java.util.*;

class Solution {
    public int triangleNumber(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        int lo = 0;
        for(;lo < nums.length && nums[lo] == 0;++lo);
        for(;lo+2 < nums.length; ++lo){
            int hi = nums.length-1, cur = hi-1;
            for(;lo+1 < hi && lo < cur;){
                if (nums[lo]+nums[cur] > nums[hi]){
                    --cur;
                } else {
                    ans += (hi-cur-1);
                    --hi;
                }
            }
            ans += (((hi-cur-1)*(hi-cur))/2);
        }
        return ans;
    }
}
