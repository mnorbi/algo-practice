import java.util.*;
import java.io.*;
class Main{
    public static void main(String[]args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Board board = null;
        int row = 0;
        while(true){
            String nextLine = in.readLine();
            if (nextLine == null || "".equals(nextLine)) break;
            if (board == null) board = new Board(nextLine.length());
            int col = 0;
            for(; col < nextLine.length(); ++col){
                char c = nextLine.charAt(col);
                boolean ok = board.set(c, row, col);
                if (!ok) throw new IllegalArgumentException(String.format("[%d,%d]: %s", row, col, c));
            }
            if (col != board.getSize()) throw new IllegalArgumentException("col cnt error:"+col);
            ++row;
        }
        if (row != board.getSize()) throw new IllegalArgumentException("row cnt error:"+row);
        board.solve();
        board.print();
    }
}
class Board{
    static int[][] tripletPosArr = new int[][]{{-1,1}, {-2,-1},{1, 2}};
    int size;
    BitSet[] rows;
    BitSet[] cols;
    BitSet[] filledRows;
    BitSet[] filledCols;
    BitSet uniqRow = new BitSet();
    BitSet uniqCol = new BitSet();
    Board(int size){
        this.size = size;
        rows = newBitSetArrayOf(size);
        cols = newBitSetArrayOf(size);
        filledRows = newBitSetArrayOf(size);
        filledCols = newBitSetArrayOf(size);
    }
    public int getSize(){
      return this.size;
    }
    private BitSet[] newBitSetArrayOf(int size){
        BitSet[] ret = new BitSet[size];
        for(int a = 0; a < size; ++a){
            ret[a] = new BitSet();
        }
        return ret;
    }
    public Deque<Point> getUnfilledList(){
        Deque<Point> ret = new LinkedList<>();
        for(int row = 0; row < size; ++row){
            for(int col = 0; col < size; ++col){
                if (!isFilled(row, col)) {
                    ret.add(new Point(row, col));
                }
            }
        }
        return ret;
    }
    private boolean isFilled(int row, int col) {
        return filledRows[row].get(col);
    }

    public boolean set(char c, int row, int col){
        if (isFilled(row, col)) return false;
        if (c == '.') return true;
        if (!(c == 'X') && (c != 'O')) return false;

        boolean val = valueOf(c);

        if (isTriplet(row, col, val)) return false;

        if (val) {
            if (!isCardinalityXOk(rows[row], cols[col])) return false;
        } else {
            if (!isCardinalityOOk(row, col)) return false;
        }

        fill(row, col, val);

        boolean fullRow = isFullRow(row);
        boolean fullCol = isFullCol(col);
        int rowId = -1;
        if (fullRow){
            rowId = idOf(rows[row]);
            if (uniqRow.get(rowId)){
                unfill(row, col);
                return false;
            }
        }

        int colId = -1;
        if (fullCol){
            colId = idOf(cols[col]);
            if (uniqCol.get(colId)){
                unfill(row, col);
                return false;
            }
        }

        if (fullRow){
            uniqRow.set(rowId);
        }
        if (fullCol){
            uniqCol.set(colId);
        }

        return true;
    }

    private boolean isFullCol(int col){
        return filledCols[col].cardinality() >= size;
    }

    private boolean isFullRow(int row) {
        return filledRows[row].cardinality() >= size;
    }

    private boolean isCardinalityOOk(int row, int col) {
        if (filledRows[row].cardinality()-rows[row].cardinality() + 1 > size /2) return false;
        if (filledCols[col].cardinality()-cols[col].cardinality() + 1 > size/ 2) return false;
        return true;
    }

    private boolean isCardinalityXOk(BitSet row, BitSet col) {
        if (row.cardinality() + 1 > size / 2) return false;
        if (col.cardinality() + 1 > size / 2) return false;
        return true;
    }

    private boolean isTriplet(int row, int col, boolean val) {
        if (isTriplet(col, filledRows[row], rows[row], val)) return true;
        if (isTriplet(row, filledCols[col], cols[col], val)) return true;
        return false;
    }

    private boolean isTriplet(int pos, BitSet filled, BitSet vals, boolean val) {
        for(int[] tripletPos : tripletPosArr){
            int matchCnt = 0;
            for(int shift : tripletPos){
                int shiftedPos = pos+shift;
                if (shiftedPos >= 0 && shiftedPos < size){
                    if (!filled.get(shiftedPos) || !vals.get(shiftedPos) == val) break;
                    ++matchCnt;
                }
            }
            if (matchCnt >= 2) return true;
        }
        return false;
    }
    private int idOf(BitSet bs){
        return (int)bs.toLongArray()[0];
    }
    private boolean valueOf(char c){
        if (c == 'X') return true;
        if (c == 'O') return false;
        throw new IllegalArgumentException();
    }
    private void fill(int row, int col, boolean val){
        filledRows[row].set(col, true);
        filledCols[col].set(row, true);
        rows[row].set(col, val);
        cols[col].set(row, val);
    }
    private void unfill(int row, int col){
        filledRows[row].set(col, false);
        filledCols[col].set(row, false);
        rows[row].set(col, false);
        cols[col].set(row, false);
    }
    public boolean set(char c, Point p) {
        return set(c, p.row, p.col);
    }
    public void unset(Point p){
        if (!filledRows[p.row].get(p.col)) return;
        if (isFullRow(p.row)){
            uniqRow.set(idOf(rows[p.row]), false);
        }
        if (isFullCol(p.col)){
            uniqCol.set(idOf(cols[p.col]), false);
        }
        unfill(p.row, p.col);
    }

    public void print() {
        for(int row = 0; row < size; ++row){
            for(int col = 0; col < size; ++col){
                if (!filledRows[row].get(col)){
                    System.out.print('.');
                } else {
                    if (rows[row].get(col)){
                        System.out.print('X');
                    } else {
                        System.out.print('O');
                    }
                }
            }
            System.out.println();
        }
    }
    public void solve(){
        Deque<Point> unfilledList = this.getUnfilledList();

        boolean ok = solve(unfilledList);
        if (!ok){
            throw new IllegalArgumentException("unsolvable");
        }
    }
    private boolean solve(Deque<Point> unfilledList) {
        if (unfilledList.size() == 0) return true;
        Point point = unfilledList.removeLast();
        boolean setOk = this.set('X', point);
        if (setOk){
            boolean solved = solve(unfilledList);
            if (solved) return true;
        }
        this.unset(point);
        setOk = this.set('O', point);
        if (setOk){
            boolean solved = solve(unfilledList);
            if (solved) return true;
        }
        this.unset(point);
        unfilledList.addLast(point);
        return false;
    }

}
class Point{
    final int row, col;
    Point(int row, int col){
        this.row = row;
        this.col = col;
    }
    public String toString(){
      return "["+row+","+col+"]";
    }
}
