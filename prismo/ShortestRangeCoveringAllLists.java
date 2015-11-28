import java.util.*;

class ShortestRangeCoveringAllLists{
    public static void main(String[]args){
        int[] sol = solve(
                new int[][]{
                        {17, 35, 63},
                        {18, 26, 66},
                        {42, 48, 90},
                        {26, 33, 39},
                        {36, 68, 72}
                }
        );
        System.out.println(Arrays.toString(sol));
        int[] sol2 = solve(
                new int[][]{
                        {15, 59, 70, 95},
                        {35, 41, 55, 91},
                        {22, 54, 57, 60}
                }
        );
        System.out.println(Arrays.toString(sol2));
    }
    static int[] solve(int[][] mat){
        Comparator<Cell> comp = (i1, i2) -> Integer.compare(mat[i1.row][i1.col], mat[i2.row][i2.col]);
        Cell rangeStart = findRangeStart(mat, comp);
        return createRange(mat, comp, rangeStart);
    }

    private static Cell findRangeStart(int[][] mat, Comparator<Cell> comp) {
        int ROWS = mat.length;
        int COLS = mat[0].length;
        PriorityQueue<Cell> minPq = new PriorityQueue<>(comp);
        minPq.add(new Cell(0, 0));
        Cell max = minPq.peek();
        for(int i = 1; i < ROWS; ++i){
            Cell cell = new Cell(i, 0);
            minPq.add(cell);
            if (comp.compare(max, cell) < 0){
                max = cell;
            }
        }
        Cell ret = null;
        int widthOpt = Integer.MAX_VALUE;
        while(true){
            Cell min = minPq.remove();
            int width = mat[max.row][max.col]-mat[min.row][min.col];
            if (widthOpt > width){
                widthOpt = width;
                ret = min;
            }
            if (min.col == COLS-1) break;
            Cell cell = new Cell(min.row, min.col +1);
            minPq.add(cell);
            if (comp.compare(max, cell) < 0){
                max = cell;
            }
        }
        return ret;
    }

    private static int[] createRange(int[][] mat, Comparator<Cell> comp, Cell rangeStart) {
        int ROWS = mat.length;
        int[] ret = new int[ROWS];
        PriorityQueue<Cell> minPq = new PriorityQueue<>(comp);
        for(int i = 0; i < ROWS; ++i){
            Cell cell = new Cell(i, 0);
            minPq.add(cell);
        }

        for(int i = 0;;){
            Cell min = minPq.peek();
            if (min.row == rangeStart.row && min.col == rangeStart.col){
                while(!minPq.isEmpty() ){
                    Cell cell = minPq.remove();
                    ret[i++] = mat[cell.row][cell.col];
                }
                return ret;
            }
            minPq.remove();
            Cell cell = new Cell(min.row, min.col+1);
            minPq.add(cell);
        }
    }

}
class Cell {
    int row, col;
    Cell(int row, int col){
        this.row = row;
        this.col = col;
    }
}
