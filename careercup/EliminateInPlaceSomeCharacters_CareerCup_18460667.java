/**
Eliminate all ‘b’ and ‘ac’ in an array of characters, you have to replace them in-place, and you are only allowed to iterate over the char array once.

Examples: 
abc -> ac 
ac->'' 
react->rt

- jeso 2 years ago in Switzerland | Report Duplicate | Flag 
Google Software Engineer / Developer Arrays
**/
class EliminateInPlaceSomeCharacters_CareerCup_18460667{
        public static void main(String[]args){
                System.out.println(eliminate("abc"));
                System.out.println(eliminate("ac"));
                System.out.println(eliminate("react"));
                System.out.println(eliminate("abac"));
                System.out.println(eliminate("adb"));
        }
        private static String eliminate(String s){
                if (s==null) return null;
                char[] chars = s.toCharArray();
                int j = 0;
                for(int i = 0; i<chars.length;){
                        if (chars[i] == 'b'){
                                ++i;
                                continue;
                        }
                        if (chars[i] == 'a' && (i+1)<chars.length && chars[i+1] == 'c'){
                                i+=2;
                                continue;
                        }
                        chars[j] = chars[i];
                        ++j;
                        ++i;
                }
                return new String(chars,0,j);
        }
}
