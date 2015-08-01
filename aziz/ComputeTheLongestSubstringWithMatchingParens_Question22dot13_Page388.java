class ComputeTheLongestSubstringWithMatchingParens_Question22dot13_Page388{
    public static void main(String[]args){
        System.out.println(validParanthesizationMaxLen("(()(()(()(()(()(()))(()(()"));
        System.out.println(validParanthesizationMaxLen("((())()(()("));
    }
    
    public static int validParanthesizationMaxLen(String str){
        if (str == null || str.length() == 0) return 0;
        return Math.max(leftMax(str), rightMax(str));
    }

    static int leftMax(String str){
        return max(str, '(', 0, str.length()-1, 1);
    }

    static int rightMax(String str){
        return max(str, ')', str.length()-1, 0, -1);
    }

    static int max(String str, char par, int start, int end, int dir){
        int max = 0;
        int balance = 0;
        int len = 0;
        for(int i = dir*start; i <= dir*end; ++i){
            char c = str.charAt(dir*i);
            if (c == par){
                ++balance;
                ++len;
            } else if (balance > 1){
                --balance;
                ++len;
            } else if (balance < 1){
                balance = 0;
                len = 0;
            } else {
                --balance;
                ++len;
                max = Math.max(max, len);
            }
        }
        return max;
    }
    private static String toPars(long l) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 64; ++i){
            long mask = 1 << i;
            if ((l & mask) == 0){
                sb.append('(');
            } else {
                sb.append(')');
            }
        }
        return sb.toString();
    }
}
