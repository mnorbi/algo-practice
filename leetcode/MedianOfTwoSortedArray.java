public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N = nums1.length+nums2.length;
        if (N == 0) {
            return 0.0;
        }
        int v1 = findMedianSortedArrays(nums1, 0, nums1.length, nums2, 0, nums2.length, (N-1)/2+1);
        int v2 = findMedianSortedArrays(nums1, 0, nums1.length, nums2, 0, nums2.length, N/2+1);
        double ret = (0.0 + v1 + v2) / 2;
        return ret;
    }
    private int findMedianSortedArrays(int[] nums1, int lo1, int hi1, int[] nums2, int lo2, int hi2, int len){
        if (lo1 == hi1){
            return getBigger(nums1, hi1, nums2, len-hi1);
        }
        if (lo2 == hi2){
            return getBigger(nums1, len-hi2, nums2, hi2);
        }
        int mid1 = (lo1+hi1+1)/2, mid2 = (lo2+hi2+1)/2;
        boolean cutFirst = hi1-lo1 >= hi2-lo2;
        mid1 = cutFirst  ? mid1 : lo1 + nonBiggerCount(nums1, lo1, hi1, nums2[mid2-1]);
        mid2 = !cutFirst ? mid2 : lo2 + nonBiggerCount(nums2, lo2, hi2, nums1[mid1-1]);
        if (mid1+mid2 > len){
            return findMedianSortedArrays(nums1, lo1, mid1-(cutFirst?1:0), nums2, lo2, mid2-(!cutFirst?1:0), len);
        } else if (mid1+mid2 == len){
            return getBigger(nums1, mid1, nums2, mid2);
        } else {
            return findMedianSortedArrays(nums1, mid1, hi1, nums2, mid2, hi2, len);
        }
    }

    private int getBigger(int[] nums1, int len1, int[] nums2, int len2) {
        if (len1 == 0){
            return nums2[len2-1];
        }
        if (len2 == 0){
            return nums1[len1-1];
        }
        return Math.max(nums1[len1-1],nums2[len2-1]);
    }

    private int nonBiggerCount(int[] arr, int lo, int hi, int val){
        int start = lo;
        --hi;
        int ret = 0;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if (arr[mid] <= val){
                ret = mid-start+1;
                lo = mid+1;
            } else if (arr[mid] >= val){
                hi = mid-1;
            }
        }
        return ret;
    }
}
