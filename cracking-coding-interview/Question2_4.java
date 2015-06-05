import java.util.*;

public class Question2_4{
	public static void main(String[] args){
                Node n = Node.of(new int[] { 1, 3, 5, 7, 9, 2, 4, 6, 8 });
                //Node n = Node.of(new int[] { 5, 1, 5});
		//System.out.println(n.chainToString());
		System.out.println(n.partition(5).chainToString());
	}
	
}
class Node{
	int val;
	Node next;

	static Node of(int[] arr){
		Node head = new Node();
		Node cur = head;
		for(int i : arr){
			Node n = new Node(); n.val = i;
			cur.next = n; 
			cur = cur.next;
		}
		return head;
	}

	Node partition(int x){
		Node head = this;
		if (head.next == null || head.next.next == null) return this;
		Node prev = head.next, cur = prev.next;
		while(cur != null){
			Node next = cur.next;
			if (cur.val < x){
				Node tmp = cur.next;
				cur.next = head.next;
				head.next = cur;
				prev.next = next;
			} else {
				prev = cur;
			}
			cur = next;
		}

		return this;
	}

	String chainToString(){
		StringBuilder sb = new StringBuilder();
		for(Node cur = this; cur.next != null; cur = cur.next){
			sb.append(cur.next.val);
			sb.append(" ");
		}
		return sb.toString();
	}
}
