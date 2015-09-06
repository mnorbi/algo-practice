public class Solution {
	public String reverseWords(String a) {
	    char[] arr = a.toCharArray();
	    reverse(arr, 0, arr.length);
	    int len = compressWhitespace(arr);
	    reverseWords(arr, len);
	    return new String(arr, 0, len);
	}
	void reverseWords(char[]arr, int len){
	    for(int i = 0, j = 0; i < len;){
	        for(i = j; i < len && Character.isWhitespace(arr[i]); ++i);
	        for(j = i; j < len && !Character.isWhitespace(arr[j]); ++j);
	        reverse(arr, i, j);
	    }
	}
	int compressWhitespace(char[]arr){
	    int to = 0;
	    for(int from = 0; from < arr.length;){
	        for(;from < arr.length && Character.isWhitespace(arr[from]); ++from);
	        for(;from < arr.length && !Character.isWhitespace(arr[from]);){
	            arr[to++] = arr[from++];
	        }
	        if (to < arr.length){
	            arr[to++] = ' ';
	        }
	    }
	    for(;to > 0 && Character.isWhitespace(arr[to-1]); --to);
	    return to;
	}
	void reverse(char[]arr, int lo, int hi){
	    if (lo >= arr.length) return;
	    
	    for(int i = lo, j = hi-1; i < j; ++i, --j){
	        char tmp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = tmp;
	    }
	}
}

