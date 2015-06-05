import java.util.*;

public class Question112{
	public static void main(String... args){
		testAnagrams(new String[] {
                        "abcde",
                        "efg",
                        "efgh",
                        "ijkl",
                        "abdce",
                        "kijl"
                });
	}
	private static void testAnagrams(String[] in){
		String[] anagrams = anagrams(in);
		print(anagrams);
		anagrams = primeHashBasedAnagrams(in);
		print(anagrams);
	}

	private static String[] anagrams(String[] in){
		Map<String, List<String>> map = new HashMap<>();
		
		for(String s : in){
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			String key = new String(chars);
			if (!map.containsKey(key)){
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(s);
		}
		
		List<String> ret = new ArrayList<String>();

		for(List<String> anagramList : map.values()){
			ret.addAll(anagramList);
		}

		return ret.toArray(new String[0]);
	}

	private static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

	private static String[] primeHashBasedAnagrams(String[] in){
		Map<Integer, List<String>> map = new HashMap<>();

		for(String s : in){
			Integer key = primeHash(s);
			if (!map.containsKey(key)){
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(s);
		}

		List<String> ret = new ArrayList<String>();

		for(List<String> anagramList : map.values()){
			ret.addAll(anagramList);
		}

		return ret.toArray(new String[0]);
	}

	private static int primeHash(String s){
		int ret = 1;
		for(int i = 0; i < s.length(); i++){
			ret *= primes[s.charAt(i)-'a'];
		}

		return ret;
	}

	private static void print(String[] strings){
		for(String s : strings){
			System.out.println(s);
		}
		System.out.println();
	}
}
