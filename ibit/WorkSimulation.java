public class Solution {
	public int paint(int a, int b, ArrayList<Integer> c) {
	    PriorityQueue<Integer> jobQueue = new PriorityQueue<>(c.size(), Collections.reverseOrder());
	    for(int i : c){
	        jobQueue.add(i);
	    }
	    PriorityQueue<Work> workerQueue = new PriorityQueue<>(a);
	    for(int i = 0; i < a; ++i){
	        workerQueue.add(new Work(i, 0));
	    }
	    
	    while(!jobQueue.isEmpty()){
	        int jobLength = jobQueue.remove();
	        Work workDone = workerQueue.remove();
	        workerQueue.add(new Work(workDone.workerId, workDone.end+jobLength));
	    }
	    int end = 0;
	    while(!workerQueue.isEmpty()){
	        Work workDone = workerQueue.remove();
	        end = Math.max(end, workDone.end);
	    }
	    return end*b;
	}
}
class Work implements Comparable<Work>{
    int end;
    int workerId;
    
    Work(int workerId, int e){
        this.workerId = workerId;
        this.end = e;
    }
    public int compareTo(Work other){
        return Integer.compare(this.end, other.end);
    }
}

