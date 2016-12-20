/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
To scramble the string, we may choose any non-leaf node and swap its two children.
Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/
class Solution {

    public boolean isScramble(String s1, String s2) {

        if (s1 == null && s2 == null) return true;

        if (s1 == null || s2 == null) return false;

        if (s1.length() != s2.length()) return false;

        int N = s1.length();

        boolean[][][] dp = new boolean[N+1][N+1][N+1];

        for(int len = 1; len <= N; ++len){

            for(int i1 = 0; i1+len <= N; ++i1){

                for(int i2 = 0; i2+len <= N; ++i2){

                    if (len == 1){

                        dp[len][i1][i2] = s1.charAt(i1) == s2.charAt(i2);

                        continue;

                    }

                    for(int cut = 1; cut < len && !dp[len][i1][i2]; ++cut){

                        dp[len][i1][i2] = 

                            dp[cut][i1][i2+len-cut] && dp[len-cut][i1+cut][i2]

                            || dp[cut][i1][i2] && dp[len-cut][i1+cut][i2+cut];

                    }

                }

            }

        }

        return dp[N][0][0];

    }

    public boolean isScrambleV2(String s1, String s2) {

        Boolean[][][] dp = new Boolean[s1.length()+1][s1.length()+1][s1.length()+1];

        return isScramble(s1, s2, 0, 0, s1.length(), dp);

    }

    public boolean isScramble(String s1, String s2, int i1, int i2, int len, Boolean[][][] dp){

        if (dp[i1][i2][len] != null){ return dp[i1][i2][len]; }

        boolean ret = len == 1 ? s1.charAt(i1) == s2.charAt(i2) : false;

        for (int nl = 1; nl < len && !ret; ++nl) {

            ret = isScramble(s1, s2, i1, i2 + len - nl, nl, dp) && isScramble(s1, s2, i1 + nl, i2, len - nl, dp);

            ret = ret || isScramble(s1, s2, i1, i2, nl, dp) && isScramble(s1, s2, i1 + nl, i2 + nl, len - nl, dp);

        }

        return dp[i1][i2][len] = ret;

    }

}
