/**
Given a string, find the rank of the string amongst its permutations sorted lexicographically. 
Assume that no characters are repeated.

Example :

Input : 'acb'
Output : 2

The order permutations with letters 'a', 'c', and 'b' : 
abc
acb
bac
bca
cab
cba
The answer might not fit in an integer, so return your answer % 1000003
**/
class Rank1{
	public int findRank(String A) {
	    int FIT = 1000003;
	    int N = A.length();
	    TreeSet<Character> set = new TreeSet<>();
	    for(int i = 0; i < N; ++i){
	        set.add(A.charAt(i));
	    }
	    int[] fact = createFactorials(N, FIT);
	    int ret = 1;
	    for(int i = 0; i < N; ++i){
	        char c = A.charAt(i);
	        ret = (ret + (rank(set, c)*fact[N-i-1])%FIT)%FIT;
	        set.remove(c);
	    }
	    return ret;
	}
	int[] createFactorials(int N, int FIT){
	    int[] ret = new int[N+1];
	    ret[0] = 1;
	    for(int i = 1; i <= N; ++i){
	        ret[i] = (ret[i-1]*(i%FIT))%FIT;
	    }
	    return ret;
	}
	int rank(TreeSet<Character> set, Character c){
	    return set.headSet(c, false).size();
	}
}
