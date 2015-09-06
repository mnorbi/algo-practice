public class Solution {
	public int romanToInt(String a) {
	    int ret = 0;
	    for(int i = 0; i < a.length();){
	        char c1 = charAt(a, i);
	        char c2 = charAt(a, i+1);
	        if (isSub(c1, c2)){
	            ret += intVal(c2)-intVal(c1);
	            i+=2;
	        } else {
	            ret += intVal(c1);
	            i+=1;
	        }
	    }
	    return ret;
	}
	boolean isSub(char c1, char c2){
	    if (c1 == 'I'){
	       if (c2 == 'V' || c2 == 'X'){
	           return true;
	       }
	       return false;
	    }
	    if (c1 == 'X'){
	        if (c2 == 'L' || c2 == 'C'){
	            return true;
	        }
	        return false;
	    }
	    if (c1 == 'C'){
	        if (c2 == 'D' || c2 == 'M'){
	            return true;
	        }
	        return false;
	    }
	    return false;
	}
	char charAt(String a, int i){
	    if (i >= a.length()) return '#';
	    return a.charAt(i);
	}
	int intVal(char c){
	    switch(c){
	        case 'I': return 1;
	        case 'V': return 5;
	        case 'X': return 10;
	        case 'L': return 50;
	        case 'C': return 100;
	        case 'D': return 500;
	        case 'M': return 1000;
            default: return 0;
	    }
	}
}

