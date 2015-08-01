import java.util.*;
class MaxSlidingWindow{
    public static void main(String[]args){
        Node[] arr = new Node[8];
        int i = 0;
        arr[i++] = new Node(1.3, 0);
        arr[i++] = new Node(2.5, 2);
        arr[i++] = new Node(3.7, 3);
        arr[i++] = new Node(1.4, 5);
        arr[i++] = new Node(2.6, 6);
        arr[i++] = new Node(2.2, 8);
        arr[i++] = new Node(1.7, 9);
        arr[i++] = new Node(1.7, 14);
        print(maxSlidingWindow(arr, 3));
    }
    public static List<Node> maxSlidingWindow(Node[] arr, int timeInterval){
        if (arr == null || arr.length == 0) return Collections.emptyList();
        Arrays.sort(arr, Node.TIMESTAMP_COMPARATOR);
        Deque<Node> deq = new ArrayDeque<>(timeInterval);
        List<Node> ret = new ArrayList<>();
        for(int i = 0, time = 0; i < arr.length; ++i){
            Node next = arr[i];
            while(time < next.time && !deq.isEmpty()){
                if(time < deq.peekFirst().time){
                    time = deq.peekFirst().time;
                } else if(time <= deq.peekFirst().time+timeInterval){
                    ret.add(new Node(deq.peekFirst().val, time++));
                } else {
                    deq.removeFirst();
                }
            }
            while(!deq.isEmpty() && deq.peekLast().time+timeInterval >= next.time && compareNodes(deq.peekLast(), next) <= 0){
                deq.removeLast();
            }
            deq.addLast(next);
            if (time < next.time){
                time = next.time;
            }
            ret.add(new Node(deq.peekFirst().val, time++));
        }
        return ret;
    }
    static int compareNodes(Node a, Node b){
        return Node.VALUE_COMPARATOR.compare(a, b);
    }
    static void print(List<Node> nodes){
        for(Iterator<Node> it = nodes.iterator(); it.hasNext();){
            Node next = it.next();
            System.out.print(next);
            System.out.print(" ");
        }
    }
    static class Node{
        private static final Comparator<Node> TIMESTAMP_COMPARATOR = new Comparator<Node>(){
            public int compare(Node a, Node b){
                return Integer.compare(a.time, b.time);
            }
        };
        private static final Comparator<Node> VALUE_COMPARATOR = new Comparator<Node>(){
            public int compare(Node a, Node b){
                return Double.compare(a.val, b.val);
            }
        };
        int time;
        double val;
        Node(double val, int time){
            this.time = time;
            this.val = val;
        }
        public String toString(){
            return String.format("time[%d],val[%f]",time,val);
        }
    }
}
