import java.math.*;
class MyBigInteger{
    public static void main(String[]args){
//        String s1 = "123456789012345";
//        String s2 = "123456789012345";
        String s1 = "10000002345678901234567890000";
        String s2 = "900000087654321098765432100000000000";
        BigDecimal bd1 = new BigDecimal(s1);
        BigDecimal bd2 = new BigDecimal(s2);
        BigDecimal bdMul = bd1.multiply(bd2);
        System.out.println(bdMul);
        int[] mBd1 = convert(s1);
        int[] mBd2 = convert(s2);
        int[] mBd = mul(mBd1, mBd2);
        System.out.println(convert(mBd));
    }
    static final int radix = 10000;
    static final int[] EMPTY_ARRAY = new int[0];
    static String convert(int[] arr){
        if (arr == EMPTY_ARRAY) return "0";
        StringBuilder ret = new StringBuilder();
        ret.append(Integer.toString(arr[arr.length-1]));
        for(int i = arr.length-2; i >= 0; --i){
            String nextDigit = String.format("%04d", arr[i]);
            ret.append(nextDigit);
        }
        return ret.toString();
    }
    static int[] convert(String s){
        if (s == null || s.length() == 0 || "0".equals(s)) return EMPTY_ARRAY;
        int len = s.length()/4 + ((s.length()%4 > 0) ? 1 : 0);
        int[] ret = new int[len];
        for(int i = s.length(), j = 0; i > 0; i-=4, ++j){
            ret[j] = Integer.parseInt(s.substring(Math.max(0, i-4), i));
        }
        return ret;
    }
    static int[] mul(int[] bigIntA, int[] bigIntB){
        if (bigIntA == null) throw new IllegalArgumentException();
        if (bigIntB == null) throw new IllegalArgumentException();

        if (bigIntA.length == 0 || bigIntB.length == 0) return EMPTY_ARRAY;

        int[] ret = new int[bigIntA.length+bigIntB.length];
        for(int i = 0; i < bigIntA.length; ++i){
            int carry = 0;
            for(int j = 0; j < bigIntB.length; ++j){
                int digitIdx = i+j;
                ret[digitIdx] += (carry + bigIntA[i]*bigIntB[j]);
                carry = ret[digitIdx] / radix;
                ret[digitIdx] %= radix;
            }
            ret[i+bigIntB.length]+=carry;
        }
        ret = trim(ret);
        return ret;
    }
    static int[] trim(int[] arr){
        int i;
        for(i = arr.length; i > 0 && arr[i-1] == 0; --i);
        int[] ret = new int[i];
        for(int j = 0; j < ret.length; ++j){
            ret[j] = arr[j];
        }
        return ret;
    }
}
