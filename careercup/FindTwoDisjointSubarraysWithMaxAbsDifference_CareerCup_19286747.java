import java.util.*;
/**
Given an array of integers. Find two disjoint contiguous sub-arrays such that the absolute difference between the sum of two sub-array is maximum. 
* The sub-arrays should not overlap. 

eg- [2 -1 -2 1 -4 2 8] ans - (-1 -2 1 -4) (2 8), diff = 16 
kadane
**/
class FindTwoDisjointSubarraysWithMaxAbsDifference_CareerCup_19286747{
        public static void main(String[]args){
                int[][] arrs = new int[][]{
                        {2,-1,-2,12,453,-9,2,8}
                        ,{7,-1,4}
                        ,{1,2,3,4,5}
                        ,{-1,-3,-5,-2,-1,-4}
                        ,{2, -1, -2, 1, -4, 2, 8}
                };
                for(int[] arr : arrs){
                        Range[] ranges = findRanges(arr);
                        print(arr, ranges[0], ranges[1]);
                }
        }
        static Range[] findRanges(int[] arr){
                Range[] minSumsFromLeft = findMinSumsFromLeft(arr);
                Range[] maxSumsFromRight = findMaxSumsFromRight(arr);
                int i = findOptimalSplit(minSumsFromLeft, maxSumsFromRight);

                Range[] maxSumsFromLeft = findMaxSumsFromLeft(arr);
                Range[] minSumsFromRight = findMinSumsFromRight(arr);
                int j = findOptimalSplit(maxSumsFromLeft, minSumsFromRight);

                int diff1 = optimalValAtSplit(i, minSumsFromLeft, maxSumsFromRight);
                int diff2 = optimalValAtSplit(j, maxSumsFromLeft, minSumsFromRight);
                if (diff1 > diff2){
                        return optimalRangesAtSplit(i, minSumsFromLeft, maxSumsFromRight);
                } else {
                        return optimalRangesAtSplit(j, maxSumsFromLeft, minSumsFromRight);
                }
        }
        static Range[] optimalRangesAtSplit(int i, Range[] leftSums, Range[] rightSums){
                return new Range[]{leftSums[i], rightSums[i+1] };
        }
        static int optimalValAtSplit(int i, Range[] leftSums, Range[] rightSums){
                return Math.abs(rightSums[i+1].sum - leftSums[i].sum);

        }
        static int findOptimalSplit(Range[] leftSums, Range[] rightSums){
                int maxVal = Integer.MIN_VALUE;
                int ans = -1;
                for(int i = 0; i < leftSums.length; ++i){
                        int nextVal = optimalValAtSplit(i, leftSums, rightSums);
                        if (maxVal < nextVal){
                                maxVal = nextVal;
                                ans = i;
                        }
                }
                return ans;
        }
        static Comparator<Integer> NATURAL_ORDER = new Comparator<Integer>(){
                public int compare(Integer a, Integer b){
                        return Integer.compare(a,b);
                }
        };
        static Comparator<Integer> REVERSED_ORDER = Collections.reverseOrder(NATURAL_ORDER);
        static Range[] findMinSumsFromLeft(int[] arr){
                return findSumsFromLeft(arr, REVERSED_ORDER);
        }
        static Range[] findMaxSumsFromLeft(int[] arr){
                return findSumsFromLeft(arr, NATURAL_ORDER);
        }
        static Range[] findSumsFromLeft(int[] arr, Comparator<Integer> comparator){
                Range[] ans = new Range[arr.length-1];
                for(int i = 0, s = i, sum = 0; i < ans.length; ++i){
                        sum += arr[i];
                        if (comparator.compare(arr[i], sum)<0){
                                sum = arr[i];
                                s = i;
                        }
                        ans[i] = new Range(s, i, sum);
                }
                return ans;
        }
        static Range[] findMinSumsFromRight(int[] arr){
                return findSumsFromRight(arr, REVERSED_ORDER);
        }
        static Range[] findMaxSumsFromRight(int[] arr){
                return findSumsFromRight(arr, NATURAL_ORDER);
        }
        static Range[] findSumsFromRight(int[] arr, Comparator<Integer> comparator){
                Range[] ans = new Range[arr.length];
                for(int i = arr.length-1, e = i, sum = 0; i > 0; --i){
                        if (comparator.compare(arr[i], sum)<0){
                                sum = arr[i];
                                e = i;
                        }
                        ans[i] = new Range(i, e, sum);
                }
                return ans;
        }
        static void print(int[] arr, Range left, Range right){
                print(arr, left);
                System.out.print(" ");
                print(arr, right);
                System.out.println();
        }
        static void print(int[] arr, Range r){
                System.out.print("[");
                System.out.print(arr[r.start]);
                for(int i = r.start+1; i <= r.end; ++i){
                        System.out.printf(", %d", arr[i]);
                }
                System.out.print("]");
        }
}
class Range{
        int start, end, sum;
        Range(int start, int end, int sum){
                this.start = start;
                this.end = end;
                this.sum = sum;
        }
        public String toString(){
                return String.format("[%d,%d] sum:%d", start, end, sum);
        }
}

