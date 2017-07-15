import java.util.Set;

import static java.util.Arrays.fill;
import static java.util.stream.Collectors.toSet;

class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || n1 == 0 || n2 == 0) { return 0; }
        Set<Character> s1Chars = s1.chars().mapToObj(c->(char)c).collect(toSet());
        Set<Character> s2Chars = s2.chars().mapToObj(c->(char)c).collect(toSet());
        s2Chars.removeAll(s1Chars);
        if (s2Chars.size() > 0){ return 0; }

        int[][] visited = new int[2][s1.length()+1];
        fill(visited[0], -1);
        fill(visited[1], -1);
        int s1Period = 0, s2Period = 0;
        int s1Cnt = 0, s2Cnt = 0;
        int a = 0, b = 0;
        for(boolean doBreak = false; n1 > 0 && !doBreak;){
            if (s1.charAt(a) == s2.charAt(b)){
                ++b;
                if (b == s2.length()){
                    b = 0;
                    ++s2Cnt;
                    if (visited[0][a] != -1){
                        s1Period = s1Cnt-visited[0][a];
                        s2Period = s2Cnt-visited[1][a];
                        doBreak = true;
                    } else {
                        visited[0][a] = s1Cnt;
                        visited[1][a] = s2Cnt;
                    }
                }
            }
            ++a;
            if (a == s1.length()){
                a = 0;
                ++s1Cnt;
                --n1;
            }
        }
        int res = (s2Cnt/n2);
        s2Cnt %= n2;
        if (n1 == 0){
            return res;
        }
        int periodCnt = (n1-1)/s1Period;
        n1 -= periodCnt*s1Period;
        res += (periodCnt*s2Period)/n2;
        s2Cnt += (periodCnt*s2Period)%n2;
        while(n1 > 0){
            if (s1.charAt(a) == s2.charAt(b)){
                ++b;
                if (b == s2.length()){
                    b = 0;
                    ++s2Cnt;
                }
            }
            ++a;
            if (a == s1.length()){
                a = 0;
                --n1;
            }
        }
        res += s2Cnt/n2;
        return res;
    }
}

