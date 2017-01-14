public class NthDigit {
    public static void main(String[] args) {
        System.out.println(new NthDigit().nthDigit(9+90*2+900*3+4));
    }

    /*
	Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
	Note: n is positive and will fit within the range of a 32-bit signed integer (n < 231).
	Example 1:
	Input: 3
	Output: 3 Example 2:
	Input: 11
	Output: 0
	Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

    */
    int nthDigit(int n){
        if (n < 0) { n = -n; }
        --n;
        int cnt = 9;
        int width = 1;
        while(n-width*cnt >= 0){
            n -= width*cnt;
            ++width;
            cnt *= 10;
        }
        int firstNumber = cnt/9;
        int targetNumber = firstNumber + n/width;
        int res = kthDigitOf(targetNumber, width, n%width);
        return res;
    }

    /* k = 0 ==> most significant digit
     * ...
     * k = width-1 ==> least significant digit
    */
    int kthDigitOf(long number, int width, int k){
        for(int a = width-1 -k; a > 0; --a){
            number /= 10;
        }
        int res = (int)(number%10);
        return res;
    }

}
