class StringSearch{
	public static void main(String[] args){
                System.out.println(
                        kmpStringSearch("ababc", "ababdababdababdababc"));
		System.out.println(
			kmpStringSearch("aaaaaaab","aa aaaaaaab"));
                System.out.println(
			kmpStringSearch("a","a"));
		System.out.println(
			kmpStringSearch("abc", "abcabc"));
		System.out.println(
			kmpStringSearch("cde", "abcdefg"));

		System.out.println(
                        kmpStringSearch("ababc", "ababdababdababdababd"));
		System.out.println(
			kmpStringSearch(null, null));
		System.out.println(
			kmpStringSearch("", null));
		System.out.println(
			kmpStringSearch(null, ""));
		System.out.println(
			kmpStringSearch("", ""));
		System.out.println(
			kmpStringSearch("bc",null));
		System.out.println(
			kmpStringSearch("cd", ""));
		
	}
	
	private static int stringSearch(String query, String text){
                if (query == null || text ==  null) return -1;
		if (query == text) return 0;

		int j = 0;
		for(int i = 0; i <= text.length() - query.length(); i++){
			for(j = 0; j < query.length()
					&& query.charAt(j) == text.charAt(i+j); j++);
			if (j == query.length()) return i;
		}
		return -1;
	}

	public static int kmpStringSearch(String pattern, String text){
                if (pattern == null || text ==  null) return -1;
                if (pattern == text) return 0;
		
		int[] mbw = maxBorderWidth(pattern);

		for(int i = 0, j = 0; i < text.length();){
			for(; j >= 0 && pattern.charAt(j) != text.charAt(i); j = mbw[j]);
			i++; j++;
			if (j == pattern.length()) {
				return i-j;
				//j = mbw[j];//next match
			}
		}
		return -1;
	}

	// i-th position is for each prefix with length i
	// the maximum border width.
	// Border = a proper prefix which is a proper suffix
	private static int[] maxBorderWidth(String pattern){
		int[] maxBorderWidth = new int[pattern.length()+1];
	
		int j = -1, i = 0;
		maxBorderWidth[i] = j;
		for(; i < pattern.length();){
			for(; j>=0 && pattern.charAt(i) != pattern.charAt(j); j=maxBorderWidth[j]);
			i++; j++;
			maxBorderWidth[i] = j;
		}
		return maxBorderWidth;
	}
}
