import java.util.*;
class Poetry{
    public String rhymeScheme(String[]poem){
        Map<String, Character> patterns = new HashMap<>();
        char[] ret = new char[poem.length];
        int distinctRhymeCnt = 0; int retSize = 0;
        for(int a = 0; a < poem.length; ++a){
            int end = poem[a].length(); while(end > 0 && poem[a].charAt(end-1) == ' ') --end;
            if (end == 0) {
                ret[retSize++] = ' ';
                continue;
            }
            int b = end-1;
            char prev = 'z';
            if (isY(poem[a].charAt(b))) --b;
            for(;b >=0; --b){
                char cur = poem[a].charAt(b);
                String sfx = null;
                if (b == 0 || poem[a].charAt(b-1) == ' ' || !isWovel(cur)&&isWovel(prev)){
                    if (!isWovel(cur) || isY(cur)) ++b;
                    sfx = poem[a].substring(b, end).toLowerCase();
                }
                if (sfx != null){
                    if (!patterns.containsKey(sfx)){
                        patterns.put(sfx, charFor(distinctRhymeCnt++));
                    }
                    ret[retSize++] = patterns.get(sfx);
                    break;
                }
                prev = cur;
            }
        }
        return new String(ret, 0, retSize);
    }
    private boolean isY(char c){
        return c == 'y' || c == 'Y';
    }
    private static final char[] vowels = "aeiouyAEIOUY".toCharArray();
    private boolean isWovel(char c){
        for(int a = 0; a < vowels.length; ++a){
            if (vowels[a] == c) return true;
        }
        return false;
    }
    private char charFor(int id){
        if ('a'+id <= 'z') return (char)('a'+id);
        return (char)('A'+(id-1-'z'+'a'));
    }

    public static void main(String[]args){
        Poetry solver = new Poetry();
        System.out.println(solver.rhymeScheme(
                new String[]{"I hope this problem", "is a whole lot better than", "this stupid haiku"}
        ));
        System.out.println(solver.rhymeScheme(
                new String[]{" ", "Measure your height", "AND WEIGHT ", "said the doctor", "", "And make sure to take your pills", " to cure your ills", "Every", "DAY"}
        ));
        System.out.println(solver.rhymeScheme(
                new String[]{"One bright day in the middle of the night", "Two dead boys got up to fight", "Back to back they faced each other", "Drew their swords and shot each other", "", "A deaf policeman heard the noise", "And came to arrest the two dead boys", "And if you dont believe this lie is true", "Ask the blind man he saw it too"}
        ));
        System.out.println(solver.rhymeScheme(
                new String[]{"", "", "", ""}
        ));
        System.out.println(solver.rhymeScheme(
                new String[]{"This poem has uppercase letters", "In its rhyme scheme", "Alpha", "Blaster", "Cat", "Desert", "Elephant", "Frog", "Gulch", "Horse", "Ireland", "Jam", "Krispy Kreme", "Loofah", "Moo", "Narf", "Old", "Pink", "Quash", "Rainbow", "Star", "Tour", "Uvula", "Very", "Will", "Xmas", "Young", "Zed", "deception", "comic", "grout", "oval", "cable", "rob", "steal", "steel", "weak"}
        ));
        System.out.println(solver.rhymeScheme(
	  new String[]{" ", " ", "This poem", " ", " ", " ", "", "Has lots of blank lines", " ", " ", " ", " ", " ", " ", " ", " in it "}
        ));
        System.out.println(solver.rhymeScheme(
		new String[]{"too bad your", " solution went sour"}
        ));
     }
}
