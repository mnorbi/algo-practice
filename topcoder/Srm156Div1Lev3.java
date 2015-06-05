import java.util.*;
public class Srm156Div1Lev3{
	public static void main(String[] args){
		PathFinding pf = new PathFinding();
		System.out.println(
			pf.minTurns(
				new String[]{
					 "...A.XXXXX.....",
					 ".....XXXXX.....",
					 "...............",
					 ".....XXXXX.B...",
					 ".....XXXXX....."
				}
			)	
		);
	}
}
class PathFinding{
	public int minTurns(String[] board){
		int h = board.length;
		int w = board[0].length();
		Node start = findStartPos(board);
		Node end = endPosFor(start);
		boolean[][][][] visited = new boolean[h][w][h][w]; 
		Deque<Node> queue = new ArrayDeque<Node>();
		queue.addLast(start);
		
		while(!queue.isEmpty()){
			Node step = queue.removeFirst();
			if (step.equals(end)) return step.stepCount;
			addNextValidSteps(board, end, step, queue, visited);
		}
		
		return -1;
	}
	
	private void addNextValidSteps(String[] board, Node end, Node step, Deque<Node> queue, boolean[][][][] visited){
		for(int dxA = -1; dxA < 2; dxA++){
			for(int dyA = -1; dyA < 2; dyA++){
				for(int dxB = -1; dxB < 2; dxB++){
					for(int dyB = -1; dyB < 2; dyB++){
						Node nextStep = Node.nextStep(step, Point.delta(dxA, dyA), Point.delta(dxB, dyB));
						if (!isValid(board, nextStep)){
							continue;
						}
						if (isVisited(nextStep, visited)){
							continue;
						}
						if (isValid(step, nextStep)){
							setVisited(nextStep, visited);
							queue.addLast(nextStep);
						}
					}
				}
			}
		}
	}
	
	private void print(String[] board, Node step){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length(); j++){
				char c = board[i].charAt(j);
				if (c == 'A' || c == 'B'){
					c = '.';
				}
				Point p = Point.of(i, j);
				if (step.a.equals(p)) c = 'A';
				if (step.b.equals(p)) c = 'B';
				if (step.a.equals(p) && step.b.equals(p)) c = 'O';
				System.out.print(c);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void setVisited(Node step, boolean[][][][] visited){
		visited[step.a.y][step.a.x][step.b.y][step.b.x] = true;
	}
	
	private boolean isVisited(Node step, boolean[][][][] visited){
		return visited[step.a.y][step.a.x][step.b.y][step.b.x];
	}
	
	private boolean isValid(String[] board, Point p){
		int h = board.length;
		int w = board[0].length();
		boolean onBoard = p.x >= 0 && p.x < w && p.y >= 0 && p.y < h;
		if (!onBoard) return false;
		
		boolean onWall = board[p.y].charAt(p.x) == 'X';
		if (onWall) return false;
		
		return true;
	}
	
	private boolean isValid(String[] board, Node step){
		if (!isValid(board, step.a)) return false;
		if (!isValid(board, step.b)) return false;
		
		boolean playerOverlap = step.a.equals(step.b);
		if (playerOverlap) return false;
		
		return true;
	}
	
	private boolean isValid(Node step, Node nextStep){
		boolean crossing = nextStep.b.equals(step.a) && nextStep.a.equals(step.b);
		
		if (crossing) return false;
		
		return true;
	}
	
	private Node endPosFor(Node start){
		Node end = new Node();
		end.a = start.b;
		end.b = start.a;
		return end;
	}
	private Node findStartPos(String[] board){
		Node ret = new Node();
		for(int y = 0; y < board.length; y++){
			for(int x = 0; x < board[y].length(); x++){
				char c = board[y].charAt(x);
				if (c == 'A'){
					ret.a = Point.of(x, y);
					if (ret.b != null) return ret;
				} else if (c == 'B'){
					ret.b = Point.of(x, y);
					if (ret.a != null) return ret;
				}
			}
		}
		return ret;
	}
	
	private static class Node{
		int stepCount = 0;
		Point a; Point b;
		
		static Node nextStep(Node step, Point dA, Point dB){
			Node ret = new Node();
			ret.a = Point.add(step.a,dA);
			ret.b = Point.add(step.b,dB);
			ret.stepCount = step.stepCount + 1;
			return ret;
		}
		
		public boolean equals(Object obj){
			Node other = (Node) obj;
			return this == other ||
			    a.equals(other.a) &&
			    b.equals(other.b);
		}
		public String toString(){
			return String.format("A:%s B:%s", a.toString(), b.toString());
		}
	}
	
	private static class Point{
		int x, y;
		static Point of(int x, int y){
			Point p = new Point();
			p.x = x; p.y = y;
			return p;
		}
		private static Point deltas[][] = new Point[][]{
			{Point.of(-1,-1), Point.of(-1,0), Point.of(-1,1)},
			{Point.of(0,-1), Point.of(0,0), Point.of(0,1)},
			{Point.of(1,-1), Point.of(1,0), Point.of(1,1)}
		};
		static Point delta(int dx, int dy){
			return deltas[dx+1][dy+1];
		}
		static Point add(Point p, Point dP){
			return Point.of(p.x+dP.x,p.y+dP.y);
		}
		public boolean equals(Object obj){
			Point other = (Point)obj;
			return x == other.x && y == other.y;
		}
		public String toString(){
			return x + "," + y;
		}
	}
}
