public class Solution {
	public ArrayList<String> prettyJSON(String a) {
	    List<String> ret = new LinkedList<>();
	    int tabCount = 0;
	    for(int i = 0; i < a.length();){
	        char c = a.charAt(i);
	        StringBuilder sb = new StringBuilder();
	        if (leftBracket(c)){
	            appendTabs(sb,tabCount++);
	            sb.append(c);
	            ++i;
	        } else if (rightBracket(c)){
	            appendTabs(sb,--tabCount);
	            sb.append(c);
	            ++i;
	            if (i < a.length()){
	                c = a.charAt(i);
	                if (c == ','){
	                    sb.append(c);
	                    ++i;
	                }
	            }
	        } else {
	            int j = i+1;
	            for(;j < a.length(); ++j){
	                c = a.charAt(j);
	                if (bracket(c)){
	                    break;
	                } else if (c == ','){
	                    ++j;
	                    break;
	                }
	            }
	            appendTabs(sb,tabCount);
	            sb.append(a.substring(i,j));
	            i = j;
	        }
	        ret.add(sb.toString());
	    }
	    return new ArrayList<String>(ret);
	}
	void appendTabs(StringBuilder sb, int count){
	    if (count == 0) return;
	    for(int i = 0; i < count; ++i){
	        sb.append('\t');
	    }
	}
	boolean leftBracket(char c){
	     boolean ret = c == '[' || c == '{';
	     return ret;
	}
	boolean rightBracket(char c){
	    boolean ret = c == ']' || c == '}';
	    return ret;
	}
	boolean bracket(char c){
	    boolean ret = leftBracket(c) || rightBracket(c);
	    return ret;
	}
}

