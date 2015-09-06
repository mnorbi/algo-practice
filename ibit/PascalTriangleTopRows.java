import java.util.*;
class PascalTriangleTopRows{
        public static void main(String[]args){
          System.out.println(generate(3));
        }
	static ArrayList<ArrayList<Integer>> generate(int a) {
	    ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
	    if (a > 0){
	        ArrayList<Integer> l = new ArrayList<>(1);
	        l.add(1);
	        ret.add(l);
	    }
	    if (a > 1){
	        ArrayList<Integer> l = new ArrayList<>(2);
	        l.add(1); l.add(1);
	        ret.add(l);
	    }
	    for(int i = 2; i < a; ++i){
	        ArrayList<Integer> l = new ArrayList<>(i+1);
	        ret.add(l);
	        l.add(1);
	        
	        ArrayList<Integer> prev = ret.get(i-1);
	        for(int j = 1; j < i; ++j){
	            l.add(prev.get(j-1)+prev.get(j));
	        }
	        l.add(1);
	    }
	    return ret;
	}
}
