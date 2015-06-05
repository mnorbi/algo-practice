class LongesCommonSubsequence{
        public static void main(String[]args){
                LongesCommonSubsequence solver = new LongesCommonSubsequence();
                assert print(solver.find("ABCDGH", "AEDFHR")).equals("ADH");
                assert print(solver.find("AGGTAB","GXTXAYB")).equals("GTAB");
        }

        private static String print(String s){
                System.out.println(s);
                return s;
        }

        public String find(String s1, String s2){
                if (s1 == s2) return s1;
                if (s1 == null || s1.length() == 0) return null;
                if (s2 == null || s2.length() == 0) return null;

                int[][] dp = new int[s1.length()+1][s2.length()+1];
                for(int i = 1; i <= s1.length(); ++i){
                        char c1 = s1.charAt(i-1);
                        for(int k = 1; k <= s2.length(); ++k){
                                dp[i][k] = Math.max(dp[i][k-1], dp[i-1][k]);
                                char c2 = s2.charAt(k-1);
                                if (c1 == c2){
                                        dp[i][k] = Math.max(dp[i][k], dp[i-1][k-1] + 1);
                                }
                        }
                }
                char[] ret = new char[dp[s1.length()][ s2.length()]];
                for(int r = ret.length-1, i = s1.length(), k = s2.length(); r >= 0;){
                        char c1 = s1.charAt(i-1);
                        char c2 = s2.charAt(k-1);

                        if (c1 == c2 && dp[i-1][k-1] < dp[i][k]){
                                ret[r] = c1;
                                --r;
                                --i;
                                --k;
                                continue;
                        }
                        if (dp[i-1][k] > dp[i][k-1]){
                                --i;
                                continue;
                        }
                        --k;
                }
                return new String(ret);
        }
}
