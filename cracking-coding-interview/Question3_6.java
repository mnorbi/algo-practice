import java.util.*;

public class Question3_6{
	public static void main(String[] args){
		SorterStack ss = new SorterStack();
		ss.push(1);
		ss.push(3);
		ss.push(5);
		ss.push(2);
		ss.push(4);

		while(true){
			System.out.println(ss.pop());
		}
	}
}
class SorterStack{
	Deque<Integer> sortedStack = new LinkedList<Integer>();
	Deque<Integer> auxStack = new LinkedList<Integer>();

	public void push(Integer i){
		moveBiggerToAux(i);
		sortedStack.push(i);
		move(auxStack,sortedStack);
	}
	public Integer pop(){
		if (sortedStack.isEmpty()) throw new NoSuchElementException();
		return sortedStack.pop();
	}
	private void moveBiggerToAux(Integer i){
		if (sortedStack.size() == 0) return;
		if (auxStack.size() > 0) throw new IllegalStateException();
		while(!sortedStack.isEmpty() && sortedStack.peek() > i){
			auxStack.push(sortedStack.pop());
		}
	}
	private void move(Deque<Integer> from, Deque<Integer> to){
		if (from.size() == 0) return;
		while(!from.isEmpty()){
			to.push(from.pop());
		}
	}
}
