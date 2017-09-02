import java.util.*;

class Solution {
    public String removeDuplicateLetters(String s) {
        int[][] idxs = new int['z'+1][];
        int[] cnts = new int['z'+1];
        for(int a = 0; a < s.length(); ++a){
            ++cnts[s.charAt(a)];
        }
        for(int a = 0; a < cnts.length; ++a){
            idxs[a] = new int[cnts[a]] ;
        }
        for(int a = 0; a < s.length(); ++a){
            int charId = s.charAt(a);
            int pos = idxs[charId].length-cnts[charId];
            idxs[charId][pos] = a;
            --cnts[charId];
        }
        LinkedList<Character> chars = new LinkedList<>();
        for(char a = 'a'; a <= 'z'; ++a){
            if (idxs[a].length > 0){
                chars.addLast(a);
            }
        }
        int cnt = chars.size();
        StringBuilder sb = new StringBuilder(cnt);
        while(cnt > 0){
            char next = removeNext(chars, idxs, cnts);
            sb.append(next);
            --cnt;
        }
        return sb.toString();
    }

    char removeNext(LinkedList<Character> chars, int[][]idxs, int[]cursors){
        Iterator<Character> it = chars.iterator();
        while(it.hasNext()){
            char next = it.next();
            int charIdx = idxs[next][cursors[next]];
            if (isRemovable(charIdx, chars, idxs)){
                it.remove();
                stepCursors(charIdx, chars, idxs, cursors);
                return next;
            }
        }
        throw new IllegalStateException();
    }
    boolean isRemovable(int charIdx, List<Character> chars, int[][]idxs){
        for(char c : chars){
            if (idxs[c][idxs[c].length-1] < charIdx) return false;
        }
        return true;
    }
    void stepCursors(int charIdx, List<Character> chars, int[][]idxs, int[]cursors){
        for(char c : chars){
            while(idxs[c][cursors[c]] < charIdx) ++cursors[c];
        }
    }
}
