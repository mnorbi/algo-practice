public class SlidingMax {
	// DO NOT MODIFY THE LIST
	public ArrayList<Integer> slidingMaximum(final List<Integer> a, int w) {
	    ArrayList<Integer> B = new ArrayList<Integer>();
	    ArrayDeque<Integer> deq = new ArrayDeque<>();
	    
	    int i = 0;
	    for(;i < w && i < a.size();++i){
	        addSlideIn(deq, a, i);
	    }
	    for(;i <= a.size();++i){
	        B.add(a.get(deq.getFirst()));
	        removeSlideOut(deq, a, i, w);
            addSlideIn(deq, a, i);
	    }
	    
	    return B;
	}
	void removeSlideOut(ArrayDeque<Integer> deq, List<Integer> a, int nextId, int w){
        while(!deq.isEmpty() && Integer.compare(deq.getFirst(), nextId-w) <= 0){
            deq.removeFirst();
        }
	}
	void addSlideIn(ArrayDeque<Integer>deq, List<Integer> a, int nextId){
	    if (nextId >= a.size()) return;
        while(!deq.isEmpty() && Integer.compare(a.get(deq.getLast()), a.get(nextId)) <= 0){
                deq.removeLast();
        }
        deq.addLast(nextId);
	}
}
