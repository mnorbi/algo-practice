import java.util.*;

public class Question9_9 {
	public static void main(String[] args){
		printAllNQueenBoards(8);
	}
	private static void printAllNQueenBoards(int size){
		BitSet[] board = new BitSet[size];
		for(int i = 0; i < board.length; ++i){
			board[i] = new BitSet();
		}
		printAllNQueenBoards(board, 0);
	}
	private static void printAllNQueenBoards(BitSet[] board, int x){
		if (x >= board.length){
			printBoard(board);
			return;
		}
		for(int y = 0; y < board.length; ++y){
			boolean placed = placeQueen(board, x, y);
			if (placed){
				printAllNQueenBoards(board, x+1);
				clearQueen(board, x, y);
			}
		}
	}
	private static boolean placeQueen(BitSet[] board, int x, int y){
		if (!checkRow(board,x, y)) return false;
		if (!checkColumn(board, x, y)) return false;
		if (!checkBackSlashDiagonal(board, x, y)) return false;
		if (!checkSlashDiagonal(board, x, y)) return false;

		board[y].set(x);
		return true;
	}
	private static boolean checkRow(BitSet[] board, int x, int y){
                return board[y].cardinality() == 0;
	}
	private static boolean checkColumn(BitSet[] board, int x, int y){
                for(BitSet row : board){
                        if (row.get(x)) return false;
                }
		return true;
	}
	private static boolean checkBackSlashDiagonal(BitSet[] board, int x, int y){
		if (!checkDiagonal(board, x, y, -1, -1)) return false;
		if (!checkDiagonal(board, x, y, +1, +1)) return false;
		return true;
	}
	private static boolean checkSlashDiagonal(BitSet[] board, int x, int y){
		if (!checkDiagonal(board, x, y, +1, -1)) return false;
		if (!checkDiagonal(board, x, y, -1, +1)) return false;
		return true;
	}
	private static boolean checkDiagonal(BitSet[] board, int x, int y, int dx, int dy){
		do{
			if (board[y].get(x)) return false;
			x+=dx;
			y+=dy;
		}while(x>= 0 && y>=0 && x<board.length && y<board.length);
		return true;
	}
	private static void clearQueen(BitSet[] board, int x, int y){
		board[y].clear(x);
	}
	private static void printBoard(BitSet[] board){
		for(int y = 0; y < board.length; y++){
			for(int x = 0; x < board.length; x++){
				System.out.print(board[y].get(x)?"1":0);
			}
			System.out.println();
		}
		for(int i = 0; i < board.length; ++i){
			System.out.print('-');
		}
		System.out.println();
	}
}
