class Solution {
    public int reversePairs(int[] nums) {
        return count(nums, new int[nums.length], 0, nums.length-1);
    }
    private int count(int[] nums, int[] aux, int lo, int hi){
        if (lo >= hi){
            return 0;
        }
        int mid = lo + (hi-lo)/2;
        int ans = count(nums, aux, lo, mid);
        ans += count(nums, aux, mid+1, hi);
        
        for(int a = lo, b = mid+1; a <= mid && b <= hi;){
            if (nums[a] > nums[b]*2L){
                ans += (mid-a+1);
                ++b;
            } else {
                ++a;
            }
        }
        int a = lo, b = mid+1, tmp = lo;
        while(a  <= mid && b <= hi){
            if (nums[a] <= nums[b]){
                aux[tmp++] = nums[a++];
            } else {
                aux[tmp++] = nums[b++];
            }
        }
        while(a <= mid){
            aux[tmp++] = nums[a++];
        }
        while(--tmp >= lo){
            nums[tmp] = aux[tmp];
        }
        return ans;
    }
}

