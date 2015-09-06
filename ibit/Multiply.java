class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().multiply("99", "99"));
    }
    public String multiply(String a, String b) {
        int[] ret = new int[a.length()+b.length()+1];
        for(int i = a.length(), cP = ret.length-b.length(); i > 0; --i, --cP){
            int cry = 0;
            for(int j = b.length(); j > 0; --j){
                int k = i+j+1;
                ret[k-1] += cry + (a.charAt(i-1)-'0')*(b.charAt(j-1)-'0');
                cry = ret[k-1]/10;
                ret[k-1] %= 10;
            }
            ret[cP-1] += cry;
        }
        int i = 0;
        for(; i < ret.length && ret[i] == 0; ++i);
        if (i >= ret.length) return "0";
        StringBuilder sb = new StringBuilder();
        for(; i < ret.length; ++i){
            sb.append(ret[i]);
        }
        return sb.toString();
    }
}
