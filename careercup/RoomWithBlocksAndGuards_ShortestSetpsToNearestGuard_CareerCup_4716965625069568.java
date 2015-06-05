import java.util.*;

/**
Given a 2-D matrix represents the room, obstacle and guard like the following (0 is room, B->obstacle, G-> Guard): 
0 0 0 
B G G 
B 0 0 

calculate the steps from a room to nearest Guard and set the matrix, like this 
2 1 1 
B G G 
B 1 1 
Write the algorithm, with optimal solution.

**/
class RoomWithBlocksAndGuards_ShortestSetpsToNearestGuard_CareerCup_4716965625069568{
        public static void main(String[]args){
                char[][] board = fillBoard(
                        new String[]{
                                "G000000",
                                "0000000",
                                "00000G0",
                                "0000000"
                        });
                print(board);
        }
        private static void print(char[][] board){
                for(int i = 0; i < board.length; ++i){
                        for(int j = 0; j < board[i].length; ++j){
                                System.out.print(board[i][j]);
                        }
                        System.out.println();
                }
                System.out.println();
                System.out.println();
        }
        private static Point[] directions = new Point[]{
                new Point(+1,0,1), new Point(-1,0,1), new Point(0,+1,1), new Point(0,-1,1)
        };
        private static char[][] fillBoard(String[] boardString){
                char[][] board = toCharArray(boardString);
                Queue<Point> queue = new LinkedList<Point>();
                for(int i = 0; i < board.length; ++i){
                        for(int j = 0; j < board[i].length; ++j){
                                if (isGuard(board,i,j)){
                                        queue.add(new Point(i,j, 0));
                                }
                        }
                }
                while(!queue.isEmpty()){
                        Point point = queue.remove();
                        for(Point dir : directions){
                                Point newPoint = point.add(dir);
                                if(isValid(board, newPoint) && isEmptyRoom(board, newPoint)){
                                        board[newPoint.x][newPoint.y] = (char)('0'+newPoint.val);
                                        queue.add(newPoint);
                                }
                        }
                }
                return board;
        }
        private static boolean isEmptyRoom(char[][] board, Point point){
                return board[point.x][point.y] == '0';
        }
        private static boolean isValid(char[][] board, Point point){
                return point.x >= 0 && point.x < board.length &&
                    point.y >= 0 && point.y < board[point.x].length;
        }
        private static boolean isGuard(char[][] board, int i, int j){
                return board[i][j] == 'G';
        }
        private static char[][] toCharArray(String[] strings){
                char[][] ans = new char[strings.length][];
                for(int i = 0; i < ans.length; ++i){
                        ans[i] = strings[i].toCharArray();
                }
                return ans;
        }
}
class Point{
        int x, y, val;
        Point(int x, int y, int val){
                this.x = x;
                this.y = y;
                this.val = val;
        }
        Point add(Point other){
                Point ans = new Point(this.x+other.x, this.y+other.y, this.val+other.val);
                return ans;
        }
}
