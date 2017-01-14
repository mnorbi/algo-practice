public class GroupShiftedString {
    public static void main(String[] args) {
        System.out.println(
                new GroupShiftedString().groupShiftedString(new String[]{
                    "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"
                })
        );
    }

    /*
	Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
	"abc" -> "bcd" -> ... -> "xyz" Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
	For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], A solution is:
	[ ["abc","bcd","xyz"], ["az","ba"], ["acef"], ["a","z"] ]

    */
    private Map<String, List<String>> groupShiftedString(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strings){
            String key = getKey(s);
            if (!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return map;
    }
    private String getKey(String s){
        if (s == null || s == "") return s;
        StringBuilder res = new StringBuilder();
        int range = 'z'-'a'+1;
        int diff = 'z' - s.charAt(0);
        for(int a = 0; a < s.length(); ++a){
            res.append((char)('a'+((s.charAt(a)-'a')+diff)%range));
        }
        return res.toString();
    }
}
