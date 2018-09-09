class PartitionToKEqualSumSubsets {
    public static void main(String[] args) {
        new PartitionToKEqualSumSubsets().canPartitionKSubsets(
                new int[] {4,1,2,3,2,3,5},2
        );
    }
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if (sum % k != 0){
            return false;
        }

        int n = nums.length;
        boolean ans = canPartitionKSubsets(nums, (1<<n)-1, sum/k, sum/k, new Boolean[1<<nums.length]);
        return ans;
    }

    private Boolean canPartitionKSubsets(int[] nums, int mask, int toFill, int targetSum, Boolean[] memo){
        boolean ans = false;

        if (memo[mask] != null){
            return memo[mask];
        }

        if (mask == 0) {
            ans = true;
        } else {
            for(int next = 0; next < nums.length; ++next){
                int flag = (1<<next);
                if ((mask&flag) > 0){
                    int nextToFill = toFill-nums[next];
                    if (nextToFill >= 0) {
                        if (canPartitionKSubsets(nums, mask ^ flag, nextToFill > 0 ? nextToFill : targetSum, targetSum, memo)) {
                            ans = true;
                            break;
                        }
                    }
                }
            }
        }

        return memo[mask] = ans;
    }
}
