import java.util.*;

/**
	Mistakes:
		1. indexing: bad condition at end (isMergeable implementation exited too early when an iterator reached and but last element still in scope)
**/
public class Srm215Dev1Lev2{
	public static void main(String[] args){
		Thesaurus solver = new Thesaurus();
		print(solver.edit(new String[] {"point run score","point dot","cut run tear score","cut valley","cute pretty"}));
                print(solver.edit(new String[]{"ape monkey wrench", "wrench twist strain", "monkey twist frugue strain"}));
		print(solver.edit(new String[] {"ape monkey wrench", "wrench twist strain", "monkey twist frugue"}));
		print(solver.edit(new String[] {"ape monkey wrench", "wrench twist strain"}));
		print(solver.edit(new String[]{"ape monkey wrench", "frugue monkey strain twist wrench"}));
	}
	private static void print(String[] entries){
		for(String s : entries){
			System.out.println(s);
		}
		System.out.println();
	}
}
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
