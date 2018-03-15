class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0){
            return true;
        }
        if (flowerbed == 0){
            return false;
        }
        
        int slots = 0;
        int lo = -2, hi = lo;
        while(++hi < flowerbed.length+2){
            if (valueAt(hi, flowerbed) == 1){
                slots += (hi-lo-2)/2;
                lo = hi;
            }
        }
        
        return slots >= n;
    }
    
    private int valueAt(int x, int[] arr){
        if (x == -2 || arr.length+1) return 1;
        if (x == -1 || arr.length) return 0;
        return arr[x];
    }
}
