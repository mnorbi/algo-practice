import java.util.*;

/**

Problem Statement
    
Given a board consisting of empty space, walls, and the starting positions of two players A and B, determine the minimum number of turns it will take for players A and B to switch positions on the board.
During a turn, one or both players may take a step. A step is defined as a unit movement up, down, left, right, or in any of the four diagonals. Players may not step into walls or off the board. Players may never share the same square at the end of a turn. Players may not cross paths during a turn. Crossing paths occurs when players A and B switch positions in a single turn. For example, assume player A is in the upper left corner of the board, and player B is in the square immediately to his right. Player A may not move right while player B moves left, since they would be passing each other. Player A can, however, move right if player B moves in any other direction.
You will be given a String[] board, representing the game board. board will contain exactly one 'A' and exactly one 'B'; each other character will be either '.' (empty space), or 'X' (a wall). Your method should return the minimum number of turns necessary for players A and B to switch positions, or -1 if this is impossible.
Definition
    
Class:
PathFinding
Method:
minTurns
Parameters:
String[]
Returns:
int
Method signature:
int minTurns(String[] board)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Notes
-
(In the following notes, assume that the coordinate system is given as (row, col). For example, the upper-left corner of the board is (0,0), and the square immedately below it is (1,0).)
-
If player A is at (0,0) and player B is at (0,1), player A would be allowed to move down-right at the same time that player B moves down-left. This is not considered crossing paths, even though the two players would meet at (0.5, 0.5).
-
However, if player A is at (0,0) and player B is at (1,1), player A would not be allowed to move down-right at the same time that player B moves up-left. This is considered crossing paths because players A and B have switched positions at the end of a single turn.
-
It is permissible to move diagonally through walls. That is, a player may move from (0,0) to (1,1) even if there are walls at (0,1) and (1,0).
Constraints
-
board will contain between 2 and 20 elements, inclusive.
-
Each element of board will contain between 2 and 20 characters, inclusive.
-
Each element of board will contain the same number of characters.
-
Each element of board will consist of only the characters '.', 'X'. 'A', and 'B'.
-
board will contain exactly one 'A' and exactly one 'B'.
Examples
0)

    
{"....",
 ".A..",
 "..B.",
 "...."}
Returns: 2
There are many ways to switch positions in two turns. For example, on turn one, player A could move right while player B moves up-right. Then, on turn two, player A could move down while player B stays where he is. It is illegal for the players to switch positions in a single turn. Therefore, the method returns 2.
1)

    
{"XXXXXXXXX",
 "A...X...B",
 "XXXXXXXXX"}
Returns: -1
Since the players cannot reach each other, they obviously cannot switch positions.
2)

    
{"XXXXXXXXX",
 "A.......B",
 "XXXXXXXXX"}
Returns: -1
Even though the players can reach each other, there is still no way for player B to ever get on the left side of player A.
3)

    
{"XXXXXXXXX",
 "A.......B",
 "XXXX.XXXX"}
Returns: 8
Players A and B spend the first three turns moving towards each other. On turn four, player A moves down-right while player B moves left. On turn five, player A moves up-right while player B moves left. It then takes three more turns of the players moving away from each other before they have switched positions, for a total of 8 turns.
4)

    
{"...A.XXXXX.....",
 ".....XXXXX.....",
 "...............",
 ".....XXXXX.B...",
 ".....XXXXX....."}
Returns: 13

5)

    
{"AB.................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "X..................X",
 ".XXXXXXXXXXXXXXXXXXX",
 "X..................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "X..................X",
 ".XXXXXXXXXXXXXXXXXXX",
 "X..................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "X..................X",
 ".XXXXXXXXXXXXXXXXXXX",
 "X..................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "X..................X",
 ".XXXXXXXXXXXXXXXXXXX",
 "X..................X",
 "XXXXXXXXXXXXXXXXXXX.",
 "...................X",
 ".XXXXXXXXXXXXXXXXXXX"}
Returns: 379

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
**/
class PathFinding{
	public int minTurns(String[] board){
		int h = board.length;
		int w = board[0].length();
		Node start = findStartPos(board);
		Node end = endPosFor(start);
		boolean[][][][] visited = initVisitedArray(w, h);
		Deque<Node> queue = new LinkedList<Node>();
		setVisited(start, visited);
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
	
	private boolean[][][][] initVisitedArray(int w, int h){
		boolean[][][][] ret = new boolean[h][][][];
		for(int i = 0; i < h; i++){
			ret[i] = new boolean[w][][];
			for(int j = 0; j < w; j++){
				ret[i][j] = new boolean[h][];
				for(int k = 0; k < h; k++){
					ret[i][j][k] = new boolean[w];
				}
			}
		}
		return ret;
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
