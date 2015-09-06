public class Solution {
	public ArrayList<String> fullJustify(ArrayList<String> a, int b) {
	    ArrayList<String> ret = new ArrayList<>();
	    for(int i = 0, j = 0; i < a.size(); i = j){
	        int l = -1;
	        int nextL = l;
	        for(j = i;j < a.size();){
	            String nextWord = a.get(j);
	            nextL = l+nextWord.length()+1;
	            if (nextL < b){
	                ++j;
	                l = nextL;
	            } else if (nextL == b){
	                ++j;
	                l = b;
	                break;
	            } else {
	                break;
	            }
	        }
	        int spreadSpace = 1;
	        int headSpace = 0;
	        if (j-i-1 > 0 && j < a.size()){
	            spreadSpace += (b-l)/(j-i-1);
	            headSpace = (b-l)%(j-i-1);
	        }
	        StringBuilder sb = new StringBuilder();
	        Iterator<String> it = a.subList(i,j).iterator();
	        sb.append(it.next());
	        while(it.hasNext() && headSpace > 0){
	            addSpace(sb, spreadSpace+1);
	            sb.append(it.next());
	            --headSpace;
	        }
	        while(it.hasNext()){
	            addSpace(sb, spreadSpace);
	            sb.append(it.next());
	        }
	        while(sb.length() < b){
	            sb.append(' ');
	        }
	        ret.add(sb.toString());
	    }
	    return ret;
	}
	void addSpace(StringBuilder sb, int count){
	    if (count == 0) return;
	    while(count-->0){
	        sb.append(' ');
	    }
	}
}
