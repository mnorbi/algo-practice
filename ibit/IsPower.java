public class Solution {
    public boolean isPower(int A) {
        if (A == 0) return false;
        if (A == 1) return true;
        for(int i = 2 + A%2; i*i <= A; i+=2){
            int x = A;
            int y = i;
            for(;x != 1;){
                if (x%y == 0){
                    x /= y;
                    y = y*y;
                } else if (y != i){
                    y = i;
                } else {
                    break;
                }
            }
            if (x == 1) return true;
        }
        return false;
    }
}
