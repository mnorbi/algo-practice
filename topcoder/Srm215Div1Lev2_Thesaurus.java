import java.util.*;
/**

Problem Statement
    
An entry in a Thesaurus is a list of words that are all synonyms. Each entry contains no duplicates within it. It is possible that two entries might have some common words, but the editors (who are somewhat cheap) have decided that if any two entries have 2 or more words in common then they should be combined into a single entry.
This editing process may produce new entries which can be combined. The final Thesaurus must contain no pair of entries that have 2 or more words in common. Of course, each entry must contain no duplicates.
Create a class Thesaurus that contains a method edit that is given a String[] entry, the entries in the original Thesaurus. The method returns the edited Thesaurus as a String[]. Each element of entry has no leading or trailing spaces and has its words separated by a single space. Each element of the return should also have no leading or trailing spaces and have its words separated by a single space. In addition, the words within each element of the return must be in alphabetical order, and the elements in the return must appear in alphabetical order.
Definition
    
Class:
Thesaurus
Method:
edit
Parameters:
String[]
Returns:
String[]
Method signature:
String[] edit(String[] entry)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
entry will contain between 1 and 50 elements inclusive.
-
Each element of entry will contain between 1 and 50 characters inclusive.
-
Each element of entry will consist of 1 or more "words" separated by single spaces.
-
Each element of entry will contain no leading or trailing spaces.
-
Each "word" will consist of 1 or more lowercase letters 'a'-'z'
-
No element of entry will contain two identical words.
Examples
0)

    
{"ape monkey wrench", "wrench twist strain"}
Returns: { "ape monkey wrench",  "strain twist wrench" }
These two entries have only one common word so they cannot be combined. After rearranging the words within each entry to put the words into alphabetical order, the first entry is first alphabetically.
1)

    
{"ape monkey wrench", "wrench twist strain", "monkey twist frugue"}
Returns: { "ape monkey wrench",  "frugue monkey twist",  "strain twist wrench" }
No entries could be combined, but two had to be arranged, and the order was changed.
2)

    
{"ape monkey wrench", "wrench twist strain", "monkey twist frugue strain"}
Returns: { "ape frugue monkey strain twist wrench" }
The first two entries could not be combined, but the last two could. After they were combined, the first entry shared both "wrench" and "monkey" with the new combined entry, so we ended up with just one entry.
3)

    
{"point run score","point dot","cut run tear score","cut valley","cute pretty"}
Returns: { "cut point run score tear",  "cut valley",  "cute pretty",  "dot point" }

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class Thesaurus{
        public String[] edit(String[] entries){
                List<SortedSet<String>> listSet = new ArrayList<SortedSet<String>>();
                for(String entry : entries){
                        listSet.add(new TreeSet<String>(Arrays.asList(entry.split(" "))));
                }
                for(int i = 0; i < listSet.size(); i++){
                        for(int j = i+1; j < listSet.size(); j++){
                                if (isMergeable(listSet.get(i), listSet.get(j))){
                                        listSet.get(i).addAll(listSet.get(j));
                                        listSet.remove(j);
                                        i = -1;
                                        break;
                                }
                        }
                }
                return toArray(listSet);
        }

        private boolean isMergeable(SortedSet<String> aSet, SortedSet<String> bSet){
                int matchCount = 0;

                boolean fetchA = true, fetchB = true;
                String a = null, b = null;
                for(Iterator<String> aIt = aSet.iterator(), bIt = bSet.iterator(); fetchA || fetchB;){
                        if (fetchA){
                                if (!aIt.hasNext()) break;
                                a = aIt.next();
                                fetchA = false;
                        }
                        if (fetchB){
                                if (!bIt.hasNext()) break;
                                b = bIt.next();
                                fetchB = false;
                        }

                        int cmp = a.compareTo(b);
                        if (cmp < 0){
                                fetchA = true;
                        } else if (cmp > 0){
                                fetchB = true;
                        } else {
                                fetchA = true;
                                fetchB = true;
                                ++matchCount;
                        }
                }
                return matchCount > 1;
        }

        private void print(Set<String> set){
                System.out.print("|");
                for(String s : set){
                        System.out.print(s);
                        System.out.print(" ");
                }
                System.out.print("|");
                System.out.println();
        }

        private String[] toArray(List<SortedSet<String>> listSet){
                String[] ret = new String[listSet.size()];
                for(int i = 0; i < ret.length; i++){
                        StringBuilder sb = new StringBuilder();
                        for(String s : listSet.get(i)){
                                sb.append(s);
                                sb.append(" ");
                        }
                        ret[i] = trimEndSpace(sb.toString());
                }
                Arrays.sort(ret);
                return ret;
        }

        private String trimEndSpace(String s){
                return s.substring(0, s.length()-1);
        }
}
